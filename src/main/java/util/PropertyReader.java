package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    public String read(String property) throws IOException {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        InputStream stream = cl.getResourceAsStream("config.properties");
        Properties properties = new Properties();
        properties.load(stream);
        return properties.getProperty(property);
    }
}
