package main.java.com.wfdai.weatherforecastdai.main;

import main.java.com.wfdai.weatherforecastdai.main.KPI.KPI;
import java.io.IOException;
import javax.xml.bind.JAXBException;
import main.java.com.wfdai.weatherforecastdai.main.KPI.RegistoKPI;
import main.java.com.wfdai.weatherforecastdai.main.weather.Weather;
import main.java.com.wfdai.weatherforecastdai.main.weather.WeatherFactory;

/**
 * Classe responsavel por instanciar o ETL
 *
 * @author daniel
 */
public class App {

    String[] localidades = {"Vila Velha de Ródão,Castelo Branco,Portugal", "Mação ,Santarém,Portugal", "Sines,Setúbal,Portugal", "Arcos de Valdevez,Viana do Castelo,Portugal", "Caminha,Viana do Castelo,Portugal", "Melgaço,Viana do Castelo,Portugal", "Monção,Viana do Castelo,Portugal", "Paredes de Coura,Viana do Castelo,Portugal", "Ponte da Barca,Viana do Castelo,Portugal", "Ponte de Lima,Viana do Castelo,Portugal", "Valença,Viana do Castelo,Portugal", "Viana do Castelo,Viana do Castelo,Portugal", "Vila Nova de Cerveira,Viana do Castelo,Portugal", "Amares,Braga,Portugal", "Barcelos,Braga,Portugal", "Braga,Braga,Portugal", "Esposende,Braga,Portugal", "Terras de Bouro,Braga,Portugal", "Vila Verde,Braga,Portugal", "Cabeceiras de Basto,Braga,Portugal", "Fafe,Braga,Portugal", "Guimarães,Braga,Portugal", "Mondim de Basto,Vila Real,Portugal", "Póvoa de Lanhoso,Braga,Portugal", "Vieira do Minho,Braga,Portugal", "Vila Nova de Famalicão,Braga,Portugal", "Vizela,Braga,Portugal", "Arouca,Aveiro,Portugal", "Espinho,Aveiro,Portugal", "Gondomar,Porto,Portugal", "Maia,Porto,Portugal", "Matosinhos,Porto,Portugal", "Oliveira de Azeméis,Aveiro,Portugal", "Paredes,Porto,Portugal", "Porto,Porto,Portugal", "Póvoa de Varzim,Porto,Portugal", "Santa Maria da Feira,Aveiro,Portugal", "Santo Tirso,Porto,Portugal", "São João da Madeira,Aveiro,Portugal", "Trofa,Porto,Portugal", "Vale de Cambra,Aveiro,Portugal", "Valongo,Porto,Portugal", "Vila do Conde,Porto,Portugal", "Vila Nova de Gaia,Porto,Portugal", "Amarante,Porto,Portugal", "Baião,Porto,Portugal", "Castelo de Paiva,Aveiro,Portugal", "Celorico de Basto,Braga,Portugal", "Cinfães,Viseu,Portugal", "Felgueiras,Porto,Portugal", "Lousada,Porto,Portugal", "Marco de Canaveses,Porto,Portugal", "Paços de Ferreira,Porto,Portugal", "Penafiel,Porto,Portugal", "Resende,Viseu,Portugal", "Boticas,Vila Real,Portugal", "Chaves,Vila Real,Portugal", "Montalegre,Vila Real,Portugal", "Ribeira de Pena,Vila Real,Portugal", "Valpaços,Vila Real,Portugal", "Vila Pouca de Aguiar,Vila Real,Portugal", "Alijó,Vila Real,Portugal", "Armamar,Viseu,Portugal", "Carrazeda de Ansiães,Bragança,Portugal", "Freixo de Espada à Cinta,Bragança,Portugal", "Lamego,Viseu,Portugal", "Mesão Frio,Vila Real,Portugal", "Moimenta da Beira,Viseu,Portugal", "Murça,Vila Real,Portugal", "Penedono,Viseu,Portugal", "Peso da Régua,Vila Real,Portugal", "Sabrosa,Vila Real,Portugal", "Santa Marta de Penaguião,Vila Real,Portugal", "São João da Pesqueira,Viseu,Portugal", "Sernancelhe,Viseu,Portugal", "Tabuaço,Viseu,Portugal", "Tarouca,Viseu,Portugal", "Torre de Moncorvo,Bragança,Portugal", "Vila Nova de Foz Côa,Guarda,Portugal", "Vila Real,Vila Real,Portugal", "Alfândega da Fé,Bragança,Portugal", "Bragança,Bragança,Portugal", "Macedo de Cavaleiros,Bragança,Portugal", "Miranda do Douro,Bragança,Portugal", "Mirandela,Bragança,Portugal", "Mogadouro,Bragança,Portugal", "Vila Flor,Bragança,Portugal", "Vimioso,Bragança,Portugal", "Vinhais,Bragança,Portugal", "Águeda,Aveiro,Portugal", "Albergaria-a-Velha,Aveiro,Portugal", "Anadia,Aveiro,Portugal", "Aveiro,Aveiro,Portugal", "Estarreja,Aveiro,Portugal", "Ílhavo,Aveiro,Portugal", "Murtosa,Aveiro,Portugal", "Oliveira do Bairro,Aveiro,Portugal", "Ovar,Aveiro,Portugal", "Sever do Vouga,Aveiro,Portugal", "Vagos,Aveiro,Portugal", "Arganil,Coimbra,Portugal", "Cantanhede,Coimbra,Portugal", "Coimbra,Coimbra,Portugal", "Condeixa-a-Nova,Coimbra,Portugal", "Figueira da Foz,Coimbra,Portugal", "Góis,Coimbra,Portugal", "Lousã,Coimbra,Portugal", "Mealhada,Aveiro,Portugal", "Mira,Coimbra,Portugal", "Miranda do Corvo,Coimbra,Portugal", "Montemor-o-Velho,Coimbra,Portugal", "Mortágua,Viseu,Portugal", "Oliveira do Hospital,Coimbra,Portugal", "Pampilhosa da Serra,Coimbra,Portugal", "Penacova,Coimbra,Portugal", "Penela,Coimbra,Portugal", "Soure,Coimbra,Portugal", "Tábua,Coimbra,Portugal", "Vila Nova de Poiares,Coimbra,Portugal", "Batalha,Leiria,Portugal", "Leiria,Leiria,Portugal", "Marinha Grande,Leiria,Portugal", "Pombal,Leiria,Portugal", "Porto de Mós,Leiria,Portugal", "Alvaiázere,Leiria,Portugal", "Ansião,Leiria,Portugal", "Castanheira de Pera,Leiria,Portugal", "Figueiró dos Vinhos,Leiria,Portugal", "Pedrógão Grande,Leiria,Portugal", "Aguiar da Beira,Guarda,Portugal", "Carregal do Sal,Viseu,Portugal", "Castro Daire,Viseu,Portugal", "Mangualde,Viseu,Portugal", "Nelas,Viseu,Portugal", "Oliveira de Frades,Viseu,Portugal", "Penalva do Castelo,Viseu,Portugal", "Santa Comba Dão,Viseu,Portugal", "São Pedro do Sul,Viseu,Portugal", "Sátão,Viseu,Portugal", "Tondela,Viseu,Portugal", "Vila Nova de Paiva,Viseu,Portugal", "Viseu,Viseu,Portugal", "Vouzela,Viseu,Portugal", "Almeida,Guarda,Portugal", "Belmonte,Castelo Branco,Portugal", "Celorico da Beira,Guarda,Portugal", "Covilhã,Castelo Branco,Portugal", "Figueira de Castelo Rodrigo,Guarda,Portugal", "Fornos de Algodres,Guarda,Portugal", "Fundão,Castelo Branco,Portugal", "Gouveia,Guarda,Portugal", "Guarda,Guarda,Portugal", "Manteigas,Guarda,Portugal", "Mêda,Guarda,Portugal", "Pinhel,Guarda,Portugal", "Sabugal,Guarda,Portugal", "Seia,Guarda,Portugal", "Trancoso,Guarda,Portugal", "Castelo Branco,Castelo Branco,Portugal", "Idanha-a-Nova,Castelo Branco,Portugal", "Oleiros,Castelo Branco,Portugal", "Penamacor,Castelo Branco,Portugal", "Proença-a-Nova,Castelo Branco,Portugal", "Alcobaça,Leiria,Portugal", "Alenquer,Lisboa,Portugal", "Arruda dos Vinhos,Lisboa,Portugal", "Bombarral,Leiria,Portugal", "Cadaval,Lisboa,Portugal", "Caldas da Rainha,Leiria,Portugal", "Lourinhã,Lisboa,Portugal", "Nazaré,Leiria,Portugal", "Óbidos,Leiria,Portugal", "Peniche,Leiria,Portugal", "Sobral de Monte Agraço,Lisboa,Portugal", "Torres Vedras,Lisboa,Portugal", "Abrantes,Santarém,Portugal", "Alcanena,Santarém,Portugal", "Constância,Santarém,Portugal", "Entroncamento,Santarém,Portugal", "Ferreira do Zêzere,Santarém,Portugal", "Ourém,Santarém,Portugal", "Sardoal,Santarém,Portugal", "Sertã,Castelo Branco,Portugal", "Tomar,Santarém,Portugal", "Torres Novas,Santarém,Portugal", "Vila de Rei,Castelo Branco,Portugal", "Vila Nova da Barquinha,Santarém,Portugal", "Alcochete,Setúbal,Portugal", "Almada,Setúbal,Portugal", "Amadora,Lisboa,Portugal", "Barreiro,Setúbal,Portugal", "Cascais,Lisboa,Portugal", "Lisboa,Lisboa,Portugal", "Loures,Lisboa,Portugal", "Mafra,Lisboa,Portugal", "Moita,Setúbal,Portugal", "Montijo,Setúbal,Portugal", "Odivelas,Lisboa,Portugal", "Oeiras,Lisboa,Portugal", "Palmela,Setúbal,Portugal", "Seixal,Setúbal,Portugal", "Sesimbra,Setúbal,Portugal", "Setúbal,Setúbal,Portugal", "Sintra,Lisboa,Portugal", "Vila Franca de Xira,Lisboa,Portugal", "Almeirim,Santarém,Portugal", "Alpiarça,Santarém,Portugal", "Azambuja,Lisboa,Portugal", "Benavente,Santarém,Portugal", "Cartaxo,Santarém,Portugal", "Chamusca,Santarém,Portugal", "Coruche,Santarém,Portugal", "Golegã,Santarém,Portugal", "Rio Maior,Santarém,Portugal", "Salvaterra de Magos,Santarém,Portugal", "Santarém,Santarém,Portugal", "Alcácer do Sal,Setúbal,Portugal", "Grândola,Setúbal,Portugal", "Odemira,Beja,Portugal", "Santiago do Cacém,Setúbal,Portugal", "Alter do Chão,Portalegre,Portugal", "Arronches,Portalegre,Portugal", "Avis,Portalegre,Portugal", "Campo Maior,Portalegre,Portugal", "Castelo de Vide,Portalegre,Portugal", "Crato,Portalegre,Portugal", "Elvas,Portalegre,Portugal", "Fronteira,Portalegre,Portugal", "Gavião,Portalegre,Portugal", "Marvão,Portalegre,Portugal", "Monforte,Portalegre,Portugal", "Nisa,Portalegre,Portugal", "Ponte de Sor,Portalegre,Portugal", "Portalegre,Portalegre,Portugal", "Sousel,Portalegre,Portugal", "Alandroal,Évora,Portugal", "Arraiolos,Évora,Portugal", "Borba,Évora,Portugal", "Estremoz,Évora,Portugal", "Évora,Évora,Portugal", "Montemor-o-Novo,Évora,Portugal", "Mora,Évora,Portugal", "Mourão,Évora,Portugal", "Portel,Évora,Portugal", "Redondo,Évora,Portugal", "Reguengos de Monsaraz,Évora,Portugal", "Vendas Novas,Évora,Portugal", "Viana do Alentejo,Évora,Portugal", "Vila Viçosa,Évora,Portugal", "Aljustrel,Beja,Portugal", "Almodôvar,Beja,Portugal", "Alvito,Beja,Portugal", "Barrancos,Beja,Portugal", "Beja,Beja,Portugal", "Castro Verde,Beja,Portugal", "Cuba,Beja,Portugal", "Ferreira do Alentejo,Beja,Portugal", "Mértola,Beja,Portugal", "Moura,Beja,Portugal", "Ourique,Beja,Portugal", "Serpa,Beja,Portugal", "Vidigueira,Beja,Portugal", "Albufeira,Faro,Portugal", "Alcoutim,Faro,Portugal", "Aljezur,Faro,Portugal", "Castro Marim,Faro,Portugal", "Faro,Faro,Portugal", "Lagoa,Faro,Portugal", "Lagos,Faro,Portugal", "Loulé,Faro,Portugal", "Monchique,Faro,Portugal", "Olhão,Faro,Portugal", "Portimão,Faro,Portugal", "São Brás de Alportel,Faro,Portugal", "Silves,Faro,Portugal", "Tavira,Faro,Portugal", "Vila do Bispo,Faro,Portugal", "Vila Real de Santo António,Faro,Portugal", "Angra do Heroísmo,Terceira,Portugal", "Calheta,São Jorge,Portugal", "Corvo,Corvo,Portugal", "Horta,Faial,Portugal", "Lagoa,São Miguel,Portugal", "Lajes das Flores,Flores,Portugal", "Lajes do Pico,Pico,Portugal", "Madalena,Pico,Portugal", "Nordeste,São Miguel,Portugal", "Ponta Delgada,São Miguel,Portugal", "Povoação,São Miguel,Portugal", "Praia da Vitória,Terceira,Portugal", "Ribeira Grande,São Miguel,Portugal", "Santa Cruz da Graciosa,Graciosa,Portugal", "Santa Cruz das Flores,Flores,Portugal", "São Roque do Pico,Pico,Portugal", "Velas,São Jorge,Portugal", "Vila do Porto,Santa Maria,Portugal", "Vila Franca do Campo,São Miguel,Portugal", "Calheta,Madeira,Portugal", "Câmara de Lobos,Madeira,Portugal", "Funchal,Madeira,Portugal", "Machico,Madeira,Portugal", "Ponta do Sol,Madeira,Portugal", "Porto Moniz,Madeira,Portugal", "Porto Santo,Porto Santo,Portugal", "Ribeira Brava,Madeira,Portugal", "Santa Cruz,Madeira,Portugal", "Santana,Madeira,Portugal", "São Vicente,Madeira,Portugal"};
    Weather weather = new Weather();
    WeatherFactory weatherFactory = new WeatherFactory();
    Parser parser = new Parser();
    Historico historico = new Historico();
    Publisher publisher = new Publisher();
    Alerta alerta = new Alerta();
    KPI kpi = new KPI();
    RegistoKPI registoKPI = new RegistoKPI();

    public App() {

    }

    /**
     * Corre todas as localidades, recolhe os dados metereologicos, verifica se
     * existem alertas, e envia a mensagem para o Broker
     *
     * @throws javax.xml.bind.JAXBException
     * @throws java.io.IOException
     */
    public void cicloLocalidades() throws JAXBException, IOException {
        for (String localidade : localidades) {
            weatherFactory.setWeather(localidade, weather);
            alerta.checkAlerta(weather, localidade);
            parser.setParser(weather);
            String mensagem = parser.getParsedMessage();
            publisher.publish(localidade, mensagem);
        }
    }

    /**
     * Corre todas as localidades, recolhe os dados metereologicos, guarda os
     * dados na BD, recolhe os dados da BD, e envia o Histórico atualizado para
     * o Broker
     *
     * @throws javax.xml.bind.JAXBException
     * @throws java.io.IOException
     */
    public void cicloHistorico() throws JAXBException, IOException {
        for (String localidade : localidades) {
            weatherFactory.setWeather(localidade, weather);
            historico.putHistorico(weather, localidade);
            historico.getHistorico(localidade);
            parser.setParser(historico);
            String mensagem = parser.getParsedMessage();
            publisher.publish(localidade + "/historico", mensagem);
        }
    }

    /**
     * Recebe os KPI´s, regista-os na BD, recolhe os KPI´s da BD, e envia os
     * KPI's atualizados para o Broker
     *
     * @throws java.lang.InterruptedException
     */
    public void cicloKpi() throws InterruptedException {
        kpi.getKPI();
        registoKPI.putKPI(kpi);
        registoKPI.getRegistoKPI();
        parser.setParser(registoKPI);
        String mensagem = parser.getParsedMessage();
        publisher.publish("/kpi", mensagem);
    }
}
