package utilidades;

import javax.servlet.http.HttpSession;

import org.hamcrest.core.IsInstanceOf;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class utilidades {
	
	/**Recupera el nombre y apellido del usuario Autenticado*/
	
	public static String getSessionUser(){
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true);
		String usuario = (String) session.getAttribute("user");
	    
		return usuario;
	}
	/**Recupera el id del usuario autenticado*/
	public static Integer getSessionIdUser(){
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true);
		Integer id = new Integer( session.getAttribute("userId").toString());
		return id;
	}
	/** Retorna si hay un usuario Autenticado*/
	public static Boolean isUserAutenticado(){
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true);
		
		if(session.getAttribute("userId")==null){
			return false;
		}
		return true;
	}
	
	/** Utilidad para validar si un usuario puede calificar un servicio*/
	public static String getPermisos(Integer idServicio){
		if(isUserAutenticado()){
			Integer idUser = getSessionIdUser();
			RestTemplate restTemplate = new RestTemplate();
			String result = restTemplate.getForObject("http://localhost:8080/logica/pagos/validarCompra/"+ idServicio+"/" +idUser, String.class);
			
			return result;
		}else{
			return "Nok";
		}
	}
	
	public static int getPerfil(){
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true);
		Integer id = new Integer( session.getAttribute("userPerfil").toString());
		return id;
	}

	/**Recupera el id del usuario autenticado*/
	public static Integer getIdUser(){
		if(isUserAutenticado()){
			return getSessionIdUser();
		}else{
			return null;
		}
	}
}
