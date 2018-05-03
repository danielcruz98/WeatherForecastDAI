package main.java.com.wfdai.weatherforecastdai.main.weather;

import java.util.Date;

/**
 * Classe para instanciar os dados metereologicos
 *
 * @author daniel
 */
public class Weather {

    int direcaoVento;
    float velocidadeVento;
    double temperatura;
    float pressao;
    Date dataDados;
    int humidade;
    float visibilidade;
    String nascerSol;
    String porSol;

    public int getDirecaoVento() {
        return direcaoVento;
    }

    public float getVelocidadeVento() {
        return velocidadeVento;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public float getPressao() {
        return pressao;
    }

    public Date getDataDados() {
        return dataDados;
    }

    public int getHumidade() {
        return humidade;
    }

    public float getVisibilidade() {
        return visibilidade;
    }

    public String getNascerSol() {
        return nascerSol;
    }

    public String getPorSol() {
        return porSol;
    }
}
