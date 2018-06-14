package main.java.com.wfdai.weatherforecastdai.main;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import main.java.com.wfdai.weatherforecastdai.main.weather.Weather;

/**
 * Envia alertas
 *
 * @author daniel
 */
public class Alerta {

    Parser parser = new Parser();
    Publisher publisher = new Publisher();
    
    private  int gelo, fogo, chuva, vento;
    
    public Alerta(){
    
        ObjectMapper mapper = new ObjectMapper();
        try {
            File jsonInputFile = new File("target/configuração.cfg");
            JsonNode rootNode = mapper.readTree(jsonInputFile);
            JsonNode importGelo = rootNode.path("gelo");
            gelo = importGelo.intValue();
            System.out.println("alertagelo"+ gelo);
            JsonNode importFogo = rootNode.path("fogo");
            fogo = importFogo.intValue();
            System.out.println("alertafogo"+fogo);
            JsonNode importChuva = rootNode.path("chuva");
            chuva = importChuva.intValue();
            System.out.println("alertachuva"+chuva);
            JsonNode importVento = rootNode.path("vento");
            vento = importVento.intValue();
            System.out.println("alertavento"+vento);

             
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    

    /**
     * Verifica se é necessário enviar alerta e , em caso afirmativo envia uma
     * mensagem para o Broker no topico respetivo
     *
     * @param weather Objecto com os dados meteorologicos recolhidos.
     * @param localidade String com a localidade.
     */
    public void checkAlerta(Weather weather, String localidade) {
        if (weather.getTemperatura() < gelo) {//2
            parser.setParser(weather.getDataDados(), "gelo", "Alerta! Possibilidade de gelo na estrada",
                    "Detectamos que a temperatura ambiente em " + localidade + " é inferior a "+gelo+", "
                    + "o que pode significar uma grande probabilidade de gelo na estrada.");
            publisher.publish(localidade + "/alertas/gelo", parser.getParsedMessage(), false);
        }
        if (weather.getTemperatura() > fogo) {//30
            parser.setParser(weather.getDataDados(), "incendio", "Alerta! Possibilidade de Incendio",
                    "Detectamos que a temperatura ambiente em " + localidade + ""
                    + " é superior a "+fogo+", o que pode significar um risco de incendio muito elevado.");
            publisher.publish(localidade + "/alertas/incendio", parser.getParsedMessage(), false);
        }
        if (weather.getHumidade() > chuva) {//95
            parser.setParser(weather.getDataDados(), "precipitacao", "Alerta! Possibilidade de Precipitação",
                    "Detectamos que a percentagem de húmidade no ar em " + localidade + " é superior a "+chuva+"%, o que"
                    + " pode significar Precipitação.");
            publisher.publish(localidade + "/alertas/precipitacao", parser.getParsedMessage(), false);
        }
        if (weather.getVelocidadeVento() > vento) {//90
            parser.setParser(weather.getDataDados(), "vento", "Alerta! Possibilidade de Ventos Fortes",
                    "Detectamos Ventos fortes em " + localidade + ", com uma velocidade aproximada de " + weather.getVelocidadeVento() + "km/h.");
            publisher.publish(localidade + "/alertas/vento", parser.getParsedMessage(), false);
        }

    }

}
