package mensajeria.rest;


import java.util.Date;
import java.util.List;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import fabricas.entidades.*;
import fabricas.entidades.VOs.MensajeriaVO;
import fabricas.mensajeria.ServiciosMensajeria;


@RestController
@RequestMapping("/")
public class RestMensajeria {
	private static final String DECODE="; charset=UTF-8";
	
	//Crear nuevo mensaje
	@RequestMapping(value = "/", 
			method = RequestMethod.POST, 
			produces={MediaType.APPLICATION_JSON_VALUE +DECODE})
	public ResponseEntity<Mensajeria> mensajes(@RequestBody  MensajeriaVO mensaje) {
		ServiciosMensajeria servicio = new ServiciosMensajeria();
		
		//Se crea la entidad mensaje
		Mensajeria msg = new Mensajeria();
		msg.setAsunto(mensaje.getAsunto());
		msg.setEstado(mensaje.getEstado());
		msg.setFecha(new Date());
		msg.setMensaje(mensaje.getMensaje());
		
		Usuario destinatario = new Usuario();
		destinatario.setIdusuario(mensaje.getDestinatario().getIdusuario());
		Usuario remitente = new Usuario();
		remitente.setIdusuario(mensaje.getRemitente().getIdusuario());
		msg.setDestinatario(destinatario);
		msg.setRemitente(remitente);
		
		Mensajeria enviado = servicio.enviarMensaje(msg);
		return new ResponseEntity<Mensajeria>(enviado, HttpStatus.OK);
	}

	//Obtener el detalle de un mensaje
	@RequestMapping(value = "/{id}", 
			method = RequestMethod.GET, 
			produces={MediaType.APPLICATION_JSON_VALUE +DECODE})
	public ResponseEntity<Mensajeria> mensajes(@PathVariable int id) {
		ServiciosMensajeria servicio = new ServiciosMensajeria();
		Mensajeria mensaje = servicio.getMensaje(id);
		return new ResponseEntity<Mensajeria>(mensaje, HttpStatus.OK);
	}
	
	//Actualizar mensaje
	@RequestMapping(value = "/{id}", 
			method = RequestMethod.PUT, 
			produces={MediaType.APPLICATION_JSON_VALUE +DECODE})
	public ResponseEntity<String> mensajes(@PathVariable int id,@RequestBody  Object servicioVO) {
		System.out.println("Mensajes");
		return new ResponseEntity<String>("sii", HttpStatus.OK);
	}
	
	//Lista todos los mensajes del usuario
	@RequestMapping(value = "/{id}/destinatario", 
			method = RequestMethod.GET, 
			produces={MediaType.APPLICATION_JSON_VALUE +DECODE})
	public ResponseEntity<List<Mensajeria>> mensajesRecibidosUsuario(@PathVariable int id) {
		ServiciosMensajeria servicio = new ServiciosMensajeria();
		List<Mensajeria> Mensajes = servicio.cargarMensajesRecibidos(id);
		return new ResponseEntity<List<Mensajeria>>(Mensajes, HttpStatus.OK);
	}
	
	//Lista todos los mensajes del usuario
	@RequestMapping(value = "/{id}/remitente", 
			method = RequestMethod.GET, 
			produces={MediaType.APPLICATION_JSON_VALUE +DECODE})
	public ResponseEntity<List<Mensajeria>> mensajesEnviadosUsuario(@PathVariable int id) {
		ServiciosMensajeria servicio = new ServiciosMensajeria();
		List<Mensajeria> Mensajes = servicio.cargarMensajesEnviados(id);
		return new ResponseEntity<List<Mensajeria>>(Mensajes, HttpStatus.OK);
	}
}
