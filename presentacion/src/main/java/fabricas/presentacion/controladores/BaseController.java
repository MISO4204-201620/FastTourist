package fabricas.presentacion.controladores;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BaseController {


	private static final String CONTACTENOS = "contactenos";
	private static final String REGISTRO = "registro";
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(BaseController.class);

	
	/**
	 * Controlador de la pantalla Contactenos
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/contactenos", method = RequestMethod.GET)
	public String contactenos(ModelMap model) {
		return CONTACTENOS;
	}
	
	/**
	 * Controlador de la pantalla de Registro
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/registro", method = RequestMethod.GET)
	public String registrarse(ModelMap model) {
		return REGISTRO;

	}
}