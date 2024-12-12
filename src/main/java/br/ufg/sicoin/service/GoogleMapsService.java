package br.ufg.sicoin.service;

import br.ufg.sicoin.repository.LixeiraRepository;
import org.springframework.stereotype.Service;

/**
 * <p>Exemplo de requisição:</p>
 * <code> curl -L -X GET 'https://maps.googleapis.com/maps/api/directions/json?origin=-34.92866,138.59863&destination=-34.92866,138.59863&waypoints=optimize:true|-34.5333,138.95|-33.8333,138.6|-37.3,140.8333|-35.2167,138.5333&key={api_key}'</code>
 * <p>Explicação: ..../json?origin={latitude},{longitude}&destination={latitude},{longitude}&waypoints=optimize:true|{latitude},{longitude}|{latitude},{longitude}&key={api_key}</p>
 */
@Service
public class GoogleMapsService {

    LixeiraRepository lixeiraRepository;

}
