package fabricas.presentacion.controladores;

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

import utilidades.Constantes;
import utilidades.EnumPerfiles;
import utilidades.utilidades;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import fabricas.presentacion.VOs.TransaccionesVO;
import fabricas.presentacion.VOs.UsuarioVO;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	
	
	private static final String ADMIN = "admin";
	private static final String EDITPROOV = "editarProveedor";
	private static final String NUEVOPROV = "crearProveedor";
	private static final String TRANSACCIONES = "transacciones";
	private static final String CATEGORIAS = "categorias";
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView cargarAdmin(){	
		
		//Validar que solo pueda ingresar Administrador
		if(!utilidades.isUserAutenticado() || 
				utilidades.getPerfil()!=EnumPerfiles.ADMINISTRADOR.getValue()){
			ModelAndView view=new ModelAndView("redirect:/registro");
			return view;
		}
		
		//Buscar lista de proveedores
		List<UsuarioVO> usuarios = null;
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();

		String result = restTemplate.getForObject("http://localhost:8080/logica/admin/", String.class);
		try {
			usuarios = mapper.readValue(result, new TypeReference<List<UsuarioVO>>(){});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		ModelAndView modelAndView = new ModelAndView(ADMIN);
		modelAndView.addObject("usuarioAutenticado",utilidades.getSessionUser());
		modelAndView.addObject("proveedores", usuarios);
		modelAndView.addObject("moduloMensajeria", Constantes.MODULO_MENSAJERIA);
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/eliminar/{id}", method = RequestMethod.GET)
	public ModelAndView eliminarProveedor(@PathVariable("id")int id, ModelMap model){	
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getForObject("http://localhost:8080/logica/admin/eliminar/"+id, String.class);
		
		ModelAndView view = new ModelAndView("redirect:/admin/");
		return view;
	}
		
	@RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
	public ModelAndView editarProovedorGET(@PathVariable("id")int id, ModelMap model){	
		
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		UsuarioVO proveedor = null;
		
		String usuario = restTemplate.getForObject("http://localhost:8080/logica/admin/getProveedor/"+id, String.class);
		try {
			proveedor = mapper.readValue(usuario, UsuarioVO.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		ModelAndView modelAndView = new ModelAndView(EDITPROOV);
		if(utilidades.getPerfil()==1){
			modelAndView.addObject("esProveedor","es proveedor");
		}
		modelAndView.addObject("usuarioAutenticado",utilidades.getSessionUser());
		modelAndView.addObject("proveedor", proveedor);
		modelAndView.addObject("moduloMensajeria", Constantes.MODULO_MENSAJERIA);
		return modelAndView;
	}
	
	@RequestMapping(value = "/editar/{id}", method = RequestMethod.POST)
	public ModelAndView editarProveedorPOST(@PathVariable("id")int id, @ModelAttribute ("proveedor") UsuarioVO user){	
				
		String resultado="";
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		
		user.setIdusuario(id);
		
		String uri = "http://localhost:8080/logica/admin/update";
		String result = (String) restTemplate.postForObject(uri,user, String.class);

		try {
			resultado = mapper.readValue(result, new TypeReference<List<UsuarioVO>>(){});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		if(utilidades.getPerfil()==1){
			ModelAndView view = new ModelAndView("redirect:/admin/editar/"+utilidades.getSessionIdUser());
			return view;	
		}else{
			ModelAndView view = new ModelAndView("redirect:/admin/");
			return view;
		}
	}
	
	
	@RequestMapping(value = "/nuevo/", method = RequestMethod.GET)
	public ModelAndView nuevoProveedor(){	
		
		UsuarioVO user = new UsuarioVO();
		ModelAndView modelAndView = new ModelAndView(NUEVOPROV);
		modelAndView.addObject("usuarioAutenticado",utilidades.getSessionUser());
		modelAndView.addObject("proveedor",user);
		modelAndView.addObject("moduloMensajeria", Constantes.MODULO_MENSAJERIA);
		return(modelAndView);
	}
	
	
	@RequestMapping(value = "/nuevo", method = RequestMethod.POST)
	public ModelAndView crearProveedor(@ModelAttribute ("proveedor") UsuarioVO user){	
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		
		String uri = "http://localhost:8080/logica/admin/crear/";
		String resultado = (String) restTemplate.postForObject(uri, user, String.class);
	 
		ModelAndView modelAndView = new ModelAndView(NUEVOPROV);
		modelAndView.addObject("usuarioAutenticado",utilidades.getSessionUser());
		modelAndView.addObject("proveedor",user);
		modelAndView.addObject("mensaje",resultado);		
		modelAndView.addObject("moduloMensajeria", Constantes.MODULO_MENSAJERIA);
		return modelAndView;	
		}
	
	@RequestMapping(value = "/solicitudes-baja", method = RequestMethod.GET)
	public ModelAndView darSolicitudesBaja(){	
		
		List<UsuarioVO> proveedores = null;
		RestTemplate restTemplate = new RestTemplate();
		ObjectMapper mapper = new ObjectMapper();
		
		String uri = "http://localhost:8080/logica/admin/solicitudes-baja/";
		String result = (String) restTemplate.getForObject(uri, String.class);

		try {
			proveedores = mapper.readValue(result, new TypeReference<List<UsuarioVO>>(){});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		ModelAndView modelAndView = new ModelAndView(ADMIN);
		modelAndView.addObject("usuarioAutenticado",utilidades.getSessionUser());
		modelAndView.addObject("mensaje","Solicitudes de baja");
		modelAndView.addObject("proveedores",proveedores);
		modelAndView.addObject("moduloMensajeria", Constantes.MODULO_MENSAJERIA);
		return modelAndView;	
	}
	
	
	@RequestMapping(value = "/transacciones", method = RequestMethod.GET)
	public ModelAndView darTransacciones(){	
		
		List<TransaccionesVO> transacciones = null;
		RestTemplate restTemplate = new RestTemplate();
		ObjectMapper mapper = new ObjectMapper();
		
		String uri = "http://localhost:8080/logica/admin/transacciones/";
		String result = (String) restTemplate.getForObject(uri, String.class);
		String fechas = "[";
		String fechaAnt = "";
		String candidato = "";
		int contador = 0;
		
		try {
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
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println(fechas);
		
		ModelAndView modelAndView = new ModelAndView(TRANSACCIONES);
		modelAndView.addObject("usuarioAutenticado",utilidades.getSessionUser());
		modelAndView.addObject("transacciones",transacciones);		
		modelAndView.addObject("fechas",fechas);		
		modelAndView.addObject("moduloMensajeria", Constantes.MODULO_MENSAJERIA);
		return modelAndView;	
	}
	
	
	
	
	@RequestMapping(value = "/categorias/", method = RequestMethod.GET)
	public ModelAndView categorias() {
		ModelAndView modelAndView = new ModelAndView(CATEGORIAS);
		modelAndView.addObject("usuarioAutenticado",utilidades.getSessionUser());
		modelAndView.addObject("moduloMensajeria", Constantes.MODULO_MENSAJERIA);
		return modelAndView;

	}
	
	@RequestMapping(value = "/categorias/", method = RequestMethod.POST)
	public ModelAndView categoriasPOST(
			@RequestParam(value="nombre", required=true) String nombre,
			@RequestParam(value="atributo", required=true)String atributo){
		
		System.out.println("Est· entrando: "+nombre+" & "+atributo);
		
		RestTemplate restTemplate = new RestTemplate();
		ObjectMapper mapper = new ObjectMapper();
		String resultado = "";
		
		String uri = "http://localhost:8080/logica/admin/nuevaCategoria/"+nombre+"/"+atributo;
		String result = (String) restTemplate.getForObject(uri, String.class);

		try {
			resultado = mapper.readValue(result, String.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		ModelAndView modelAndView = new ModelAndView(CATEGORIAS);
		modelAndView.addObject("usuarioAutenticado",utilidades.getSessionUser());
		modelAndView.addObject("moduloMensajeria", Constantes.MODULO_MENSAJERIA);
		return modelAndView;
		
	}

	
}
