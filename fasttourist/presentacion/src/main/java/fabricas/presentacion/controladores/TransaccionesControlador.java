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

import utilidades.Constantes;
import utilidades.utilidades;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import fabricas.presentacion.VOs.TransaccionesVO;

@Controller
@RequestMapping(value = "/transacciones")
public class TransaccionesControlador {

	private static final String VIEW_HISTORICOS = "historicos";
	private static final String VIEW_TRANSACCIONES = "transaccionesProveedor";
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView verTotal() {
		List<TransaccionesVO> transacciones = null;		
		ObjectMapper mapper = new ObjectMapper();		
		RestTemplate restTemplate = new RestTemplate();
		
		String fechas = "[";
		String fechaAnt = "";
		String candidato = "";
		int contador = 0;
		
		String result = restTemplate.getForObject("http://localhost:8080/logica/transacciones/getByProvider/" +
				utilidades.getSessionIdUser()+"/", String.class);
				
		try{
			transacciones = mapper.readValue(result, new TypeReference<List<TransaccionesVO>>(){});	
			for(TransaccionesVO tr:transacciones)
			{
				String fe[] = tr.getFecha().toString().split(" ");
//				fechas = fechas+"{\n";
				if(fe[1].equals(fechaAnt)){
					contador++;
//					fechas = fechas+"'fecha'"+":'"+fe[1]+" "+fe[2]+"',\n";
//					fechas = fechas+"'valor'"+":"+contador+"\n}, ";						
					candidato = "{'fecha'"+":'"+fe[1]+" "+fe[2]+"',\n";
					candidato = candidato+"'valor'"+":"+contador+"\n}, ";						
				}
				else{
					fechas = fechas+candidato;
					candidato = "";
					fechas = fechas+"{'fecha'"+":'"+fe[1]+" "+fe[2]+"',\n";
					fechas = fechas+"'valor'"+":"+"1\n}, ";	
					contador=0;
				}
				fechaAnt = fe[1];
			}		
			fechas = fechas+"];";		
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}	
		
		ModelAndView mav = new ModelAndView(VIEW_TRANSACCIONES);
		mav.addObject("transacciones", transacciones);	
		mav.addObject("fechas",fechas);
		mav.addObject("usuarioAutenticado", utilidades.getSessionUser());
		mav.addObject("moduloMensajeria", Constantes.MODULO_MENSAJERIA);
		
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
		mav.addObject("moduloMensajeria", Constantes.MODULO_MENSAJERIA);
		
		return mav;
	}
}
