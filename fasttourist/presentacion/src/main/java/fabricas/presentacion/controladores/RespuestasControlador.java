package fabricas.presentacion.controladores;

import java.util.ArrayList;
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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import fabricas.presentacion.VOs.PreguntasVO;
import fabricas.presentacion.VOs.PreguntasWrapper;
import utilidades.utilidades;

@Controller
@RequestMapping(value = "/moduloRespuestas")
public class RespuestasControlador {
	private static final String VIEW_PREGUNTAS = "moduloRespuestas";

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView buscarPreguntas() {
		List<PreguntasVO> preguntas = null;		
		
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		
		String result = restTemplate.getForObject("http://localhost:8080/logica/preguntas/get/" +utilidades.getSessionIdUser()+"/", 
				String.class);
		
		try{			
			preguntas = mapper.readValue(result, new TypeReference<List<PreguntasVO>>(){});				
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}	
		
		PreguntasWrapper wrapper = new PreguntasWrapper();
		wrapper.setPreguntas((ArrayList<PreguntasVO>) preguntas);
		
		ModelAndView mav = new ModelAndView(VIEW_PREGUNTAS);
		mav.addObject("preguntasWrapper", wrapper);		
		mav.addObject("usuarioAutenticado", utilidades.getSessionUser());
		
		return mav;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView editarPreguntas(@ModelAttribute(value = "preguntasWrapper") PreguntasWrapper preguntas) {
	
		System.out.println("Llega");
		for(PreguntasVO pregunta : preguntas.getPreguntas()){
			if(pregunta.getRespuesta() != null){
				RestTemplate restTemplate = new RestTemplate();
				
				try{
				String result = restTemplate.getForObject("http://localhost:8080/logica/preguntas/setRespuesta/" + 
						pregunta.getRespuesta() + "/" + pregunta.getIdPreguntas(), String.class);
				}
				catch(Exception e){
					System.out.println(e.getMessage());
				}
			}
		}
				
		ModelAndView view=new ModelAndView("redirect:/moduloRespuestas/");
		return view;
	}
}
