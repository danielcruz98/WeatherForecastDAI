package main.java.com.wfdai.weatherforecastdai.main;

import java.io.IOException;
import javax.xml.bind.JAXBException;

public class Main {

    public static void main(String[] args) throws JAXBException, IOException, InterruptedException {
        App app = new App();
        GestorErros gestorErros = new GestorErros();
        gestorErros.getErro();
        app.cicloKpi();

        for (int i = 9; i < 10; i++) {
            if (i < 8) {

                app.cicloKpi();
                app.cicloLocalidades();
                System.out.println("sleep");
                try {
                    Thread.sleep(5 * 60 * 1000);
                } catch (InterruptedException exc) {
                    break;
                }
                
            } else {
                app.cicloHistorico();
                i = 0;
            }
        }
    }
}
