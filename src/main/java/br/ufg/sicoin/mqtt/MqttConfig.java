package br.ufg.sicoin.mqtt;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

// broker:44.204.120.123:1883

/*
Tópicos:

“L001”
“L002”
“L003”
“L004”
 */

@Configuration
@Slf4j
public class MqttConfig {

    @Value("${sicoin.mqtt.host}")
    String mqttHost;

    @Bean
    public IMqttClient imqttClient() throws MqttException {
        String publisherId = UUID.randomUUID().toString();
        MqttClientPersistence persistence = new MemoryPersistence();
        return new MqttClient("tcp://"+mqttHost,publisherId,persistence);
    }

    @Bean
    public MqttConnectOptions mqttConnectOptions() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(3);
        return options;
    }

    @Bean
    public List<String> topics() {
        String sufix = "L";
        ArrayList<String> topics = new ArrayList<>();
        for(int i=0; i<100;i++){
            topics.add(sufix+i);
        }

        return Collections.unmodifiableList(topics);
    }


}
