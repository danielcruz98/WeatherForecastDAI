package main.java.com.wfdai.weatherforecastdai.main.weather;

import java.io.IOException;
import java.util.Date;
import javax.xml.bind.JAXBException;


interface WeatherInterface {



    public void setWeather(String localizacao, Weather weather) throws JAXBException, IOException;


}
