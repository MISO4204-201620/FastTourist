package fabricas.presentacion.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import fabricas.presentacion.VOs.AlimentacionVO;

@Controller
@RequestMapping(value = "/alimentacion")
public class AlimentacionControlador {
	private static final String ALIMENTACION = "indexAlimentacion";

	@RequestMapping(value = "/{id}/", method = RequestMethod.GET)
	public ModelAndView getAlimentacion(@PathVariable("id")int id, ModelMap model){

		AlimentacionVO alimentacion = null;
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		
		String result = restTemplate.getForObject("http://localhost:8080/logica/alimentacion/" + id, String.class);
		try {
			alimentacion = mapper.readValue(result, AlimentacionVO.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		ModelAndView modelAndView = new ModelAndView(ALIMENTACION);
		modelAndView.addObject("alimentacion", alimentacion);
        
		return modelAndView;
	}
	
	@RequestMapping(value = "/alimentacion", method = RequestMethod.GET)
	public String welcome(ModelMap model) {
		return ALIMENTACION;

	}
}
