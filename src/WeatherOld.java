

import com.github.fedy2.weather.YahooWeatherService;
import com.github.fedy2.weather.data.Channel;
import com.github.fedy2.weather.data.unit.DegreeUnit;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBException;
import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.OpenWeatherMap;


 
public class  WeatherOld {
    //inicializar variaveis
DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");


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

    public WeatherOld() {
    }

    public WeatherOld(int direcaoVento, float velocidadeVento, int temperatura, float pressao, Date dataDados, int humidade, float visibilidade, String nascerSol, String porSol) {
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
    
    public void setWeather(String localizacao) throws JAXBException, IOException {
        
        try {
            this.setWeatherOWM(localizacao);
            
            
        } catch (NullPointerException e) {
            try {
                this.setWeatherYahoo(localizacao);
            } catch (IOException ex) {
                 System.out.println("Yahoo não disponivel: " + ex.getCause());

            } catch (JAXBException ex) {
                System.out.println("Yahoo não disponivel: " + ex.getCause());
            }
            System.out.println("OWM não disponivel: " + e.getCause());

        }
    }
    
    
    
    
     public void setWeatherOWM(String localizacao) throws JAXBException, IOException {
        boolean isMetric = true;
        OpenWeatherMap owm = new OpenWeatherMap("");   
        owm.setUnits(OpenWeatherMap.Units.METRIC);
        owm.setApiKey("07187078b07349a4c3098d15c10305af");
        owm.setLang(OpenWeatherMap.Language.PORTUGUESE);
        CurrentWeather cwd = owm.currentWeatherByCityName(localizacao);
        
        this.direcaoVento=Math.round(cwd.getWindInstance().getWindDegree());
        this.velocidadeVento=cwd.getWindInstance().getWindSpeed();
        this.temperatura=Math.round(cwd.getMainInstance().getTemperature());
        this.pressao=Math.round(cwd.getMainInstance().getPressure());
        this.dataDados=cwd.getDateTime();
        this.humidade=Math.round(cwd.getMainInstance().getHumidity());
        this.visibilidade=100-cwd.getCloudsInstance().getPercentageOfClouds();
        this.nascerSol=new SimpleDateFormat("HH:mm").format(cwd.getSysInstance().getSunriseTime());
        this.porSol=new SimpleDateFormat("HH:mm").format(cwd.getSysInstance().getSunsetTime());
        
    }
         public void setWeatherYahoo(String localizacao) throws JAXBException, IOException {
        YahooWeatherService service = new YahooWeatherService();
        List<Channel> channel;
        channel = service.getForecastForLocation(localizacao, DegreeUnit.CELSIUS).first(1);
        this.direcaoVento=channel.get(0).wind.getDirection();
        this.velocidadeVento=channel.get(0).wind.getSpeed();
        this.temperatura=channel.get(0).item.getCondition().getTemp();
        this.pressao=(float) (0.029890669 * channel.get(0).atmosphere.getPressure());
        this.dataDados=channel.get(0).item.getPubDate();
        this.humidade=channel.get(0).atmosphere.getHumidity();
        this.visibilidade=channel.get(0).atmosphere.getVisibility();
        if (channel.get(0).astronomy.getSunrise().getConvention().toString() == "AM"){
        this.nascerSol= String.format("%02d", channel.get(0).astronomy.getSunrise().getHours()) + ":"
        + String.format("%02d",channel.get(0).astronomy.getSunrise().getMinutes());
        }else {this.nascerSol= String.format("%02d",channel.get(0).astronomy.getSunrise().getHours() + 12)
        + ":" + String.format("%02d", channel.get(0).astronomy.getSunrise().getMinutes());}
        if (channel.get(0).astronomy.getSunset().getConvention().toString() == "AM"){
        this.porSol=  String.format("%02d",channel.get(0).astronomy.getSunset().getHours()) + ":"
        + String.format("%02d",channel.get(0).astronomy.getSunset().getMinutes());
        }else {this.porSol= String.format("%02d",channel.get(0).astronomy.getSunset().getHours()
        + 12) + ":" +String.format("%02d", channel.get(0).astronomy.getSunset().getMinutes());}
   
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