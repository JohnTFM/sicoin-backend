package br.ufg.sicoin.mqtt;

import br.ufg.sicoin.dto.mqtt.LixeiraSinalDTO;
import br.ufg.sicoin.repository.LixeiraRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@Slf4j
@RequiredArgsConstructor
public class MqttTwinListener implements IMqttMessageListener {

    private final LixeiraRepository lixeiraRepository;

    private final ObjectMapper objectMapper;

    @Override
    @Transactional
    public void messageArrived(String s, MqttMessage mqttMessage){

        try{
            LixeiraSinalDTO dto = objectMapper.readValue(new String(mqttMessage.getPayload()), LixeiraSinalDTO.class);

            log.info(dto.getLatitude().toString());

            log.info(dto.getLongitude().toString());

            lixeiraRepository.atualizarLixeiraDoMqqtt(
                    dto.getId(),
                    dto.getPesoAtual(),
                    dto.getVolumeAtual(),
                    Instant.now(),
                    dto.getLatitude(),
                    dto.getLongitude()
            );

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
