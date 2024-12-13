package br.ufg.sicoin.service;

import br.ufg.sicoin.dto.DirectionsResponse;
import br.ufg.sicoin.dto.Geolocalizacao;
import br.ufg.sicoin.dto.RespostaRotaDTO;
import br.ufg.sicoin.model.lixeira.Lixeira;
import br.ufg.sicoin.repository.LixeiraRepository;
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
public class GoogleMapsService {

    //smp latitude,longitude

    LixeiraRepository lixeiraRepository;

    @Value("${sicoin.google-api-key}")
    String googleApiKey;

    @Value("${sicoin.google-maps-host-json}")
    String googleMapsHost;

    public RespostaRotaDTO criarRota(Geolocalizacao geolocalizacao) {
        List<Lixeira> lixeiras = new ArrayList<>();

        lixeiras.add(Lixeira.builder()
                .latitude(-16.59793650250547D)
                .longitude(-49.26148284733547)
                .build()
        );

        lixeiras.add(Lixeira.builder()
                .latitude(-16.599475313024744)
                .longitude(-49.25959056509277)
                .build()
        );

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

            RespostaRotaDTO respostaRotaDTO = new RespostaRotaDTO();
            respostaRotaDTO.setPolylines(polylines);
            respostaRotaDTO.setTimestamp(Instant.now());
            respostaRotaDTO.setGoogleStatus(response.getStatus());
            return respostaRotaDTO;

        }

        if(response!=null){
            RespostaRotaDTO respostaRotaDTO = new RespostaRotaDTO();
            respostaRotaDTO.setPolylines(null);
            respostaRotaDTO.setTimestamp(Instant.now());
            respostaRotaDTO.setGoogleStatus(response.getStatus());
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


}
