package main.java.com.wfdai.weatherforecastdai.main;

import main.java.com.wfdai.weatherforecastdai.main.weather.Weather;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
/**
 * Envia alertas
 *
 * @author daniel
 */
public class Alerta {

    Parser parser = new Parser();
    Publisher publisher = new Publisher();
    
    private int gelo,fogo,chuva,vento;

    /**
     * Verifica se é necessário enviar alerta e , em caso afirmativo envia uma
     * mensagem para o Broker no topico respetivo
     *
     * @param weather Objecto com os dados meteorologicos recolhidos.
     * @param localidade String com a localidade.
     */
    public void checkAlerta(Weather weather, String localidade) {
        if (weather.getTemperatura() < gelo) {
            parser.setParser(weather.getDataDados(), "gelo", "Alerta! Possibilidade de gelo na estrada",
                    "Detectamos que a temperatura ambiente em " + localidade + " é inferior a 2ºC, "
                    + "o que pode significar uma grande probabilidade de gelo na estrada.");
            publisher.publish(localidade + "/alertas/gelo", parser.getParsedMessage(), false);
            
            System.out.println(getGelo());
        }
        if (weather.getTemperatura() > fogo) {
            parser.setParser(weather.getDataDados(), "incendio", "Alerta! Possibilidade de Incendio",
                    "Detectamos que a temperatura ambiente em " + localidade + ""
                    + " é superior a 30ºC, o que pode significar um risco de incendio muito elevado.");
            publisher.publish(localidade + "/alertas/incendio", parser.getParsedMessage(), false);
              System.out.println(getGelo());
        }
        if (weather.getHumidade() > chuva) {
            parser.setParser(weather.getDataDados(), "precipitacao", "Alerta! Possibilidade de Precipitação",
                    "Detectamos que a percentagem de húmidade no ar em " + localidade + " é superior a 95%, o que"
                    + " pode significar Precipitação.");
            publisher.publish(localidade + "/alertas/precipitacao", parser.getParsedMessage(), false);
              System.out.println(getGelo());
        }
        if (weather.getVelocidadeVento() > vento) {
            parser.setParser(weather.getDataDados(), "vento", "Alerta! Possibilidade de Ventos Fortes",
                    "Detectamos Ventos fortes em " + localidade + ", com uma velocidade aproximada de " + weather.getVelocidadeVento() + "km/h.");
            publisher.publish(localidade + "/alertas/vento", parser.getParsedMessage(), false);
              System.out.println(getGelo());
        }

    }

    public void setGelo(int gelo) {
        this.gelo = gelo;
    }

    public void setFogo(int fogo) {
        this.fogo = fogo;
    }

    public void setChuva(int chuva) {
        this.chuva = chuva;
    }

    public void setVento(int vento) {
        this.vento = vento;
    }

    public int getGelo() {
        return gelo;
    }

    public int getFogo() {
        return fogo;
    }

    public int getChuva() {
        return chuva;
    }

    public int getVento() {
        return vento;
    }
    
    

}
