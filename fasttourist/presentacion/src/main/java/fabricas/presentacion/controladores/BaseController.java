package fabricas.presentacion.controladores;
 
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import utilidades.Constantes;
import utilidades.EnumPerfiles;
import utilidades.utilidades;

import com.fasterxml.jackson.databind.ObjectMapper;

import fabricas.presentacion.VOs.PerfilesVO;
import fabricas.presentacion.VOs.UsuarioVO;

@Controller
public class BaseController {


	private static final String CONTACTENOS = "contactenos";
	private static final String REGISTRO = "registro";
	
	/**
	 * Controlador de la pantalla Contactenos
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/contactenos", method = RequestMethod.GET)
	public ModelAndView contactenos(ModelMap model) {
	
		ModelAndView modelAndView = new ModelAndView(CONTACTENOS);
		modelAndView.addObject("usuarioAutenticado",utilidades.getSessionUser());
		modelAndView.addObject("moduloMensajeria", Constantes.MODULO_MENSAJERIA);
		return modelAndView;
	}
	
	/**
	 * Controlador de la pantalla de Registro
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/registro", method = RequestMethod.GET)
	public ModelAndView registrarse(ModelMap model) {
		ModelAndView modelAndView = new ModelAndView(REGISTRO);
		modelAndView.addObject("usuario", new UsuarioVO());
		modelAndView.addObject("usuarioAutenticado",utilidades.getSessionUser());
		modelAndView.addObject("moduloMensajeria", Constantes.MODULO_MENSAJERIA);
		modelAndView.addObject("moduloBusquedas", Constantes.MODULO_BUSQUEDAS);
		return modelAndView;
	}
	
	@RequestMapping(value = "/registro", method = RequestMethod.POST)
	public ModelAndView registro(@ModelAttribute("usuario") UsuarioVO usuario){
		
		String response = null;
		RestTemplate restTemplate = new RestTemplate();
		ObjectMapper mapper = new ObjectMapper();
		
		//se setea por defecto el perfil de cliente
		PerfilesVO perfil = new PerfilesVO();
		perfil.setIdperfil(EnumPerfiles.CLIENTE.getValue());
		usuario.setPerfil(perfil);
		usuario.setActivo(true);
		
		response = restTemplate.postForObject("http://localhost:8080/logica/registro/",usuario,String.class);
		
		try {
			usuario = mapper.readValue(response, UsuarioVO.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		if(usuario.getEmail()!=null && !usuario.getEmail().isEmpty())
		{
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession(true);
			session.setAttribute("user", usuario.getNombre() + " " + usuario.getApellido());
			session.setAttribute("userId", usuario.getIdusuario());
			session.setAttribute("userCorreo", usuario.getEmail());
			session.setAttribute("userPerfil", usuario.getPerfil().getIdperfil());
			response= "Se ha registrado con éxito";
		}else{
			response= "Ya hay un usuario registrado con ese correo!";
		}
				
		ModelAndView modelAndView = new ModelAndView(REGISTRO);
		modelAndView.addObject("response", response);
		modelAndView.addObject("usuario", usuario);
		modelAndView.addObject("usuarioAutenticado",utilidades.getSessionUser());
		modelAndView.addObject("moduloMensajeria", Constantes.MODULO_MENSAJERIA);
		modelAndView.addObject("moduloBusquedas", Constantes.MODULO_BUSQUEDAS);
		return modelAndView;
		
	}
	
	
	@RequestMapping(value="/registro/auth", method = RequestMethod.POST)
	public ModelAndView autenticacion(
		@RequestParam(value="correo", required=true) String correo, 
		@RequestParam(value="password", required=true) String password){
		
		String response = null;
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		UsuarioVO usuario=null;
		
		String url = correo+","+password;
		response = restTemplate.getForObject("http://localhost:8080/logica/registro/auth/"+url+"/", String.class);
		
		try {
			usuario = mapper.readValue(response, UsuarioVO.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		if(usuario.getEmail()!=null && !usuario.getEmail().isEmpty())
		{			
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession(true);
			session.setAttribute("user", usuario.getNombre() + " " + usuario.getApellido());
			session.setAttribute("userId", usuario.getIdusuario());
			session.setAttribute("userCorreo", usuario.getEmail());
			session.setAttribute("userPerfil", usuario.getPerfil().getIdperfil());
			response= "Te has autenticado correctamente";
			
			//Si se autenticó un proveedor, se redirecciona a index del proveedor
			if(usuario.getPerfil().getIdperfil()==EnumPerfiles.PROVEEDOR.getValue()){
				ModelAndView view=new ModelAndView("redirect:/adminProveedor/");
				return view;
			}
			
			//Si es Administrador
			if(usuario.getPerfil().getIdperfil()==EnumPerfiles.ADMINISTRADOR.getValue()){
				ModelAndView view=new ModelAndView("redirect:/admin/");
				return view;
			}
			
			
		}else{
			response= "Error de autenticación. Revisa correo y/o contraseña";
		}
		
		ModelAndView modelAndView = new ModelAndView(REGISTRO);
		modelAndView.addObject("response", response);
		modelAndView.addObject("usuario", new UsuarioVO());
		modelAndView.addObject("usuarioAutenticado",utilidades.getSessionUser());
		modelAndView.addObject("moduloMensajeria", Constantes.MODULO_MENSAJERIA);
		modelAndView.addObject("moduloBusquedas", Constantes.MODULO_BUSQUEDAS);
		return modelAndView;
	}
	
}