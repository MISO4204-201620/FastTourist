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
import fabricas.presentacion.VOs.PaseosecologicoVO;
import fabricas.presentacion.VOs.ServicioVO;
import fabricas.presentacion.VOs.UsuarioVO;

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
//		modelAndView.addObject("paseos", paseos);

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

		String result = restTemplate.getForObject("http://localhost:8080/logica/paseos/filtarPaseos/"+url, String.class);

		try {
			servicios = mapper.readValue(result, new TypeReference<List<ServicioVO>>(){});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		ModelAndView modelAndView = new ModelAndView(VIEW_PASEOS);
		modelAndView.addObject("servicios", servicios);
		
		return modelAndView;
	}
	
	
	@RequestMapping(value="/paseos/getServicio/{id}/", method=RequestMethod.GET)
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

		return modelAndView;
	}

	
}
