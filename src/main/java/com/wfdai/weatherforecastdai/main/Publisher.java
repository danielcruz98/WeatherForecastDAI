package main.java.com.wfdai.weatherforecastdai.main;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Publisher {
        String topic;
        String content;
     //   int qos             = 0;
     //   String broker       = "localhost:666";
     //   String clientId     = "javaServer";
        MemoryPersistence persistence;

    public Publisher() {
    }
    
    public void publish(String localidade, String mensagem) {
    this.publish( localidade, mensagem, true) ;
    
    }
    public void publish(String localidade, String mensagem, boolean retained) {
        int qos             = 2;
        String broker       = "tcp://127.0.0.1:8080";
        String clientId     = "javaServer";
        this.topic = localidade;
        this.content = mensagem;
        this.persistence = new MemoryPersistence();
        
        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: "+broker);
            sampleClient.connect(connOpts);
            System.out.println("Connected");
            System.out.println("Publishing message: "+content);
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            message.setRetained(retained);
            sampleClient.publish(topic, message);
            System.out.println("Message published");
            sampleClient.disconnect();
            System.out.println("Disconnected");
            //System.exit(0);
        } catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
        }
    }
}