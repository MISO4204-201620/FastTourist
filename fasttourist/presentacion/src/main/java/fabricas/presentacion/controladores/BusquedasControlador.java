package fabricas.presentacion.controladores;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import utilidades.Constantes;
import utilidades.utilidades;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import fabricas.presentacion.VOs.BusquedasVO;

@Controller
@RequestMapping(value = "/busquedas")
public class BusquedasControlador {

	private static final String VIEW_BUSQUEDAS = "busquedas";


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView indexBusquedas(ModelMap model) {

		List<BusquedasVO> busquedas=null;
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();


		//Se obtienen los servicos del proveedor ordenados por fecha de creacion
		String result = restTemplate.getForObject("http://localhost:8080/busqueda/"+utilidades.getIdUser(), String.class);
		try {
			busquedas = mapper.readValue(result, new TypeReference<List<BusquedasVO>>(){});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		ModelAndView modelAndView = new ModelAndView(VIEW_BUSQUEDAS);
		modelAndView.addObject("busquedas", busquedas);
		modelAndView.addObject("usuarioAutenticado",utilidades.getSessionUser());
		modelAndView.addObject("moduloBusquedas", Constantes.MODULO_BUSQUEDAS);
		modelAndView.addObject("moduloMensajeria", Constantes.MODULO_MENSAJERIA);
		return modelAndView;
	}
}