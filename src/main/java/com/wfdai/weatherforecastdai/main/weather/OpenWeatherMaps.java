package main.java.com.wfdai.weatherforecastdai.main.weather;

import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.xml.bind.JAXBException;
import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.OpenWeatherMap;

/**
 * Recolhe os dados da API externa Open Weather Maps
 *
 * @author daniel
 */
public class OpenWeatherMaps implements WeatherInterface {

    /**
     * Recolhe os dados da API externa Open Weather Maps
     *
     * @param localizacao String com a localização
     * @param weather Objecto com os dados meteorologicos recolhidos.
     * @throws JAXBException
     * @throws IOException
     */
    @Override
    public void setWeather(String localizacao, Weather weather) throws JAXBException, IOException {
        boolean isMetric = true;
        OpenWeatherMap owm = new OpenWeatherMap("");
        owm.setUnits(OpenWeatherMap.Units.METRIC);
        owm.setApiKey("07187078b07349a4c3098d15c10305af");
        owm.setLang(OpenWeatherMap.Language.PORTUGUESE);
        CurrentWeather cwd = owm.currentWeatherByCityName(localizacao);
        weather.direcaoVento = Math.round(cwd.getWindInstance().getWindDegree());
        weather.velocidadeVento = cwd.getWindInstance().getWindSpeed();
        weather.temperatura = Math.round(cwd.getMainInstance().getTemperature());
        weather.pressao = Math.round(cwd.getMainInstance().getPressure());
        weather.dataDados = cwd.getDateTime();
        weather.humidade = Math.round(cwd.getMainInstance().getHumidity());
        weather.visibilidade = 100 - cwd.getCloudsInstance().getPercentageOfClouds();
        weather.nascerSol = new SimpleDateFormat("HH:mm").format(cwd.getSysInstance().getSunriseTime());
        weather.porSol = new SimpleDateFormat("HH:mm").format(cwd.getSysInstance().getSunsetTime());
    }
}
