package br.ufg.sicoin.mqtt;

import br.ufg.sicoin.dto.mqtt.LixeiraSinalDTO;
import br.ufg.sicoin.repository.LixeiraRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@Slf4j
@RequiredArgsConstructor
public class MqttTwinListener implements MqttCallback {

    private final LixeiraRepository lixeiraRepository;

    private final ObjectMapper objectMapper;

    @Override
    public void connectionLost(Throwable throwable) {

        log.error("Conexão ao MQTT PERDIDA!");

    }

    @Override
    @Transactional
    public void messageArrived(String s, MqttMessage mqttMessage){

        try{
            LixeiraSinalDTO dto = objectMapper.readValue(new String(mqttMessage.getPayload()), LixeiraSinalDTO.class);

            lixeiraRepository.atualizarLixeiraDoMqqtt(
                    dto.getId(),
                    dto.getPesoAtual(),
                    dto.getVolumeAtual(),
                    Instant.now()
            );

        }catch (Exception e){
            log.error(e.getMessage(),e);
        }

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        //nao sera enviada mensagens ao broker
    }
}
