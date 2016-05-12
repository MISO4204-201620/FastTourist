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
	@RequestMapping(value = "/get/{id}/", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE +"; charset=UTF-8"})
	public ResponseEntity<List<Transacciones>> getTransacciones(@PathVariable int id) {

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();

		List<Transacciones> tr = (List<Transacciones>) em.createNamedQuery("Transacciones.findByUserId")
				.setParameter("id", id)
				.getResultList();
		em.close();
		return new ResponseEntity<List<Transacciones>>(tr, HttpStatus.OK);
	} 

}
