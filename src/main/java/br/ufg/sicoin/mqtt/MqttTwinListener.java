package br.ufg.sicoin.mqtt;

import br.ufg.sicoin.dto.mqtt.LixeiraSinalDTO;
import br.ufg.sicoin.event.transients.ColetaLixeiraEvent;
import br.ufg.sicoin.model.lixeira.Lixeira;
import br.ufg.sicoin.repository.ColetaEventRepository;
import br.ufg.sicoin.repository.LixeiraRepository;
import br.ufg.sicoin.service.LixeiraService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class MqttTwinListener implements MqttCallback {

    private final LixeiraRepository lixeiraRepository;

    private final ObjectMapper objectMapper;

    private final LixeiraService lixeiraService;

    private final ColetaEventRepository coletaEventRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void connectionLost(Throwable throwable) {

        log.error("Conexão ao MQTT PERDIDA!");

    }


    @Override
    public void messageArrived(String s, MqttMessage mqttMessage){


        LixeiraSinalDTO dto = null;

        Optional<Lixeira> optLixeria= Optional.empty();

        try{
             dto = objectMapper.readValue(new String(mqttMessage.getPayload()), LixeiraSinalDTO.class);

            log.info("Sinal da lixeira "+ dto.getId() + " recebido.");

             optLixeria = lixeiraRepository.findById(dto.getId());

            lixeiraService.atualizarLixeiraDoDTO(dto);

        }catch (Exception e){

            log.info("Erro ao atualizar lixeira do dto!");

            log.error(e.getMessage(),e);

        }

       try{
           if(dto==null)
               return;

           if(optLixeria.isEmpty())
               return;

           Lixeira lixeiraAntiga = optLixeria.get();


           boolean isColetada = identificarColeta(lixeiraAntiga,dto);

           if(!isColetada){
               return;
           }

           applicationEventPublisher.publishEvent(new ColetaLixeiraEvent(lixeiraAntiga,true));
       }catch (Exception e){
           log.error("Erro ao atualizar lixeira do dto!", e);
       }

    }

    private boolean identificarColeta(Lixeira lixeiraAntiga, LixeiraSinalDTO lixeiraSinalDTO){

        double nivelVolumeLixeiraNovaEmRelacaoAAntiga = lixeiraSinalDTO.getVolumeAtual()/lixeiraAntiga.getVolumeAtual();

        double nivelPesoLixeiraNovaEmRelacaoAntiga = lixeiraSinalDTO.getPesoAtual()/lixeiraAntiga.getPesoAtual();

        boolean diminuiu85PorcentoVolume = nivelVolumeLixeiraNovaEmRelacaoAAntiga <0.15;

        boolean diminuiu85PorcentoPeso = nivelPesoLixeiraNovaEmRelacaoAntiga <0.15;

        return diminuiu85PorcentoVolume && diminuiu85PorcentoPeso;
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        //nao sera enviada mensagens ao broker
    }
}
