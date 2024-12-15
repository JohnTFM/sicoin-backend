package br.ufg.sicoin.service;

import br.ufg.sicoin.dto.RequisicaoCaminhaoRotaDTO;
import br.ufg.sicoin.dto.googlemaps.DirectionsResponse;
import br.ufg.sicoin.dto.RespostaRotaDTO;
import br.ufg.sicoin.model.lixeira.Lixeira;
import br.ufg.sicoin.repository.LixeiraRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Exemplo de requisição:</p>
 * <code> curl -L -X GET 'https://maps.googleapis.com/maps/api/directions/json?origin=-34.92866,138.59863&destination=-34.92866,138.59863&waypoints=optimize:true|-34.5333,138.95|-33.8333,138.6|-37.3,140.8333|-35.2167,138.5333&key={api_key}'</code>
 * <p>Explicação: ..../json?origin={latitude},{longitude}&destination={latitude},{longitude}&waypoints=optimize:true|{latitude},{longitude}|{latitude},{longitude}&key={api_key}</p>
 */
@Service
@RequiredArgsConstructor
public class GoogleMapsService {

    private static final double EARTH_RADIUS = 6371.0088; // Raio da Terra em km (WGS84)

    private final LixeiraService lixeiraService;

    @Value("${sicoin.google-api-key}")
    String googleApiKey;

    @Value("${sicoin.google-maps-host-json}")
    String googleMapsHost;

    @Transactional
    public RespostaRotaDTO criarRota(RequisicaoCaminhaoRotaDTO geolocalizacao) {

        List<Lixeira> lixeiras = lixeiraService.obterLixeirasProximas(
                geolocalizacao.getLatitude(),
                geolocalizacao.getLongitude(),
                geolocalizacao.getKilometrosLimite()
        );

        RespostaRotaDTO respostaRotaDTO = new RespostaRotaDTO();

        if(lixeiras.isEmpty()) {
            respostaRotaDTO.setBackendStatus("NENHUMA LIXEIRA ENCONTRADA EM UM RÁIO DE 1KM PRÓXIMA!");
            return respostaRotaDTO;
        }

        respostaRotaDTO.setLixeiras(lixeiras);

        Lixeira ultimaLixeira = lixeiras.getLast();

        RestTemplate restTemplate = new RestTemplate();

        String url = googleMapsHost + "?origin={origin}&destination={destination}&waypoints={waypoints}&key={key}";

        Map<String, String> params = new HashMap<>();
        params.put("origin", geolocalizacao.getLatitude()+","+geolocalizacao.getLongitude());
        params.put("destination", ultimaLixeira.getLatitude() + "," + ultimaLixeira.getLongitude());
        params.put("waypoints",construirWaypoints(lixeiras));
        params.put("key", googleApiKey);

        DirectionsResponse response = restTemplate.getForObject(url, DirectionsResponse.class,params);

        if(response!=null && response.getStatus().equals("OK")){
            List<String> polylines = response.getRoutes().stream()
                    .flatMap(r -> r.getLegs().stream())
                    .flatMap(leg -> leg.getSteps().stream())
                    .map(step -> step.getPolyline().getPoints())
                    .toList();

            respostaRotaDTO.setPolylines(polylines);
            respostaRotaDTO.setTimestamp(Instant.now());
            respostaRotaDTO.setGoogleStatus(response.getStatus());
            respostaRotaDTO.setBackendStatus(response.getStatus());
            return respostaRotaDTO;

        }

        if(response!=null){
            respostaRotaDTO.setPolylines(null);
            respostaRotaDTO.setTimestamp(Instant.now());
            respostaRotaDTO.setGoogleStatus(response.getStatus());
            respostaRotaDTO.setBackendStatus(response.getStatus());
            return respostaRotaDTO;
        }
        throw new RuntimeException("Ocorreu um erro com a requisição!");
    }

    private String construirWaypoints(List<Lixeira> lix){
        List<Lixeira> lixeirasWaypoints = new ArrayList<>(lix);
        lixeirasWaypoints.removeLast();

        String waypointsParam = "";

        for (int i = 0; i < lixeirasWaypoints.size(); i++) {
            Lixeira lixeira = lixeirasWaypoints.get(i);
            if(i==0){
                waypointsParam+="optimize:true|"+lixeira.getLatitude()+","+lixeira.getLongitude();
            }else {
                waypointsParam+="|"+lixeira.getLatitude()+","+lixeira.getLongitude();
            }
        }

        return waypointsParam;
    }

    /**
     * Verifica se o lat1 e lon1 está perto com uma "distance" de lat2 e lon2.
     * @param lat1 passe em graus
     * @param lon1 passe em graus
     * @param lat2 passe em graus
     * @param lon2 passe em graus
     * @param distance passe em metros
     * @return
     */
    public static boolean estaPerto(double lat1, double lon1, double lat2, double lon2, double distance) {
        // Converter graus para radianos
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        // Diferença das coordenadas
        double deltaLat = lat2Rad - lat1Rad;
        double deltaLon = lon2Rad - lon1Rad;

        // Fórmula de Haversine
        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
                + Math.cos(lat1Rad) * Math.cos(lat2Rad)
                * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distanceBetweenPoints = EARTH_RADIUS * c;

        // Verificar se a distância está dentro do limite
        return distanceBetweenPoints <= distance;
    }


}
