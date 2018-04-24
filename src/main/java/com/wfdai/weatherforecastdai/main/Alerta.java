package main.java.com.wfdai.weatherforecastdai.main;

import main.java.com.wfdai.weatherforecastdai.main.weather.Weather;

/**
 *
 * @author daniel
 */
public class Alerta {

    Parser parser = new Parser();
    Publisher publisher = new Publisher();


    public void checkAlerta(Weather weather, String localidade) {
        if (weather.getTemperatura() < 2) {
            parser.setParser(weather.getDataDados(), "gelo", "Alerta! Possibilidade de gelo na estrada",
                    "Detectamos que a temperatura ambiente em " + localidade + " é inferior a 2ºC, "
                            + "o que pode significar uma grande probabilidade de gelo na estrada.");
            publisher.publish(localidade + "/alertas/gelo", parser.getParsedMessage(), false);
        }
        if (weather.getTemperatura() > 30) {
            parser.setParser(weather.getDataDados(), "incendio", "Alerta! Possibilidade de Incendio",
                    "Detectamos que a temperatura ambiente em " + localidade + ""
                            + " é superior a 30ºC, o que pode significar um risco de incendio muito elevado.");
            publisher.publish(localidade + "/alertas/incendio", parser.getParsedMessage(), false);
        }
        if (weather.getHumidade() > 95) {
            parser.setParser(weather.getDataDados(), "precipitacao", "Alerta! Possibilidade de Precipitação",
                    "Detectamos que a percentagem de húmidade no ar em " + localidade + " é superior a 95%, o que"
                            + " pode significar Precipitação.");
            publisher.publish(localidade + "/alertas/precipitacao", parser.getParsedMessage(), false);
        }
        if (weather.getVelocidadeVento() > 90) {
            parser.setParser(weather.getDataDados(), "vento", "Alerta! Possibilidade de Ventos Fortes", 
                    "Detectamos Ventos fortes em " + localidade + ", com uma velocidade aproximada de "+weather.getVelocidadeVento()+"km/h.");
            publisher.publish(localidade + "/alertas/vento", parser.getParsedMessage(), false);
        }

    }

}
