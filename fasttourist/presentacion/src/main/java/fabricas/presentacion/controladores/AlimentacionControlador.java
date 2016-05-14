package fabricas.presentacion.controladores;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import utilidades.Constantes;
import utilidades.utilidades;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import fabricas.presentacion.VOs.CalificacionesVO;
import fabricas.presentacion.VOs.ServicioVO;
import fabricas.presentacion.VOs.TipoalimentacionVO;
import fabricas.presentacion.VOs.UsuarioVO;

@Controller
@RequestMapping(value = "/alimentacion")
public class AlimentacionControlador {
	private static final String VIEW_SERVICIOS_ALIMENTACION = "indexAlimentacion";
	private static final String VIEW_ALIMENTACION = "verAlimentacion";
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView buscarAlimentacion(ModelMap model) {
		List<ServicioVO> servicios = null;
		List<UsuarioVO> proveedores = null;
		List<TipoalimentacionVO> tipo = null;
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		
		String result = restTemplate.getForObject("http://localhost:8080/logica/alimentacion/getProveedores2", 
				String.class);
		
		try{
			proveedores = mapper.readValue(result, new TypeReference<List<UsuarioVO>>(){});			
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		result = restTemplate.getForObject("http://localhost:8080/logica/alimentacion/gettipos", 
				String.class);
		
		try{
			tipo = mapper.readValue(result, new TypeReference<List<TipoalimentacionVO>>(){});			
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		result = restTemplate.getForObject("http://localhost:8080/logica/alimentacion/getServiciosAlimentacion2", 
				String.class);
		
		try{
			servicios = mapper.readValue(result, new TypeReference<List<ServicioVO>>(){});			
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		ModelAndView mav = new ModelAndView(VIEW_SERVICIOS_ALIMENTACION);
		mav.addObject("servicios", servicios);
		mav.addObject("proveedores", proveedores);
		mav.addObject("tipo", tipo);
		mav.addObject("usuarioAutenticado",utilidades.getSessionUser());
		mav.addObject("moduloMensajeria", Constantes.MODULO_MENSAJERIA);
		mav.addObject("moduloBusquedas", Constantes.MODULO_BUSQUEDAS);
		return mav;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView buscarAlimentacion(
			@RequestParam(value="proveedor", required=false) String proveedor,
			@RequestParam(value="tipo", required=false) String tipoAlimentacion){
		
		List<ServicioVO> servicios = null;
		List<UsuarioVO> proveedores = null;
		List<TipoalimentacionVO> tipo = null;
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		
		String result = restTemplate.getForObject("http://localhost:8080/logica/alimentacion/getProveedores2/", 
				String.class);
		
		try{
			proveedores = mapper.readValue(result, new TypeReference<List<UsuarioVO>>(){});			
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		result = restTemplate.getForObject("http://localhost:8080/logica/alimentacion/gettipos/", 
				String.class);
		
		try{
			tipo = mapper.readValue(result, new TypeReference<List<TipoalimentacionVO>>(){});			
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		String consulta = "proveedor="+proveedor+",tipoAlimentacion="+tipoAlimentacion;
				
		result = restTemplate.getForObject("http://localhost:8080/logica/alimentacion/" + consulta, 
				String.class);
		
		try{
			servicios = mapper.readValue(result, new TypeReference<List<ServicioVO>>(){});			
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		ModelAndView mav = new ModelAndView(VIEW_SERVICIOS_ALIMENTACION);
		mav.addObject("servicios", servicios);
		mav.addObject("proveedores", proveedores);
		mav.addObject("tipo", tipo);
		mav.addObject("usuarioAutenticado",utilidades.getSessionUser());
		mav.addObject("moduloMensajeria", Constantes.MODULO_MENSAJERIA);
		mav.addObject("moduloBusquedas", Constantes.MODULO_BUSQUEDAS);
		return mav;
	}

	@RequestMapping(value = "/getAlimentacion/{id}/", method = RequestMethod.GET)
	public ModelAndView getAlimentacion(@PathVariable("id")int id, ModelMap model){

		ServicioVO servicio = null;
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		
		String result = restTemplate.getForObject("http://localhost:8080/logica/alimentacion/get/" + id, String.class);
		
		try {
			servicio = mapper.readValue(result, ServicioVO.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		int promCalificacion = 0;
		
		for(CalificacionesVO calificacion : servicio.getCalificaciones()){
			promCalificacion = calificacion.getIdcalificaciones() + promCalificacion;
		}
		
		promCalificacion = servicio.getCalificaciones().size()>0 ? promCalificacion/servicio.getCalificaciones().size() : 0;
		
		ModelAndView modelAndView = new ModelAndView(VIEW_ALIMENTACION);
		modelAndView.addObject("servicio", servicio);
		modelAndView.addObject("promCalificacion", promCalificacion);
		modelAndView.addObject("usuarioAutenticado",utilidades.getSessionUser());
		modelAndView.addObject("idusuarioAutenticado",utilidades.getIdUser());
		modelAndView.addObject("moduloMensajeria", Constantes.MODULO_MENSAJERIA);
		modelAndView.addObject("moduloBusquedas", Constantes.MODULO_BUSQUEDAS);
		return modelAndView;
	}
	
	@RequestMapping(value = "/getAlimentacion/{id}/", method = RequestMethod.POST)
	public ModelAndView getAlimentacion(@PathVariable("id")int id,
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
				result = restTemplate.getForObject("http://localhost:8080/logica/pagos/addCarrito/"+id+"/"+idUsuario, String.class);
			}
			
			strView = "redirect:/alimentacion/getAlimentacion/"+id+"/";			
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
		ModelAndView modelAndView = new ModelAndView(VIEW_SERVICIOS_ALIMENTACION);
		modelAndView.addObject("usuarioAutenticado",utilidades.getSessionUser());
		modelAndView.addObject("moduloMensajeria", Constantes.MODULO_MENSAJERIA);
		modelAndView.addObject("moduloBusquedas", Constantes.MODULO_BUSQUEDAS);
		return modelAndView;
	}
}
