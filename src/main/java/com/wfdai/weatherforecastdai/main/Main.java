package main.java.com.wfdai.weatherforecastdai.main;

import java.io.IOException;
import javax.xml.bind.JAXBException;

/**
 * Define o processo de execução da App WFDAI
 *
 * @author daniel
 */
public class Main {

    public static void main(String[] args) throws JAXBException, IOException, InterruptedException {
        App app = new App();
        GestorErros gestorErros = new GestorErros();
        gestorErros.getErro();
        app.cicloKpi();
        GestorErros erros = new GestorErros();
        for (int i = 9; i < 10; i++) {
            if (i < 8) {

                app.cicloKpi();
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
