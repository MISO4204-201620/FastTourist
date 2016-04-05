package fabricas.presentacion.controladores;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import fabricas.presentacion.VOs.CalificacionesVO;
import fabricas.presentacion.VOs.ServicioVO;

@Controller
@RequestMapping("/paseos")
public class PaseosController {

	private static final String VIEW_PASEOS = "paseos";
	private static final String VIEW_VER_PASEO = "verPaseo";

	/**
	 * Controlador de la pantalla de busqueda de alojamiento
	 * Metodo GET
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/paseos", method=RequestMethod.GET)
	public ModelAndView mostrarPaseos(ModelMap model) {		
		
		List<ServicioVO> servicios=null;
//		List<PaseosecologicoVO> paseos=null;
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();

		//Se obtienen los servicos de alojamiento activos, ordenados por fecha de entrada
		String result = restTemplate.getForObject("http://localhost:8080/logica/paseos/getServiciosPaseos", String.class);
		try {
			servicios = mapper.readValue(result, new TypeReference<List<ServicioVO>>(){});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
//		result = restTemplate.getForObject("http://localhost:8080/logica/paseos/getPaseos", String.class);
//		try {
//			paseos = mapper.readValue(result, new TypeReference<List<PaseosecologicoVO>>(){});
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}

		ModelAndView modelAndView = new ModelAndView(VIEW_PASEOS);
		modelAndView.addObject("servicios", servicios);
		modelAndView.addObject("usuarioAutenticado",utilidades.getSessionUser());
		if(utilidades.getSessionUserRole() != null){
			if(utilidades.getSessionUserRole().startsWith("Proveedor"))
				modelAndView.addObject("perfilUsuario",utilidades.getSessionUserRole());
		}
		System.out.println("PerfilUser: "+utilidades.getSessionUserRole());

		return modelAndView;
	}
	
	@RequestMapping(value="/paseos", method=RequestMethod.POST)
	public ModelAndView filtrarPaseos(
		@RequestParam(value="nombre", required=false) String nombre, 
		@RequestParam(value="lugar", required=false) String lugar, 
		@RequestParam(value="servicio", required=false) String servicio){
		
		List<ServicioVO> servicios = null;
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();

		//Se obtienen los servicios de alojamineto que coincidan con los criterios de busqueda
		String url="nombre="+nombre+",lugar="+lugar+",servicio="+servicio;

		String result = restTemplate.getForObject("http://localhost:8080/logica/paseos/filtrarPaseos/"+url, String.class);

		try {
			servicios = mapper.readValue(result, new TypeReference<List<ServicioVO>>(){});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		ModelAndView modelAndView = new ModelAndView(VIEW_PASEOS);
		modelAndView.addObject("servicios", servicios);
		modelAndView.addObject("usuarioAutenticado",utilidades.getSessionUser());
		return modelAndView;
	}
	
	
	@RequestMapping(value="/getServicio/{id}/", method=RequestMethod.GET)
	public ModelAndView darPaseoEcologico(@PathVariable("id")int id, ModelMap model) {		
		
		ServicioVO servicio=null;
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();

		//El de alojamiento sirve porque el servicio contiene el paseoEcologico
		String result = restTemplate.getForObject("http://localhost:8080/logica/alojamiento/get/" + id, String.class);
		try {
			servicio = mapper.readValue(result, ServicioVO.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		//Calculan las estrellas de calificacion
		int promCalificacion = 0;
		int cont = 0;
		for (CalificacionesVO calificacion : servicio.getCalificaciones()) {
			cont++;
			promCalificacion = promCalificacion + calificacion.getValor();
		}


		//Se envian los objetos a la pantalla
		ModelAndView modelAndView = new ModelAndView(VIEW_VER_PASEO);
		modelAndView.addObject("servicio", servicio);
		if(cont >0){
			promCalificacion = (int) Math.ceil(promCalificacion/cont);
			modelAndView.addObject("promCalificacion", promCalificacion);
		}
		
		//Se verrifica si se debe habilitar el boton para calificar
		modelAndView.addObject("permisos", utilidades.getPermisos(id));
		modelAndView.addObject("usuarioAutenticado",utilidades.getSessionUser());
		return modelAndView;
	}

	
	/**
	 * Controlador de la pantalla de ver informacion detallada de servicio de
	 * paseo ecologico *
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/getServicio/{id}/", method = RequestMethod.POST)
	public ModelAndView darPaseoEcologico(
			@PathVariable("id")int id,
			@RequestParam(value="inputPregunta", required=false) String pregunta,
			@RequestParam(value="inputComentario", required=false) String inputComentario,
			@RequestParam(value="valor", required=false) String valor,
			@RequestParam(value="carrito", required=false) Integer carrito){
		
		RestTemplate restTemplate = new RestTemplate();
		String result = "";
		
		//Se recupera el id del usuario autenticado
		Integer idUsuario = null;
		if(utilidades.isUserAutenticado()){
			idUsuario = utilidades.getSessionIdUser();
		}
		
		//Se almacena la pregunta relacionada al servicio
		if(pregunta != null && !pregunta.isEmpty()){
			pregunta = pregunta.replace("?", "");
			result = restTemplate.getForObject("http://localhost:8080/logica/preguntas/set/" + pregunta + "/" + id, String.class);
		}else if(valor != null && !valor.isEmpty()){
			String comentario = (inputComentario.isEmpty() ? "Sin comentarios":inputComentario);
			result = restTemplate.getForObject("http://localhost:8080/logica/calificaciones/set/"+ valor +"/" +comentario +"/" +id +"/" +idUsuario, String.class); 
		//Si se envi� a agregar al carrito
		}else if(carrito !=null){
			result = restTemplate.getForObject("http://localhost:8080/logica/pagos/addCarrito/"+id+"/"+idUsuario, String.class);
		}
		
		ModelAndView view=new ModelAndView("redirect:/paseos/getServicio/"+id+"/");
		return view;

	}
	
	
	
	//-----CRUD-----
	
	
	@RequestMapping(value="/paseos/crear", method = RequestMethod.GET)
	public ModelAndView redirigirCreate(){
		
		ModelAndView modelAndView = new ModelAndView(VIEW_PASEOS);
		modelAndView.addObject("usuarioAutenticado",utilidades.getSessionUser());
		modelAndView.addObject("nuevoPaseo","nuevoPaseo");
		return modelAndView;
	}
	
	@RequestMapping(value="/paseos/recibir", method = RequestMethod.POST)
	public ModelAndView createPaseo(
			@RequestParam(value="descripcion",required=true)String descrip,
			@RequestParam(value="duracion",required=true)int duracion,
			@RequestParam(value="fotos",required=true)String fotos,
			@RequestParam(value="lugar",required=true)String lugar,
			@RequestParam(value="nombre",required=true)String nombre,
			@RequestParam(value="precio",required=true)String precio,
			@RequestParam(value="requerimientos",required=true)String req){
		
		RestTemplate restTemplate = new RestTemplate();
		String result = "";

		String url = descrip+","+duracion+","+fotos+","+lugar+","+nombre+","+precio+","+req;
		//Crear Servicio
		
		result = restTemplate.getForObject("http://localhost:8080/logica/paseos/crear/"+url, String.class);
		
		
		ModelAndView modelAndView = new ModelAndView(VIEW_PASEOS);
		modelAndView.addObject("response",result);
		modelAndView.addObject("usuarioAutenticado",utilidades.getSessionUser());
		
		return modelAndView;
	}
	
	
	@RequestMapping(value="/eliminar/{id}/", method = RequestMethod.GET)
	public ModelAndView eliminarPaseo(
			@PathVariable("id")String id){
		
		RestTemplate restTemplate = new RestTemplate();
		String result = "";
		
		result = restTemplate.getForObject("http://localhost:8080/logica/paseos/eliminar/"+id, String.class);
		
		
		ModelAndView view = new ModelAndView(VIEW_PASEOS);
		view.addObject("response",result);
		view.addObject("usuarioAutenticado",utilidades.getSessionUser());
		
		
		return view;

	}	
	
	
	
	
	
}