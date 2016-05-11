package utilidades;

public class Constantes {
	public static PropertiesLoader properties = PropertiesLoader.getInstance();
	
	public static final String MODULO_MENSAJERIA = properties.getProperty("Mensajeria");
	public static final String MODULO_REPORTES = properties.getProperty("ModuloReportes");
	public static final String MODULO_CALIFICACIONES = properties.getProperty("CalificarServivio");
	public static final String MODULO_BUSQUEDAS = properties.getProperty("ModuloBusquedaPersonalizada");
}
