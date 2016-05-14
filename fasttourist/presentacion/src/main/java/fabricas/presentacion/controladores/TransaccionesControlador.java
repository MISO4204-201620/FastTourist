package fabricas.presentacion.controladores;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import utilidades.utilidades;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import fabricas.presentacion.VOs.TransaccionesVO;

@Controller
@RequestMapping(value = "/transacciones")
public class TransaccionesControlador {

	private static final String VIEW_HISTORICOS = "historicos";
	private static final String VIEW_TRANSACCIONES = "transacciones";
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView verTotal() {
		List<TransaccionesVO> transacciones = null;		
		ObjectMapper mapper = new ObjectMapper();		
		RestTemplate restTemplate = new RestTemplate();
		
		String result = restTemplate.getForObject("http://localhost:8080/logica/transacciones/get/" +
				utilidades.getSessionIdUser()+"/", String.class);
				
		try{
			transacciones = mapper.readValue(result, new TypeReference<List<TransaccionesVO>>(){});													
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}	
		
		ModelAndView mav = new ModelAndView(VIEW_TRANSACCIONES);
		mav.addObject("transacciones", transacciones);		
		mav.addObject("usuarioAutenticado", utilidades.getSessionUser());
		
		return mav;
	}
		
	@RequestMapping(value = "/historicos", method = RequestMethod.GET)
	public ModelAndView verHistoricos() {	
		
		List<String> servicios = null;		
		ObjectMapper mapper = new ObjectMapper();		
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		String result = restTemplate.getForObject("http://localhost:8080/logica/transacciones/getDistinct/" +
				utilidades.getSessionIdUser()+"/", String.class);
		
		try{
			servicios = mapper.readValue(result, new TypeReference<List<String>>(){});
						
			for(String s : servicios){
	            map.put(s, Collections.frequency(servicios, s));
	        }											
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}	
				
		ModelAndView mav = new ModelAndView(VIEW_HISTORICOS);
		mav.addObject("transacciones", map);		
		mav.addObject("usuarioAutenticado", utilidades.getSessionUser());
		
		return mav;
	}
}
