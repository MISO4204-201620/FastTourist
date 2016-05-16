package utilidades;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

	private static PropertiesLoader instance;
	private static Properties properties = new Properties();

	public PropertiesLoader() {
		load();
	}

	public static PropertiesLoader getInstance() {
		if (instance == null)
			synchronized (PropertiesLoader.class) {
				if (instance == null)
					instance = new PropertiesLoader();
			}
		return instance;
	}

	public Properties load() {
		try {
			InputStream in = Thread
					.currentThread()
					.getContextClassLoader()
					.getResourceAsStream(
							"default.properties");
			properties.load(in);

		} catch (IOException e) {
			System.out.println(e);
		}
		return properties;
	}

	public String getProperty(String prop) {
		String pro = properties.getProperty(prop);
		if (pro == null) {
			System.out.println("error cargando propiedad 2 " + prop);
		}
		return pro;
	}

}