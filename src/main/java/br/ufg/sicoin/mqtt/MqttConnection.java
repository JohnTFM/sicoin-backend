package br.ufg.sicoin.mqtt;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@Slf4j
@RequiredArgsConstructor
public class MqttConnection {

    private final IMqttClient iMqttClient;

    private final IMqttMessageListener mqttTwinListener;

    private final MqttConnectOptions options;

    private final List<String> topics;

    @PostConstruct
    public void conectarAoMQTT() {

        if(iMqttClient.isConnected()) {
           return;
        }

        try {
            log.info("Conectando ao MQTT");
            iMqttClient.connect(options);
            log.info("CONECTADO ao MQTT: " + iMqttClient.isConnected());

            topics.forEach(t->{
                try {
                    iMqttClient.subscribe(t,mqttTwinListener);
                } catch (MqttException e) {
                    log.error("Erro ao tentar se inscrever ao tópico MQTT {}", e.getMessage(), e);
                }
            });

        } catch (MqttException e) {
            log.error("Erro ao tentar conectar ao broker MQTT: {}", e.getMessage(), e);
        }

    }

}
