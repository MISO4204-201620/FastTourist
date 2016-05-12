package servicios.rest;


import java.util.Date;

import javax.persistence.EntityManager;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import fabricas.entidades.Calificaciones;
import fabricas.entidades.Servicio;
import fabricas.entidades.Usuario;


@RestController
@RequestMapping("/calificaciones")
public class RestCalificaciones {

	@RequestMapping(value = "/set/{valor}/{comentario}/{id}/{idUsuario}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String setCalificacion(@PathVariable int id,@PathVariable int idUsuario,@PathVariable int valor,@PathVariable String comentario) {

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();
		
		Servicio servicio = (Servicio) em.createNamedQuery("Servicio.findById")
				.setParameter("id", id)
				.getSingleResult();
		Usuario usuario = (Usuario) em.createNamedQuery("Usuario.findById")
				.setParameter("id", idUsuario)
				.getSingleResult();
		
		Calificaciones  c = new Calificaciones();
		c.setComentario(comentario);
		c.setFecha(new Date());
		c.setServicio(servicio);
		c.setUsuario(usuario);
		c.setValor(valor);
		
		em.persist(c);
		em.getTransaction().commit();
		em.close();
		return "ok";
	}
}