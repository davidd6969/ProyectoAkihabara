package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {

    // Ruta del archivo de configuración
    private static final String CONFIG_PATH = "config.properties";

    // Objeto Properties que almacenará las configuraciones cargadas
    private Properties props;

    // Constructor: carga las propiedades desde el archivo al crear una instancia
    public ConfigLoader() {
        props = new Properties(); // Se inicializa el objeto Properties
        try (FileInputStream fis = new FileInputStream(CONFIG_PATH)) {
            props.load(fis); // Se cargan las propiedades desde el archivo
        } catch (IOException e) {
            // Si ocurre un error al leer el archivo, se muestra en consola
            System.err.println("Error cargando configuración: " + e.getMessage());
        }
    }

    // Método que devuelve el valor de la clave "api_key" desde las propiedades
    public String getApiKey() {
        return props.getProperty("api_key");
    }

    // Método que devuelve el valor de la clave "model" desde las propiedades
    public String getModel() {
        return props.getProperty("model");
    }
}
