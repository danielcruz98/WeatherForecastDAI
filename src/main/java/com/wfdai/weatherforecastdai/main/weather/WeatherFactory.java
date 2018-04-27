package main.java.com.wfdai.weatherforecastdai.main.weather;

import java.io.IOException;
import javax.xml.bind.JAXBException;
import main.java.com.wfdai.weatherforecastdai.main.GestorErros;

public class WeatherFactory {

    GestorErros erros = new GestorErros();

    public WeatherFactory() {
    }

    public static WeatherInterface getWeather(String criteria) {
        if (criteria.equals("yahoo")) {
            return new WeatherYahoo();
        } else if (criteria.equals("OWM")) {
            return new OpenWeatherMaps();
        }
        return null;
    }

    public void setWeather(String localizacao, Weather weather) throws JAXBException, IOException {

        try {
            WeatherInterface weatherIn = WeatherFactory.getWeather("OWM");
            weatherIn.setWeather(localizacao, weather);

        } catch (NullPointerException e) {

            System.out.println("city not found");

            try {
                WeatherInterface weatherIn = WeatherFactory.getWeather("yahoo");
                weatherIn.setWeather(localizacao, weather);

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

}
