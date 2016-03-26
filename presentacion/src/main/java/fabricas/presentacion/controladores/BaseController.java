package fabricas.presentacion.controladores;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import fabricas.presentacion.VOs.ServicioVO;
import fabricas.presentacion.VOs.UsuarioVO;

@Controller
public class BaseController {


	private static final String CONTACTENOS = "contactenos";
	private static final String REGISTRO = "registro";
	private static final String CARRITO = "carrito";	
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
	
	@RequestMapping(value = "/registro/", method = RequestMethod.POST)
	public ModelAndView registro(
		@RequestParam(value="nombre", required=true) String nombre, 
		@RequestParam(value="apellido", required=true) String apellido,
		@RequestParam(value="email", required=true) String email, 
		@RequestParam(value="password", required=true) String password,
		@RequestParam(value="direccion", required=true) String direccion,
		@RequestParam(value="telefono", required=true) String telefono,
		@RequestParam(value="tipoUsuario", required=true) String tipoUsuario
		){
		
		String response = null;
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		
		String url="nombre="+nombre+",apellido="+apellido+",email="+email+",password="+password+",direccion="+direccion+
				",telefono="+telefono+",tipoUsuario="+tipoUsuario;

		String result = restTemplate.getForObject("http://localhost:8080/logica/registro/nuevo/"+url, String.class);
		try {
			response = mapper.readValue(result, new TypeReference<String>(){});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		ModelAndView modelAndView = new ModelAndView(REGISTRO);
		modelAndView.addObject("response", response);
		return modelAndView;
		
	}
	
	/**
	 * Controlador de la pantalla de el carrito de compras
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/carrito", method = RequestMethod.GET)
	public String verCarrito(ModelMap model) {
		return CARRITO;

	}
}