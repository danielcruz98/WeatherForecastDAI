package main.java.com.wfdai.weatherforecastdai.main.KPI;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class KPI implements MqttCallback {

    private String uptime, totalClients, activeClients, messages, subscriptions, receivedLoad5, receivedLoad15, bytesSent15;

    MqttClient client;

    public KPI() {
    }

    public void getKPI() throws InterruptedException {
        try {
            client = new MqttClient("tcp://127.0.0.1:8080", "javaServer");
            client.connect();
            client.setCallback(this);
            client.subscribe("$SYS/broker/#");
            Thread.sleep(9 * 1000);
            client.disconnect();

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
    

    @Override
    public void connectionLost(Throwable cause) {
        // TODO Auto-generated method stub

    }

    @Override
    public void messageArrived(String topic, MqttMessage message)
            throws Exception {
     
        this.setKPI( message.toString(),  topic);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        // TODO Auto-generated method stub

    }

    public void setKPI(String message, String topic) {
        
        switch (topic) {
            case "$SYS/broker/uptime":
                this.setUptime(message);
                break;
            case "$SYS/broker/clients/total":
                this.setTotalClients(message);
                break;
            case "$SYS/broker/clients/active":
                this.setActiveClients(message);
                break;
            case "$SYS/broker/messages/stored":
                this.setMessages(message);
                break;
            case "$SYS/broker/subscriptions/count":
                this.setSubscriptions(message);
                break;
            case "$SYS/broker/load/messages/received/5min":
                this.setReceivedLoad5(message);
                break;
            case "$SYS/broker/load/messages/received/15min":
                this.setReceivedLoad15(message);
                break;
            case "$SYS/broker/load/bytes/sent/15min":
                this.setBytesSent15(message);
                break;
            default:
                break;
        }
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    public String getTotalClients() {
        return totalClients;
    }

    public void setTotalClients(String totalClients) {
        this.totalClients = totalClients;
    }

    public String getActiveClients() {
        return activeClients;
    }

    public void setActiveClients(String activeClients) {
        this.activeClients = activeClients;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public String getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(String subscriptions) {
        this.subscriptions = subscriptions;
    }

    public String getReceivedLoad5() {
        return receivedLoad5;
    }

    public void setReceivedLoad5(String receivedLoad5) {
        this.receivedLoad5 = receivedLoad5;
    }

    public String getReceivedLoad15() {
        return receivedLoad15;
    }

    public void setReceivedLoad15(String receivedLoad15) {
        this.receivedLoad15 = receivedLoad15;
    }

    public String getBytesSent15() {
        return bytesSent15;
    }

    public void setBytesSent15(String bytesSent15) {
        this.bytesSent15 = bytesSent15;
    }

}
