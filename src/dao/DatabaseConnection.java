package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;


public class DatabaseConnection {

    // Ruta del archivo de configuración con las credenciales de conexión
    private static final String CONFIG_PATH = "config.properties";

    // Variables para almacenar la URL, usuario y contraseña de la base de datos
    private static String URL;
    private static String USER;
    private static String PASSWORD;

    // Bloque estático que se ejecuta una única vez al cargar la clase
    static {
        try (FileInputStream fis = new FileInputStream(CONFIG_PATH)) {
            // Crea un objeto Properties para leer las propiedades desde el archivo
            Properties props = new Properties();
            props.load(fis); // Carga las propiedades del archivo

            // Asigna los valores de conexión obtenidos del archivo
            URL = props.getProperty("db.url");
            USER = props.getProperty("db.user");
            PASSWORD = props.getProperty("db.password");

            // Verifica que no falten propiedades necesarias
            if (URL == null || USER == null || PASSWORD == null) {
                throw new IllegalArgumentException("Faltan propiedades en config.properties");
            }

        } catch (IOException e) {
            // Muestra error si no se puede leer el archivo
            e.printStackTrace();
            // Lanza error crítico para evitar que la clase se utilice sin configuración válida
            throw new ExceptionInInitializerError("Error al cargar la configuración desde config.properties");
        }
    }

    // Método público y estático para obtener una conexión a la base de datos
    public static Connection getConnection() throws SQLException {
        // Devuelve una conexión usando los datos cargados
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
