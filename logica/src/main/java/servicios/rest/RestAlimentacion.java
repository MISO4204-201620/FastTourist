package servicios.rest;


import javax.persistence.EntityManager;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fabricas.entidades.Alimentacion;


@RestController
@RequestMapping("/alimentacion")
public class RestAlimentacion {
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Alimentacion> getAlimentacionById(@PathVariable int id) {

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();
		
		Alimentacion result = (Alimentacion) em.createNamedQuery("Alimentacion.findById")
				.setParameter("id", id)
				.getSingleResult();
						
		return new ResponseEntity<Alimentacion>(result, HttpStatus.OK);
	}

}
