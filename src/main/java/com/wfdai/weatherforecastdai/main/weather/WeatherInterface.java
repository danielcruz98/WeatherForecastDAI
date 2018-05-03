package main.java.com.wfdai.weatherforecastdai.main.weather;

import java.io.IOException;
import javax.xml.bind.JAXBException;

/**
 * Interface weather
 *
 * @author daniel
 */
interface WeatherInterface {

    /**
     * Recolhe os dados da API externa
     *
     * @param localizacao String com a localização
     * @param weather Objecto com os dados meteorologicos recolhidos.
     * @throws JAXBException
     * @throws IOException
     */
    public void setWeather(String localizacao, Weather weather) throws JAXBException, IOException;
}
