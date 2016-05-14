package servicios.rest;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fabricas.entidades.Transacciones;

@RestController
@RequestMapping("/transacciones")
public class RestTransaccion {
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getByProvider/{id}", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE +"; charset=UTF-8"})
	public ResponseEntity<List<Transacciones>> getTransacciones(@PathVariable int id) {

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();

		List<Transacciones> tr = (List<Transacciones>) em.createNamedQuery("Transacciones.findByProvider")
				.setParameter("id", id)
				.getResultList();
		
		
		return new ResponseEntity<List<Transacciones>>(tr, HttpStatus.OK);
	} 

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getDistinct/{id}/", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE +"; charset=UTF-8"})
	public ResponseEntity<List<String>> getTransaccionesDistinct(@PathVariable int id) {

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();

		List<String> tr = em.createNamedQuery("Transacciones.findAllDistinct")
				.setParameter("id", id)
				.getResultList();
		
		System.out.println("Envio rta");

		return new ResponseEntity<List<String>>(tr, HttpStatus.OK);
	}	 

}
