package main.java.com.wfdai.weatherforecastdai.main;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import javax.xml.bind.JAXBException;

/**
 * Define o processo de execução da App WFDAI
 *
 * @author daniel
 */
public class Main {

    public static void main(String[] args) throws JAXBException, IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        Alerta a = new Alerta();
        //objectMapper.writeValue(new File("target/dadosAlerta.json"), a);
        //String appAsString = objectMapper.writeValueAsString(a);
        
        
        
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        
        Alerta alerta = objectMapper.readValue(new File("target/dadosAlerta.json"), Alerta.class);
                
        App app = new App();
        GestorErros gestorErros = new GestorErros();
        gestorErros.getErro();
        app.cicloKpi();
        GestorErros erros = new GestorErros();
        for (int i = 1; i < 10; i++) {
            if (i < 8) {

                //pp.cicloKpi();
                app.cicloLocalidades();
                System.out.println("sleep");
                try {
                    Thread.sleep(5 * 60 * 1000);
                } catch (InterruptedException exc) {
                    erros.putErro("Thread interrupted: " + exc.getCause().toString());
                    break;
                }

            } else {
                app.cicloHistorico();
                i = 0;
            }
        }
    }
}
