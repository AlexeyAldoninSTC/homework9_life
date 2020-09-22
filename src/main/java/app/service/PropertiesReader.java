package app.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.MissingFormatArgumentException;
import java.util.Properties;

public class PropertiesReader {

    /**
     * Provides properties read from file
     * @param path - path to Properties file
     * @return - filled properties instance
     */
    public Properties getProperties(String path) {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(path)) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new MissingFormatArgumentException("Ошибка при загрузке параметров для запуска приложения " +
                    "из файла [" + path + "]");
        }
        return properties;
    }
}
