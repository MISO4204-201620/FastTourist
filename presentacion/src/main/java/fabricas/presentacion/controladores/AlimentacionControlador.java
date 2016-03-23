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
import fabricas.presentacion.VOs.UsuarioVO;

@Controller
public class AlimentacionControlador {
	private static final String VIEW_SERVICIOS_ALIMENTACION = "indexAlimentacion";
	private static final String VIEW_ALIMENTACION = "verAlimentacion";
	
	@RequestMapping(value = "/alimentacion", method = RequestMethod.GET)
	public ModelAndView buscarAlimentacion(ModelMap model) {
		List<ServicioVO> servicios = null;
		List<UsuarioVO> proveedores = null;
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		
		String result = restTemplate.getForObject("http://localhost:8080/logica/alimentacion/getProveedores", 
				String.class);
		
		try{
			proveedores = mapper.readValue(result, new TypeReference<List<UsuarioVO>>(){});			
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		result = restTemplate.getForObject("http://localhost:8080/logica/alimentacion/", 
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
		
		return mav;
	}
	
	@RequestMapping(value = "/alimentacion", method = RequestMethod.POST)
	public ModelAndView buscarAlimentacion(
			@RequestParam(value="ciudad", required=false) String ciudad,
			@RequestParam(value="proveedor", required=false) String proveedor,			
			@RequestParam(value="tipoAlimentacion", required=false) String tipoAlimentacion){
		
		List<ServicioVO> servicios = null;
		List<UsuarioVO> proveedores = null;
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		
		String result = restTemplate.getForObject("http://localhost:8080/logica/alimentacion/getProveedores", 
				String.class);
		
		try{
			proveedores = mapper.readValue(result, new TypeReference<List<UsuarioVO>>(){});			
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		String consulta = "ciudad="+ciudad+",proveedor="+proveedor+",tipoAlimentacion="+tipoAlimentacion;
				
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
		
		promCalificacion = promCalificacion/servicio.getCalificaciones().size();
		
		ModelAndView modelAndView = new ModelAndView(VIEW_ALIMENTACION);
		modelAndView.addObject("servicio", servicio);
		modelAndView.addObject("promCalificacion", promCalificacion);
        
		return modelAndView;
	}
	
	
}
