/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.wfdai.weatherforecastdai.main.weather;

import com.github.fedy2.weather.YahooWeatherService;
import com.github.fedy2.weather.data.Channel;
import com.github.fedy2.weather.data.unit.DegreeUnit;
import java.io.IOException;
import java.util.List;
import javax.xml.bind.JAXBException;

/**
 *
 * @author daniel
 */
public class WeatherYahoo {

    public void setWeatherYahoo(String localizacao, Weather weather) throws JAXBException, IOException {
        YahooWeatherService service = new YahooWeatherService();
        List<Channel> channel;
        channel = service.getForecastForLocation(localizacao, DegreeUnit.CELSIUS).first(1);
        weather.direcaoVento = channel.get(0).wind.getDirection();
        weather.velocidadeVento = channel.get(0).wind.getSpeed();
        weather.temperatura = channel.get(0).item.getCondition().getTemp();
        weather.pressao = (float) (0.029890669 * channel.get(0).atmosphere.getPressure());
        weather.dataDados = channel.get(0).item.getPubDate();
        weather.humidade = channel.get(0).atmosphere.getHumidity();
        weather.visibilidade = channel.get(0).atmosphere.getVisibility();
        if ("AM".equals(channel.get(0).astronomy.getSunrise().getConvention().toString())) {
            weather.nascerSol = String.format("%02d", channel.get(0).astronomy.getSunrise().getHours()) + ":"
                    + String.format("%02d", channel.get(0).astronomy.getSunrise().getMinutes());
        } else {
            weather.nascerSol = String.format("%02d", channel.get(0).astronomy.getSunrise().getHours() + 12)
                    + ":" + String.format("%02d", channel.get(0).astronomy.getSunrise().getMinutes());
        }
        if ("AM".equals(channel.get(0).astronomy.getSunset().getConvention().toString())) {
            weather.porSol = String.format("%02d", channel.get(0).astronomy.getSunset().getHours()) + ":"
                    + String.format("%02d", channel.get(0).astronomy.getSunset().getMinutes());
        } else {
            weather.porSol = String.format("%02d", channel.get(0).astronomy.getSunset().getHours()
                    + 12) + ":" + String.format("%02d", channel.get(0).astronomy.getSunset().getMinutes());
        }

    }
}
