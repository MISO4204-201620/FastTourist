package derivador;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;
import org.codehaus.plexus.util.WriterFactory;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;

public class BinaryReplacement {

	public static void main(String args[]) {
		try {
			// Read Pom
			Model model = loadPomFile(Constantes.POM_LOCATION);
			// Add/remove dependencies
			Dependency d = new Dependency();
			d.setArtifactId("mensajeria");
			d.setGroupId("fastfactory");
			d.setVersion("0.0.1-SNAPSHOT");
			d.setType("war");
			model = removeDependency(model, d);
			// Save pom
			writePomFile(Constantes.POM_LOCATION, model);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static Model loadPomFile(String pomLocation) throws IOException,
			XmlPullParserException {

		File pomfile = new File(pomLocation);
		FileReader reader = null;
		MavenXpp3Reader mavenreader = new MavenXpp3Reader();
		reader = new FileReader(pomfile);
		Model model = mavenreader.read(reader);
		return model;
	}

	public static Model addDependency(Model model, Dependency dependency) {

		Boolean validate = Boolean.TRUE;

		// verifica que no exita aun la dependecia
		for (Dependency d : model.getDependencies()) {
			if (d.getArtifactId().equals(dependency.getArtifactId())) {
				validate = Boolean.FALSE;
				System.out.print("Ya existe la dependecia: "
						+ d.getArtifactId() + "\n");
				break;
			}
		}
		// Si no existe la agrega
		if (validate) {
			model.addDependency(dependency);
		}

		return model;
	}

	public static Model removeDependency(Model model, Dependency dependency) {
		model.removeDependency(dependency);

		for (Dependency d : model.getDependencies()) {
			if (d.getArtifactId().equals(dependency.getArtifactId())) {
				model.removeDependency(d);
				break;
			}
		}

		return model;
	}

	public static void writePomFile(String pomLocation, Model model)
			throws IOException {
		MavenXpp3Writer mavenXpp3Writer = new MavenXpp3Writer();
		File pomfile = new File(pomLocation);
		Writer fileWriter = null;
		fileWriter = WriterFactory.newXmlWriter(pomfile);
		mavenXpp3Writer.write(fileWriter, model);
	}
}
