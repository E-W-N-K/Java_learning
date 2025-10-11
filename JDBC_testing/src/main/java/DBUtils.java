import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {
    /*
    private static String dbUrl = "jdbc:h2:mem:test;INIT=RUNSCRIPT FROM 'classpath:init.sql'"; //url для работы с бд на этом пк
    private static String dbUsername = "sa";
    private static String dbPassword = "";
    это более просто и не гибкий способ
    */
    public static Connection getConnection() {

        String dbUrl = null; // значение будем доставать из проперти-файла
        String dbUsername = "sa";
        String dbPassword = "";

        FileInputStream fis; //заберёт информацию из файли в виде потока информации
        Properties properties = new Properties();

        try {
            fis = new FileInputStream("src/main/resources/config.properties"); //получении информации из файла
            properties.load(fis);

            dbUrl = properties.getProperty("db.host"); //передаём ключ по которому забираем название

            //таким образом можно забрать все параметры по их ключам.
            // этот вариант более гибкий. чтобы менять только в конфиг файле

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e); //для отлова ошибки при получении информации из файла
        } catch (IOException e) {
            throw new RuntimeException(e); //для отлова ошибки при загрузки информации в переменную properties из fis
        }

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }
}
