package com.mycompany.myapp.MqttHandler;

import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

@Configuration
public class mqttMessageHandler {

    @ServiceActivator(inputChannel = "mqttInputChannel")
    public void handleMessage(Message<String> message) {
        System.out.println("Received message: " + message.getPayload());
    }
}
