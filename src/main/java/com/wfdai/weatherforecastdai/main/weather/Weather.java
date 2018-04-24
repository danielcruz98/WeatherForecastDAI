package main.java.com.wfdai.weatherforecastdai.main.weather;

import java.io.IOException;
import java.util.Date;
import javax.xml.bind.JAXBException;
import main.java.com.wfdai.weatherforecastdai.main.GestorErros;

public class Weather {
    //inicializar variaveis

    //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    WeatherYahoo weatherYahoo = new WeatherYahoo();
    OpenWeatherMaps openWeatherMaps = new OpenWeatherMaps();
    GestorErros erros = new GestorErros();

    int direcaoVento;
    float velocidadeVento;
    int temperatura;
    float pressao;
    Date dataDados;
    int humidade;
    float visibilidade;
    String nascerSol;
    String porSol;
    // String tempo;
    //construtor

    public Weather() {
    }

    public Weather(int direcaoVento, float velocidadeVento, int temperatura, float pressao, Date dataDados, int humidade, float visibilidade, String nascerSol, String porSol) {
        this.direcaoVento = direcaoVento;
        this.velocidadeVento = velocidadeVento;
        this.temperatura = temperatura;
        this.pressao = pressao;
        this.dataDados = dataDados;
        this.humidade = humidade;
        this.visibilidade = visibilidade;
        this.nascerSol = nascerSol;
        this.porSol = porSol;
    }

    public void setWeather(String localizacao, Weather weather) throws JAXBException, IOException {

        try {
            openWeatherMaps.setWeatherOWM(localizacao, weather);

        } catch (NullPointerException e) {

            System.out.println("city not found");

            try {
                weatherYahoo.setWeatherYahoo(localizacao, weather);

            } catch (IOException ex) {
                erros.putErro("YW IOException: " + ex.getCause().toString());

            } catch (JAXBException ex) {
                erros.putErro("YW JAXBException: " + ex.getCause().toString());
            }

        } catch (IOException ex) {
            erros.putErro("OWM IOException: " + ex.getCause().toString());
        } catch (JAXBException ex) {
            erros.putErro("OWM JAXBException: " + ex.getCause().toString());
        }
    }

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
