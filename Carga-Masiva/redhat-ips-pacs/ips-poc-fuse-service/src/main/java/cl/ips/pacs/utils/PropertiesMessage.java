package cl.ips.pacs.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesMessage {

    public static String getProperty(String key) {

        try (InputStream input = PropertiesMessage.class.getClassLoader().getResourceAsStream("ValidationMessages.properties")) {

            Properties prop = new Properties();

            //load a properties file from class path, inside static method
            prop.load(input);

            return prop.getProperty(key);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
		return null;

    }

}