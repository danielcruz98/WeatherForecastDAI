package main.java.com.wfdai.weatherforecastdai.main;

import java.io.IOException;
import javax.xml.bind.JAXBException;

public class Main{


    public static void main(String[] args) throws JAXBException, IOException, InterruptedException {
        App app = new App();
        GestorErros gestorErros= new GestorErros();
        gestorErros.getErro();
        app.cicloKpi();


        for (int i = 6; i < 7; i++) {
            if (i < 5) {
                
                app.cicloKpi();
                app.cicloLocalidades();
                System.out.println("sleep");
                Thread.sleep(10 * 60 * 1000);
            } else {
                app.cicloHistorico();
                i = 0;
            }
        }
    }
}
