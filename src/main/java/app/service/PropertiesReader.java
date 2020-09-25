package app.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.MissingFormatArgumentException;
import java.util.Properties;

public class PropertiesReader {

    /**
     * Provides properties read from file
     * @param args - array of arguments provided during application launch
     * @return - filled properties instance
     */
    public Properties getProperties(String[] args) {
        String path = "src/main/resources/" + args[0];
        Properties properties = new Properties();
        try {
            properties.setProperty("outputPath", args[1]);
            properties.setProperty("moves", args[2]);
        } catch (IndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Insufficient arguments amount");
        }
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
