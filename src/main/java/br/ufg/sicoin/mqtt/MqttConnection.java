package br.ufg.sicoin.mqtt;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@Slf4j
@RequiredArgsConstructor
public class MqttConnection {

    private final IMqttClient iMqttClient;

    private final MqttCallback callback;

    private final MqttConnectOptions options;

    private final List<String> topics;

    @PostConstruct
    public void conectarAoMQTT() {

        if(iMqttClient.isConnected()) {
           return;
        }

        try {
            iMqttClient.connect(options);
            log.info("CONECTADO ao MQTT: {}", iMqttClient.isConnected());

            String[] topicArray = topics.toArray(new String[0]);
            int[] qosArray = new int[topics.size()];

            iMqttClient.subscribe(topicArray, qosArray);

            iMqttClient.setCallback(callback);

            log.info("Inscrição nos tópicos finalizada!");

        } catch (MqttException e) {
            log.error("Erro ao tentar conectar ao broker MQTT: {}", e.getMessage(), e);
        }

    }

}
