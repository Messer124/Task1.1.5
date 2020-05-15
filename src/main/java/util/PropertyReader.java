package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    public String read(String property) throws IOException {
        ClassLoader classLoaderl = Thread.currentThread().getContextClassLoader();
        InputStream stream = classLoaderl.getResourceAsStream("config.properties");
        Properties properties = new Properties();
        properties.load(stream);
        return properties.getProperty(property);
    }
}
