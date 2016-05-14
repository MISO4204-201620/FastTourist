package fabricas.presentacion.controladores;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import utilidades.Constantes;
import utilidades.utilidades;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fabricas.presentacion.VOs.PaqueteVO;
import fabricas.presentacion.VOs.ServicioVO;
import fabricas.presentacion.VOs.TipoalimentacionVO;
import fabricas.presentacion.VOs.TipotransporteVO;

@Controller
@RequestMapping("/paquetes")
public class PaqueteControlador {

	private static final String VIEW_BUSCAR_PAQUETE = "indexPaquetes";
	private static final String VIEW_VER_PAQUETE = "verPaquete";

	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView buscarPaquete(ModelMap model) {
		List<PaqueteVO> paquetes=null;	
		List<TipoalimentacionVO> tiposAlimentacion = null;
		List<TipotransporteVO> tiposTransporte = null;
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();

		//Se obtiene la lista de proveedores que tengan publicados servicios de alojamiento
		//Para poner en el select de la pantalla

//		String result = restTemplate.getForObject("http://localhost:8080/logica/paquetes/getproveedores/", String.class);
//		try {
//			proveedores = mapper.readValue(result, new TypeReference<List<UsuarioVO>>(){});
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}

		//Se obtienen los servicos de alojamiento activos, ordenados por fecha de entrada
		String result = restTemplate.getForObject("http://localhost:8080/logica/paquetes/", String.class);
		try {
			paquetes = mapper.readValue(result, new TypeReference<List<PaqueteVO>>(){});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		result = restTemplate.getForObject("http://localhost:8080/logica/alimentacion/gettipos", String.class);
		try{
			tiposAlimentacion = mapper.readValue(result, new TypeReference<List<TipoalimentacionVO>>(){});			
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		result = restTemplate.getForObject("http://localhost:8080/logica/transporte/getipos/", String.class);
		try {
			tiposTransporte = mapper.readValue(result, new TypeReference<List<TipotransporteVO>>(){});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		for(int i=0; i<paquetes.size(); i++){
			PaqueteVO paquete = paquetes.get(i);			
			List<ServicioVO> servicios=null;
			
			result = restTemplate.getForObject("http://localhost:8080/logica/paquetes/getServicios/" + paquete.getIdpaquetes(), String.class);
			try {
				servicios = mapper.readValue(result, new TypeReference<List<ServicioVO>>(){});
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			paquete.setServicios(servicios);				
			paquetes.set(i, paquete);			
		}		

		ModelAndView modelAndView = new ModelAndView(VIEW_BUSCAR_PAQUETE);
		modelAndView.addObject("paquetes", paquetes);	
		modelAndView.addObject("tiposAlimentacion", tiposAlimentacion);
		modelAndView.addObject("tiposTransporte", tiposTransporte);
		modelAndView.addObject("usuarioAutenticado",utilidades.getSessionUser());
		modelAndView.addObject("moduloMensajeria", Constantes.MODULO_MENSAJERIA);
		modelAndView.addObject("moduloBusquedas", Constantes.MODULO_BUSQUEDAS);
		return modelAndView;
	}
	
	@RequestMapping(value = "/", method =RequestMethod.POST)
	public ModelAndView buscarPaquetes(									
			@RequestParam(value="fechaEntrada", required=false) String fechaEntrada,
			@RequestParam(value="fechaSalida", required=false) String fechaSalida,			
			@RequestParam(value="origen", required=false) String origen, 
			@RequestParam(value="destino", required=false) String destino, 			
			@RequestParam(value="tipoTransporte", required=false) String tipoTransporte,
			@RequestParam(value="tipoAlimentacion", required=false) String tipoAlimentacion) {

		List<PaqueteVO> paquetes = null;
		List<TipoalimentacionVO> tiposAlimentacion = null;
		List<TipotransporteVO> tiposTransporte = null;
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();		

		//Se obtienen los servicios de alojamineto que coincidan con los criterios de busqueda
		String url="fechaEntrada="+fechaEntrada+",fechaSalida="+fechaSalida;
		url = url + ",origen="+origen+",destino="+destino+",tipoTransporte="+tipoTransporte+",tipoAlimentacion="+tipoAlimentacion;
		
		System.out.println("url " + url);

		String result = restTemplate.getForObject("http://localhost:8080/logica/paquetes/"+url, String.class);

		try {
			System.out.println(result);
			paquetes = mapper.readValue(result, new TypeReference<List<PaqueteVO>>(){});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		for(int i=0; i<paquetes.size(); i++){
			PaqueteVO paquete = paquetes.get(i);			
			List<ServicioVO> servicios=null;
			
			result = restTemplate.getForObject("http://localhost:8080/logica/paquetes/getServicios/" + paquete.getIdpaquetes(), String.class);
			try {
				servicios = mapper.readValue(result, new TypeReference<List<ServicioVO>>(){});
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			paquete.setServicios(servicios);				
			paquetes.set(i, paquete);			
		}
		
		result = restTemplate.getForObject("http://localhost:8080/logica/alimentacion/gettipos", String.class);
		try{
			tiposAlimentacion = mapper.readValue(result, new TypeReference<List<TipoalimentacionVO>>(){});			
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		result = restTemplate.getForObject("http://localhost:8080/logica/transporte/getipos/", String.class);
		try {
			tiposTransporte = mapper.readValue(result, new TypeReference<List<TipotransporteVO>>(){});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		ModelAndView modelAndView = new ModelAndView(VIEW_BUSCAR_PAQUETE);
		modelAndView.addObject("paquetes", paquetes);
		modelAndView.addObject("tiposAlimentacion", tiposAlimentacion);
		modelAndView.addObject("tiposTransporte", tiposTransporte);
		modelAndView.addObject("usuarioAutenticado",utilidades.getSessionUser());
		modelAndView.addObject("moduloMensajeria", Constantes.MODULO_MENSAJERIA);
		modelAndView.addObject("moduloBusquedas", Constantes.MODULO_BUSQUEDAS);
		return modelAndView;
	}


	/**
	 * Controlador de la pantalla de ver informacion detallada de servicio de alojamiento
	 * @param id
	 * @param model
	 * @return
	 * @throws IOException 
	 * @throws RestClientException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@RequestMapping(value = "/getPaquete/{id}/", method = RequestMethod.GET)
	public ModelAndView getPaquete(@PathVariable("id")int id, ModelMap model){

		PaqueteVO paquete=null;
		List<ServicioVO> servicios=null;
		List<TipoalimentacionVO> tiposAlimentacion = null;
		List<TipotransporteVO> tiposTransporte = null;
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();

		String result = restTemplate.getForObject("http://localhost:8080/logica/paquetes/get/" + id, String.class);
		try {
			paquete = mapper.readValue(result, PaqueteVO.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		result = restTemplate.getForObject("http://localhost:8080/logica/alimentacion/gettipos", 
				String.class);
		
		try{
			tiposAlimentacion = mapper.readValue(result, new TypeReference<List<TipoalimentacionVO>>(){});			
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		result = restTemplate.getForObject("http://localhost:8080/logica/transporte/getipos/", String.class);
		try {
			tiposTransporte = mapper.readValue(result, new TypeReference<List<TipotransporteVO>>(){});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		result = restTemplate.getForObject("http://localhost:8080/logica/paquetes/getServicios/" + id, String.class);
		try {
			servicios = mapper.readValue(result, new TypeReference<List<ServicioVO>>(){});
			paquete.setServicios(servicios);			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		int precioPaquete = 0;
		for(ServicioVO servicio:servicios){
			precioPaquete = precioPaquete + servicio.getPrecio().intValue();			
		}
		
		paquete.setPrecio(new BigDecimal(precioPaquete));
		
		//Se envian los objetos a la pantalla
		ModelAndView modelAndView = new ModelAndView(VIEW_VER_PAQUETE);
		modelAndView.addObject("paquete", paquete);		
		modelAndView.addObject("tiposAlimentacion", tiposAlimentacion);
		modelAndView.addObject("tiposTransporte", tiposTransporte);
		modelAndView.addObject("usuarioAutenticado",utilidades.getSessionUser());
		modelAndView.addObject("idusuarioAutenticado",utilidades.getIdUser());
		modelAndView.addObject("moduloMensajeria", Constantes.MODULO_MENSAJERIA);	
		modelAndView.addObject("moduloBusquedas", Constantes.MODULO_BUSQUEDAS);
		return modelAndView;
	}
	
	@RequestMapping(value = "/getPaquete/{id}/", method = RequestMethod.POST)
	public ModelAndView getPaquete(@PathVariable("id")int id,			
			@RequestParam(value="inputPregunta", required=false) String pregunta,
			@RequestParam(value="inputComentario", required=false) String inputComentario,
			@RequestParam(value="valor", required=false) String valor,
			@RequestParam(value="carrito", required=false) Integer carrito){
		
		String strView = "";
		
		if(utilidades.getSessionUser() != null){
			RestTemplate restTemplate = new RestTemplate();
			String result = "";
			
			//Se recupera el id del usuario autenticado
			Integer idUsuario = utilidades.getSessionIdUser();
	
			//Si se envió a almacenar una pregunta
			if(pregunta != null && !pregunta.isEmpty()){
				pregunta = pregunta.replace("?", "");
				result = restTemplate.getForObject("http://localhost:8080/logica/preguntas/set/" + pregunta + "/" + id, String.class);
			//Si se envió a almacenar una calificacion
			}else if(valor != null && !valor.isEmpty()){
				String comentario = (inputComentario.isEmpty() ? "Sin comentarios":inputComentario);
				result = restTemplate.getForObject("http://localhost:8080/logica/calificaciones/set/"+ valor +"/" +comentario +"/" +id +"/" +idUsuario, String.class); 
			//Si se envió a agregar al carrito
			}else if(carrito !=null){			
				PaqueteVO paquete = null;
				List<ServicioVO> servicios=null;
				ObjectMapper mapper = new ObjectMapper();				
				
				result = restTemplate.getForObject("http://localhost:8080/logica/paquetes/getServicios/" + id, String.class);
				try {
					servicios = mapper.readValue(result, new TypeReference<List<ServicioVO>>(){});
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				
				for(ServicioVO servicio:servicios){
					int idServicio = servicio.getIdservicios();
					result = restTemplate.getForObject("http://localhost:8080/logica/pagos/addCarrito/"+idServicio+"/"+idUsuario, String.class);
				}			
			}
			strView = "redirect:/paquetes/getPaquete/"+id+"/";			
		}else{
			strView = "redirect:/registro/";			
		}
		
		ModelAndView view=new ModelAndView(strView);
		
		return view;		
	}
	
	@RequestMapping(value="/index")
	public ModelAndView cerrarSesion(){
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true);
		session.invalidate();
		ModelAndView modelAndView = new ModelAndView(VIEW_BUSCAR_PAQUETE);
		modelAndView.addObject("usuarioAutenticado",utilidades.getSessionUser());
		modelAndView.addObject("moduloMensajeria", Constantes.MODULO_MENSAJERIA);
		modelAndView.addObject("moduloBusquedas", Constantes.MODULO_BUSQUEDAS);
		return modelAndView;
	}
}