package fabricas.presentacion.controladores;

import java.util.List;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import utilidades.Constantes;
import utilidades.EnumPerfiles;
import utilidades.utilidades;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import fabricas.presentacion.VOs.ServicioVO;
import fabricas.presentacion.VOs.UsuarioVO;

@Controller
@RequestMapping(value = "/adminProveedor")
public class CrudControlador {

	private static final String VIEW_PROVEEDOR_SERVICIOS = "proveedorServicios";
	private static final String VIEW_PROVEEDOR_EDITAR_SERVICIO = "editarServicio";
	private static final String VIEW_PROVEEDOR_CREAR_SERVICIOS = "crearServicios";


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView indexProveedor(ModelMap model) {

		//Validar si hay usuario autenticado y si es proveedor
		if(!utilidades.isUserAutenticado() || 
				utilidades.getPerfil()!=EnumPerfiles.PROVEEDOR.getValue()){
			ModelAndView view=new ModelAndView("redirect:/registro");
			return view;
		}


		List<ServicioVO> servicios=null;
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();


		//Se obtienen los servicos del proveedor ordenados por fecha de creacion
		String result = restTemplate.getForObject("http://localhost:8080/logica/servicios/getByProvider/"+utilidades.getSessionIdUser()+"/", String.class);
		try {
			servicios = mapper.readValue(result, new TypeReference<List<ServicioVO>>(){});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		ModelAndView modelAndView = new ModelAndView(VIEW_PROVEEDOR_SERVICIOS);
		modelAndView.addObject("servicios", servicios);
		modelAndView.addObject("usuarioAutenticado",utilidades.getSessionUser());
		modelAndView.addObject("moduloMensajeria", Constantes.MODULO_MENSAJERIA);
		return modelAndView;
	}

	@RequestMapping(value = "/edit/{id}/", method = RequestMethod.GET)
	public ModelAndView editAlojamiento(@PathVariable("id")int id, ModelMap model) {

		List<ServicioVO> servicios=null;
		ServicioVO servicio=null;
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();

		//Validar si hay usuario autenticado y si es proveedor
		if(!utilidades.isUserAutenticado() || 
				utilidades.getPerfil()!=EnumPerfiles.PROVEEDOR.getValue()){
			ModelAndView view=new ModelAndView("redirect:/registro");
			return view;
		}

		String result = restTemplate.getForObject("http://localhost:8080/logica/servicios/get/" + id+"/", String.class);
		try {
			servicio = mapper.readValue(result, ServicioVO.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		result = restTemplate.getForObject("http://localhost:8080/logica/servicios/getByProvider/"+utilidades.getSessionIdUser()+"/", String.class);
		try {
			servicios = mapper.readValue(result, new TypeReference<List<ServicioVO>>(){});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		ModelAndView modelAndView= new ModelAndView(VIEW_PROVEEDOR_EDITAR_SERVICIO);
		modelAndView.addObject("servicio", servicio);
		modelAndView.addObject("servicios", servicios);
		modelAndView.addObject("usuarioAutenticado",utilidades.getSessionUser());
		modelAndView.addObject("moduloMensajeria", Constantes.MODULO_MENSAJERIA);
		return modelAndView;
	}

	@RequestMapping(value = "/edit/{id}/", method = RequestMethod.POST)
	public ModelAndView editAlojamiento(@PathVariable("id")int id,@ModelAttribute("servicio") ServicioVO servicio) {
		List<ServicioVO> servicios=null;
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

		servicio.setIdservicios(id);

		String uri = "http://localhost:8080/logica/servicios/edit/";
		String result = (String) restTemplate.postForObject(uri,servicio, String.class);
		try {
			servicio = mapper.readValue(result, ServicioVO.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		result = restTemplate.getForObject("http://localhost:8080/logica/servicios/getByProvider/"+utilidades.getSessionIdUser()+"/", String.class);
		try {
			servicios = mapper.readValue(result, new TypeReference<List<ServicioVO>>(){});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		ModelAndView modelAndView= new ModelAndView(VIEW_PROVEEDOR_EDITAR_SERVICIO);
		modelAndView.addObject("servicio", servicio);
		modelAndView.addObject("servicios", servicios);
		modelAndView.addObject("editExitoso",true);
		modelAndView.addObject("usuarioAutenticado",utilidades.getSessionUser());
		modelAndView.addObject("moduloMensajeria", Constantes.MODULO_MENSAJERIA);
		return modelAndView;
	}

	@RequestMapping(value = "/crear/", method = RequestMethod.GET)
	public ModelAndView crearAlojamiento(ModelMap model) {
		ServicioVO servicio = new ServicioVO();
		List<ServicioVO> servicios=null;
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();

		//Validar si hay usuario autenticado y si es proveedor
		if(!utilidades.isUserAutenticado() || 
				utilidades.getPerfil()!=EnumPerfiles.PROVEEDOR.getValue()){
			ModelAndView view=new ModelAndView("redirect:/registro");
			return view;
		}

		String result = restTemplate.getForObject("http://localhost:8080/logica/servicios/getByProvider/"+utilidades.getSessionIdUser()+"/", String.class);
		try {
			servicios = mapper.readValue(result, new TypeReference<List<ServicioVO>>(){});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		ModelAndView modelAndView = new ModelAndView(VIEW_PROVEEDOR_CREAR_SERVICIOS);
		modelAndView.addObject("servicio", servicio);
		modelAndView.addObject("servicios", servicios);
		modelAndView.addObject("usuarioAutenticado",utilidades.getSessionUser());
		modelAndView.addObject("moduloMensajeria", Constantes.MODULO_MENSAJERIA);
		return modelAndView;
	}

	@RequestMapping(value = "/crear/", method = RequestMethod.POST)
	public ModelAndView crearAlojamiento(@ModelAttribute("servicio") ServicioVO servicio) {

		List<ServicioVO> servicios=null;
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());


		UsuarioVO usuarioVO = new UsuarioVO();
		usuarioVO.setIdusuario(utilidades.getSessionIdUser());
		servicio.setUsuario(usuarioVO);


		String uri = "http://localhost:8080/logica/servicios/create/";
		String result = (String) restTemplate.postForObject(uri,servicio,String.class);
		try {
			servicio = mapper.readValue(result, ServicioVO.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		result = restTemplate.getForObject("http://localhost:8080/logica/servicios/getByProvider/"+utilidades.getSessionIdUser()+"/", String.class);
		try {
			servicios = mapper.readValue(result, new TypeReference<List<ServicioVO>>(){});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		ModelAndView modelAndView = new ModelAndView(VIEW_PROVEEDOR_CREAR_SERVICIOS);
		modelAndView.addObject("servicio", servicio);
		modelAndView.addObject("servicios", servicios);
		modelAndView.addObject("crearExitoso",true);
		modelAndView.addObject("usuarioAutenticado",utilidades.getSessionUser());
		modelAndView.addObject("moduloMensajeria", Constantes.MODULO_MENSAJERIA);
		return modelAndView;
	}

	@RequestMapping(value = "/delete/{id}/", method = RequestMethod.GET)
	public ModelAndView eliminarAlojamiento(@PathVariable("id")int id) {


		RestTemplate restTemplate = new RestTemplate();

		String url = "http://localhost:8080/logica/servicios/delete/"+id +"/";

		try {
			restTemplate.delete(url);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		ModelAndView view=new ModelAndView("redirect:/adminProveedor/");
		return view;

	}
	
	@RequestMapping(value = "/editar-info/", method = RequestMethod.GET)
	public ModelAndView editarProveedor() {
		
		ModelAndView view=new ModelAndView("redirect:/admin/editar/"+utilidades.getSessionIdUser()+"/");
		return view;
	}
	
	
}