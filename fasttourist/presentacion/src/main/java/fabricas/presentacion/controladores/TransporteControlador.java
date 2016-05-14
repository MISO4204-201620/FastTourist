package fabricas.presentacion.controladores;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import utilidades.Constantes;
import utilidades.utilidades;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fabricas.presentacion.VOs.CalificacionesVO;
import fabricas.presentacion.VOs.ServicioVO;
import fabricas.presentacion.VOs.TipotransporteVO;
import fabricas.presentacion.VOs.UsuarioVO;

@Controller
@RequestMapping("/transporte")
public class TransporteControlador {

	private static final String VIEW_BUSCAR_TRANSPORTE = "indexTransporte";
	private static final String VIEW_VER_TRANSPORTE= "verTransporte";



	/**
	 * Controlador de la pantalla de busqueda de alojamiento
	 * Metodo GET
	 * @param model
	 * @return
	 */

	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView buscarTransporte(ModelMap model) {
		List<ServicioVO> servicios=null;
		List<UsuarioVO> proveedores = null;
		List<TipotransporteVO> tipo = null;
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();

		//Se obtiene la lista de proveedores que tengan publicados servicios de transporte
		//Para poner en el select de la pantalla

		String result = restTemplate.getForObject("http://localhost:8080/logica/transporte/getproveedores/", String.class);
		try {
			proveedores = mapper.readValue(result, new TypeReference<List<UsuarioVO>>(){});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		//Se obtiene la lista de tipos de transporte

		result = restTemplate.getForObject("http://localhost:8080/logica/transporte/getipos/", String.class);
		try {
			tipo = mapper.readValue(result, new TypeReference<List<TipotransporteVO>>(){});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		//Se obtienen los servicos de transporte activos, ordenados por fecha de entrada
		result = restTemplate.getForObject("http://localhost:8080/logica/transporte/", String.class);
		try {
			servicios = mapper.readValue(result, new TypeReference<List<ServicioVO>>(){});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		ModelAndView modelAndView = new ModelAndView(VIEW_BUSCAR_TRANSPORTE);
		modelAndView.addObject("servicios", servicios);
		modelAndView.addObject("proveedores", proveedores);
		modelAndView.addObject("tipo", tipo);
		modelAndView.addObject("usuarioAutenticado",utilidades.getSessionUser());
		modelAndView.addObject("moduloMensajeria", Constantes.MODULO_MENSAJERIA);
		modelAndView.addObject("moduloBusquedas", Constantes.MODULO_BUSQUEDAS);
		return modelAndView;
	}

	/**
	 * Controlador de la pantalla de busqueda de transporte
	 * Se buscan servicios por criterios de busqueda
	 * Metodo POST
	 * @param ciudad
	 * @param proveedor
	 * @param fechaEntrada
	 * @param fechaSalida
	 * @param habitaciones
	 * @param personas
	 * @return
	 */
	@RequestMapping(value = "/", method =RequestMethod.POST)
	public ModelAndView buscarTransporte(
			@RequestParam(value="origen", required=false) String origen, 
			@RequestParam(value="destino", required=false) String destino, 
			@RequestParam(value="fechaSalida", required=false) String fechaSalida,
			@RequestParam(value="tipo", required=false) String tipo,
			@RequestParam(value="personas", required=false) String personas,
			@RequestParam(value="proveedor", required=false) String proveedor
			) {

		List<ServicioVO> servicios = null;
		List<UsuarioVO> proveedores = null;
		List<TipotransporteVO> tipoTransporte = null;
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();

		//Se obtiene la lista de proveedores que tengan publicados servicios de transporte
		//Para poner en el select de la pantalla

		String result = restTemplate.getForObject("http://localhost:8080/logica/transporte/getproveedores/", String.class);
		try {
			proveedores = mapper.readValue(result, new TypeReference<List<UsuarioVO>>(){});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		//Se obtiene la lista de tipos de transporte

		result = restTemplate.getForObject("http://localhost:8080/logica/transporte/getipos/", String.class);
		try {
			tipoTransporte = mapper.readValue(result, new TypeReference<List<TipotransporteVO>>(){});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}


		//Se obtienen los servicios de transporte que coincidan con los criterios de busqueda
		String url="origen="+origen+",destino="+destino+",fechaSalida="+fechaSalida;
		url = url+ ",tipo="+tipo+",personas="+personas+",proveedor="+proveedor;


		result = restTemplate.getForObject("http://localhost:8080/logica/transporte/"+url, String.class);

		try {
			servicios = mapper.readValue(result, new TypeReference<List<ServicioVO>>(){});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		ModelAndView modelAndView = new ModelAndView(VIEW_BUSCAR_TRANSPORTE);
		modelAndView.addObject("servicios", servicios);
		modelAndView.addObject("proveedores", proveedores);
		modelAndView.addObject("tipo", tipoTransporte);
		modelAndView.addObject("usuarioAutenticado",utilidades.getSessionUser());
		modelAndView.addObject("moduloMensajeria", Constantes.MODULO_MENSAJERIA);
		modelAndView.addObject("moduloBusquedas", Constantes.MODULO_BUSQUEDAS);
		return modelAndView;
	}

	/**
	 * Controlador de la pantalla de ver informacion detallada de servicio de transporte
	 * @param id
	 * @param model
	 * @return
	 * @throws IOException 
	 * @throws RestClientException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@RequestMapping(value = "/getTransporte/{id}/", method = RequestMethod.GET)
	public ModelAndView getTransporte(@PathVariable("id")int id, ModelMap model){

		ServicioVO servicio=null;
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();

		String result = restTemplate.getForObject("http://localhost:8080/logica/transporte/get/" + id, String.class);
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
		ModelAndView modelAndView = new ModelAndView(VIEW_VER_TRANSPORTE);
		modelAndView.addObject("servicio", servicio);
		if(cont >0){
			promCalificacion = (int) Math.ceil(promCalificacion/cont);
			modelAndView.addObject("promCalificacion", promCalificacion);
		}

		//Se verrifica si se debe habilitar el boton para calificar
		modelAndView.addObject("permisos", utilidades.getPermisos(id));
		modelAndView.addObject("usuarioAutenticado",utilidades.getSessionUser());
		modelAndView.addObject("idusuarioAutenticado",utilidades.getIdUser());
		modelAndView.addObject("moduloMensajeria", Constantes.MODULO_MENSAJERIA);
		modelAndView.addObject("moduloBusquedas", Constantes.MODULO_BUSQUEDAS);
		return modelAndView;

	}



	/**
	 * Controlador de la pantalla de ver informacion detallada de servicio de transporte
	 * @param id
	 * @param model
	 * @return
	 * @throws IOException 
	 * @throws RestClientException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@RequestMapping(value = "/getTransporte/{id}/", method = RequestMethod.POST)
	public ModelAndView getTransporte(@PathVariable("id")int id,
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
			//Si se envió a agregar al carrito
		}else if(carrito !=null){
			result = restTemplate.getForObject("http://localhost:8080/logica/pagos/addCarrito/"+id+"/"+idUsuario, String.class);
		}	
		ModelAndView view=new ModelAndView("redirect:/transporte/getTransporte/"+id+"/");
		return view;
	}

}
