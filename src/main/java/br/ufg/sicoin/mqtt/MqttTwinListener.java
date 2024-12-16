package br.ufg.sicoin.mqtt;

import br.ufg.sicoin.dto.mqtt.LixeiraSinalDTO;
import br.ufg.sicoin.model.lixeira.Lixeira;
import br.ufg.sicoin.repository.LixeiraRepository;
import br.ufg.sicoin.service.LixeiraService;
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

    private final LixeiraService lixeiraService;

    @Override
    public void connectionLost(Throwable throwable) {

        log.error("Conexão ao MQTT PERDIDA!");

    }



    @Override
    public void messageArrived(String s, MqttMessage mqttMessage){
        LixeiraSinalDTO dto = null;
        try{
             dto = objectMapper.readValue(new String(mqttMessage.getPayload()), LixeiraSinalDTO.class);
            lixeiraService.atualizarLixeiraDoDTO(dto);
        }catch (Exception e){
            log.info("Erro ao atualizar lixeira do dto!");
            log.error(e.getMessage(),e);
        }

        if(dto==null)
            return;

        var optLixeria = lixeiraRepository.findById(dto.getId());

        if(optLixeria.isEmpty())
            return;

        Lixeira lixeira = optLixeria.get();

        //todo verificar no banco se existiu um evento de coleta dessa lixeira em no máximo 1 minuto, caso positivo
        // , verificar se a lixeira deu uma esvaziada, se tiver dado, setar o evento de coleta como "lixeira_esvaziou"=true
        // caso a lixeira não tiver sido esvaziada, setar como false.

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        //nao sera enviada mensagens ao broker
    }
}
