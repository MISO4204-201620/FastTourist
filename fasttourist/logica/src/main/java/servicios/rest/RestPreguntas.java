package servicios.rest;


import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import fabricas.entidades.Preguntas;
import fabricas.entidades.Servicio;


@RestController
@RequestMapping("/preguntas")
public class RestPreguntas {

	@RequestMapping(value = "/set/{pregunta}/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String setPregunta(@PathVariable int id,@PathVariable String pregunta) {

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();
		
		Servicio servicio = (Servicio) em.createNamedQuery("Servicio.findById")
				.setParameter("id", id)
				.getSingleResult();
		
		Preguntas p = new Preguntas();
		p.setFechaCreacion(new Date());
		p.setPregunta(pregunta+"?");
		p.setServicio(servicio);
		
		em.persist(p);
		em.getTransaction().commit();
		em.close();
		return "ok";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Preguntas>> getAllByUserId(@PathVariable int id) {

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();
		
		List<Preguntas> preguntas = (List<Preguntas>) em.createNamedQuery("Preguntas.findByUserId")
				.setParameter("id", id)
				.getResultList();
		em.close();
		return new ResponseEntity<List<Preguntas>>(preguntas, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/set/{respuesta}/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> setRespuesta(@PathVariable int id, @PathVariable String respuesta) {

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();
		
		Preguntas pregunta = em.find(Preguntas.class, id);
		
		if(pregunta == null)
		{
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
		pregunta.setRespuesta(respuesta);
		
		em.persist(pregunta);
		em.getTransaction().commit();
		em.close();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}