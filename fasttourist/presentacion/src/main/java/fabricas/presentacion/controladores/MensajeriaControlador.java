package fabricas.presentacion.controladores;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import utilidades.Constantes;
import utilidades.utilidades;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import fabricas.presentacion.VOs.MensajeriaVO;
import fabricas.presentacion.VOs.UsuarioVO;

@Controller
@RequestMapping(value = "/mensajeria")
public class MensajeriaControlador {

	private static final String VIEW_MENSAJERIA = "mensajeria";


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView mensajesRecibidos(ModelMap model) {

		// Validar si hay usuario autenticado y si es proveedor
		if (!utilidades.isUserAutenticado()) {
			ModelAndView view = new ModelAndView("redirect:/registro");
			return view;
		}

		List<MensajeriaVO> mensajes = null;
		List<UsuarioVO> usuarios = null;
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();

		// Se obtienen los servicos del proveedor ordenados por fecha de
		// creacion
		String result = restTemplate.getForObject(
				"http://localhost:8080/mensajeria/"
						+ utilidades.getSessionIdUser() + "/destinatario",
						String.class);
		try {
			mensajes = mapper.readValue(result,
					new TypeReference<List<MensajeriaVO>>() {
			});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		//Lista de usuarios para crear correo
		result = restTemplate.getForObject(
				"http://localhost:8080/logica/registro/usuarios",String.class);
		try {
			usuarios = mapper.readValue(result,
					new TypeReference<List<UsuarioVO>>() {
			});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		ModelAndView modelAndView = new ModelAndView(VIEW_MENSAJERIA);
		modelAndView.addObject("mensajes", mensajes);
		modelAndView.addObject("mensaje", new MensajeriaVO());
		modelAndView.addObject("usuarioAutenticado",
				utilidades.getSessionUser());
		modelAndView.addObject("usuarios", usuarios);
		modelAndView.addObject("perfil",utilidades.getPerfil());
		modelAndView.addObject("moduloBusquedas", Constantes.MODULO_BUSQUEDAS);

		return modelAndView;
	}

	@RequestMapping(value = "/enviados", method = RequestMethod.GET)
	public ModelAndView mensajesEnviados(ModelMap model) {

		// Validar si hay usuario autenticado y si es proveedor
		if (!utilidades.isUserAutenticado()) {
			ModelAndView view = new ModelAndView("redirect:/registro");
			return view;
		}

		List<MensajeriaVO> mensajes = null;
		List<UsuarioVO> usuarios = null;
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();

		// Se obtienen los servicos del proveedor ordenados por fecha de
		// creacion
		String result = restTemplate.getForObject(
				"http://localhost:8080/mensajeria/"
						+ utilidades.getSessionIdUser() + "/remitente",
						String.class);
		try {
			mensajes = mapper.readValue(result,
					new TypeReference<List<MensajeriaVO>>() {
			});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		//Lista de usuarios para crear correo
		result = restTemplate.getForObject(
				"http://localhost:8080/logica/registro/usuarios",String.class);
		try {
			usuarios = mapper.readValue(result,
					new TypeReference<List<UsuarioVO>>() {
			});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		ModelAndView modelAndView = new ModelAndView(VIEW_MENSAJERIA);
		modelAndView.addObject("mensajes", mensajes);
		modelAndView.addObject("mensaje", new MensajeriaVO());
		modelAndView.addObject("remitente", true);
		modelAndView.addObject("usuarios", usuarios);
		modelAndView.addObject("usuarioAutenticado",
				utilidades.getSessionUser());
		modelAndView.addObject("perfil",utilidades.getPerfil());
		modelAndView.addObject("moduloBusquedas", Constantes.MODULO_BUSQUEDAS);
		return modelAndView;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView mensajesRecibidos(@ModelAttribute("mensaje") MensajeriaVO mensaje) {

		// Validar si hay usuario autenticado y si es proveedor
		if (!utilidades.isUserAutenticado()) {
			ModelAndView view = new ModelAndView("redirect:/registro");
			return view;
		}

		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		
		UsuarioVO usuario = new UsuarioVO();
		usuario.setIdusuario(utilidades.getSessionIdUser());
		mensaje.setRemitente(usuario);

		// Se obtienen los servicos del proveedor ordenados por fecha de
		// creacion
		String uri = "http://localhost:8080/mensajeria/";
		String result = (String) restTemplate.postForObject(uri,mensaje,String.class);
		try {
			mensaje = mapper.readValue(result,
					new TypeReference<MensajeriaVO>() {
			});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return new ModelAndView("redirect:/mensajeria/enviados");
	}
}