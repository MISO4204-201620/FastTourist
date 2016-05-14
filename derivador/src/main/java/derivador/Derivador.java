package derivador;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.Plugin;
import org.codehaus.plexus.util.xml.Xpp3Dom;
import org.codehaus.plexus.util.xml.Xpp3DomBuilder;

public class Derivador {

	static List<String> features = null;
	static Properties properties = null;

	public static void main(String args[]) {

		System.out.println("******************************************************");
		System.out.println("***Iniciando la generacion del producto FastTourist***");
		System.out.println("******************************************************\n");
		
		ConfigLoader configuracion = new ConfigLoader();
		
		try {
			System.out.println("Paso1. Se obtienen los features de la configuracion\n");
			features = configuracion.cargarConfiguracion();
			System.out.println("Paso2. Se genera el producto");
			productGenerator();
			System.out.println("Paso3. Se escribe el archivo de propiedades\n");
			PropertiesWritter.cargarConfiguracion(properties);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("*****************************************************");
		System.out.println("******Producto FastTourist generado exitosamente*****");
		System.out.println("*****************************************************");
	}

	public static void productGenerator() {

		properties = new Properties();
		System.out.println("Paso 2.1. Se agrega o remueve del producto el feature busqueda personalizada");
		busquedaFeature();
		System.out.println("Paso 2.2. Se agrega o remueve del producto el feature mensajeria");
		mensajeriaFeature();
		System.out.println("Paso 2.3. Se agrega o remueve del producto el feature reportes");
		reportesFeature();
		System.out.println("Paso 2.4. Se agrega o remueve del producto el feature calificaciones\n");
		calificacionesFeature();

	}

	/**
	 * Metodo para aplicar variabilidad con el feature de busqueda personalizada
	 * tipo de variabilidad usada: Aspectos
	 **/
	private static void busquedaFeature() {
		
		Model model = null;
		Plugin plugin = null;
		try {
			model = BinaryReplacement.loadPomFile(Constantes.RUTA_REPO_LOCAL + Constantes.POM_LOCATION_PRESENTACION);
			//Create Plugin
			plugin = new Plugin();
			plugin.setGroupId("org.codehaus.mojo");
			plugin.setArtifactId("aspectj-maven-plugin");
			String pluginConfig=
							"<configuration>"  
								+ "<complianceLevel>1.7</complianceLevel>" 
								+ "<aspectLibraries>"
						          +  "<aspectLibrary>"
						          	+ "<groupId>fastfactory</groupId>"
						            + "<artifactId>aspectos</artifactId>"
						          + "</aspectLibrary>"
						        + "</aspectLibraries>"
						   +"</configuration>";
			
			 Xpp3Dom dom=Xpp3DomBuilder.build(new ByteArrayInputStream(pluginConfig.getBytes()),"UTF-8");
			plugin.setConfiguration(dom);
		} catch (Exception e) {
			System.out.println(e);
		}
		

		// Metodo: Aspectos
		if (features.contains(Constantes.MODULO_BUSQUEDA_PERSONALIZADA)) {
			// Add Plugin
			model = BinaryReplacement.addPlugin(model, plugin);
			properties.setProperty(Constantes.MODULO_BUSQUEDA_PERSONALIZADA,
					"true");
		} else {
			// Remove Plugin
			model = BinaryReplacement.removePlugin(model, plugin);
			properties.setProperty(Constantes.MODULO_BUSQUEDA_PERSONALIZADA,
					"false");
		}
		// Save pom
		try {
			BinaryReplacement.writePomFile(Constantes.RUTA_REPO_LOCAL +Constantes.POM_LOCATION_PRESENTACION, model);
		} catch (IOException e) {
			System.out.println(e);
		}

	}

	/**
	 * Metodo para aplicar variabilidad con el feature de mensajeria tipo de
	 * variabilidad usada: servicio Rest - BinaryReplacement
	 **/
	private static void mensajeriaFeature() {
		// Read Pom
		Model model = null;
		try {
			model = BinaryReplacement.loadPomFile(Constantes.RUTA_REPO_LOCAL + Constantes.POM_LOCATION);
		} catch (Exception e) {
			System.out.println(e);
		}

		// Create Dependency
		Dependency dependency = new Dependency();
		dependency.setArtifactId("mensajeria");
		dependency.setGroupId("fastfactory");
		dependency.setVersion("0.0.1-SNAPSHOT");
		dependency.setType("war");

		if (features.contains(Constantes.MODULO_MENSAJERIA)) {
			// Add Dependency
			model = BinaryReplacement.addDependency(model, dependency);
			properties.setProperty(Constantes.MODULO_MENSAJERIA, "true");
		} else {
			// Remove Dependency
			model = BinaryReplacement.removeDependency(model, dependency);
			properties.setProperty(Constantes.MODULO_MENSAJERIA, "false");
		}
		// Save pom
		try {
			BinaryReplacement.writePomFile(Constantes.RUTA_REPO_LOCAL +Constantes.POM_LOCATION, model);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Metodo para aplicar variabilidad con el feature de reportes tipo de
	 * variabilidad usada: Patron- Decorator
	 **/
	private static void reportesFeature() {
		// Metodo:Patron
		if (features.contains(Constantes.MODULO_REPORTES)) {
			properties.setProperty(Constantes.MODULO_REPORTES, "true");
		} else {
			properties.setProperty(Constantes.MODULO_REPORTES, "false");
		}
	}

	/**
	 * Metodo para aplicar variabilidad con el feature de calificaciones tipo de
	 * variabilidad usada: Patron - xxxxx
	 **/
	private static void calificacionesFeature() {

		// Metodo: Patron
		if (features.contains(Constantes.MODULO_CALIFICACIONES)) {
			properties.setProperty(Constantes.MODULO_CALIFICACIONES, "true");
		} else {
			properties.setProperty(Constantes.MODULO_CALIFICACIONES, "false");
		}

	}
}
