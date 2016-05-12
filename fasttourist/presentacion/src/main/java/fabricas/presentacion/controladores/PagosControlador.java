package fabricas.presentacion.controladores;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import utilidades.Constantes;
import utilidades.utilidades;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import fabricas.presentacion.VOs.CarritoVO;

@Controller
@RequestMapping("/pagar")
public class PagosControlador {

	private static final String VER_CARRITO = "carrito";
	private static final String PAGAR = "checkout";

	/**
	 * Metodo para ver pantalla del carrito
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/carrito", method = RequestMethod.GET)
	public ModelAndView getCarrito( ModelMap model){

		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		List<CarritoVO> carrito = null;
		Integer valor=0;
		float impuestos =0;
		float total=0;

		//TODO:Recuperar el id del usuario autenticado
		//Temporal se carga por defecto Pedro Perez
		if(utilidades.isUserAutenticado()){
			int idUsuario = utilidades.getSessionIdUser();

			String result = restTemplate.getForObject(
					"http://localhost:8080/logica/pagos/checkout/" + idUsuario, String.class);

			try {
				carrito = mapper.readValue(result, new TypeReference<List<CarritoVO>>(){});
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}		

			for (CarritoVO carritoVO : carrito) {
				valor +=carritoVO.getServicio().getPrecio().intValueExact();
			}

			impuestos = (float) (valor *0.16);
			total = valor + impuestos;

		}
		ModelAndView modelAndView = new ModelAndView(VER_CARRITO);
		modelAndView.addObject("carrito", carrito);
		modelAndView.addObject("valor", valor);
		modelAndView.addObject("impuestos", impuestos);
		modelAndView.addObject("total", total);
		modelAndView.addObject("usuarioAutenticado",utilidades.getSessionUser());
		modelAndView.addObject("moduloMensajeria", Constantes.MODULO_MENSAJERIA);
		return modelAndView;

	}

	/**
	 * Metodo para comprar y pagar
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
	public ModelAndView checkout( ModelMap model){

		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		List<CarritoVO> carrito = null;
		Integer valor=0;
		float impuestos =0;
		float total=0;

		//TODO:Recuperar el id del usuario autenticado
		//Temporal se carga por defecto Pedro Perez
		int idUsuario =utilidades.getSessionIdUser();

		String result = restTemplate.getForObject(
				"http://localhost:8080/logica/pagos/checkout/" + idUsuario, String.class);

		try {
			carrito = mapper.readValue(result, new TypeReference<List<CarritoVO>>(){});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		for (CarritoVO carritoVO : carrito) {
			valor +=carritoVO.getServicio().getPrecio().intValueExact();
		}

		impuestos = (float) (valor *0.16);
		total = valor + impuestos;

		ModelAndView modelAndView = new ModelAndView(PAGAR);
		modelAndView.addObject("carrito", carrito);
		modelAndView.addObject("valor", valor);
		modelAndView.addObject("impuestos", impuestos);
		modelAndView.addObject("total", total);
		modelAndView.addObject("usuarioAutenticado",utilidades.getSessionUser());
		modelAndView.addObject("moduloMensajeria", Constantes.MODULO_MENSAJERIA);
		return modelAndView;

	}


	/**
	 * Metodo para comprar y pagar
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/checkout", method = RequestMethod.POST)
	public ModelAndView checkout( ){

		RestTemplate restTemplate = new RestTemplate();
		List<CarritoVO> carrito = null;

		//TODO:Recuperar el id del usuario autenticado
		//Temporal se carga por defecto Pedro Perez
		int idUsuario = utilidades.getSessionIdUser();

		String result = restTemplate.getForObject(
				"http://localhost:8080/logica/pagos/setCheckout/" + idUsuario, String.class);


		ModelAndView modelAndView = new ModelAndView(PAGAR);
		modelAndView.addObject("carrito", carrito);
		modelAndView.addObject("pagoExitoso",result);
		modelAndView.addObject("usuarioAutenticado",utilidades.getSessionUser());
		modelAndView.addObject("moduloMensajeria", Constantes.MODULO_MENSAJERIA);
		return modelAndView;

	}



	/**
	 * Metodo para ver pantalla del carrito
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/carrito/{ids}", method = RequestMethod.GET)
	public ModelAndView removeCarrito(@PathVariable("ids") String ids, ModelMap model){
		RestTemplate restTemplate = new RestTemplate();
		String result="";
		String [] id = ids.split(",");

		for (String x : id) {
			result = restTemplate.getForObject(
					"http://localhost:8080/logica/pagos/remove/" + x, String.class);
		}
		ModelAndView view=new ModelAndView("redirect:/pagar/carrito");
		return view;


	}
}
