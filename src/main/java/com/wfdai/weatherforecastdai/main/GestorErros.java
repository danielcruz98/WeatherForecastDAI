package main.java.com.wfdai.weatherforecastdai.main;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class GestorErros {

    String erro;
    ArrayList<String> erros;
    ArrayList<Date> time;

    public GestorErros() {
        this.erros = new ArrayList<String>();
        this.time = new ArrayList<Date>();
    }

    public GestorErros(String erro) {
        this.erro = erro;
    }

    Parser parser = new Parser();
    Publisher publish = new Publisher();

    public void putErro(String erro) {
        try {
            MysqlDataSource dataSource = new MysqlDataSource();
            DataBase database = new DataBase();
            dataSource.setUser(database.getUser());
            dataSource.setPassword(database.getPassword());
            dataSource.setServerName(database.getServerName());
            Connection conn = dataSource.getConnection();
            Statement st = conn.createStatement();
            if (!erro.isEmpty()) {
                st.executeUpdate("INSERT INTO mydb.Erros (`erro`)"
                        + "VALUES ('" + erro + "')");
            }

            conn.close();

        } catch (SQLException e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    public void getErro() {
        try {
            MysqlDataSource dataSource = new MysqlDataSource();
            DataBase database = new DataBase();
            dataSource.setUser(database.getUser());
            dataSource.setPassword(database.getPassword());
            dataSource.setServerName(database.getServerName());
            Connection conn = dataSource.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("Select * from mydb.Erros ");

            while (rs.next()) {
                erros.add(rs.getString("erro"));
                LocalDate datePart = LocalDate.parse(rs.getDate("time").toString());
                LocalTime timePart = LocalTime.parse(rs.getTime("time").toString());
                LocalDateTime dt = LocalDateTime.of(datePart, timePart);
                Date data = java.sql.Timestamp.valueOf(dt);
                time.add(data);

            }
            conn.close();
            parser.setParser(this);
            String mensagem = parser.getParsedMessage();
            publish.publish("/Erros", mensagem);
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    public ArrayList<String> getErros() {
        return erros;
    }

    public ArrayList<Date> getTime() {
        return time;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

}
