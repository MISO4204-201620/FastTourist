package busqueda.rest;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fabricas.entidades.Busquedas;

@RestController
@RequestMapping("/")
public class RestBusqueda {
	
	private static final String DECODE="; charset=UTF-8";
	
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE +DECODE})
	public ResponseEntity <List<Busquedas>> getBusquedas(@PathVariable("id") int iduser) {

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();

		List<Busquedas> busquedas = (List<Busquedas>) em.createNamedQuery("Busquedas.findByUser")
				.setParameter("iduser", iduser)
				.getResultList();

		return new ResponseEntity <List<Busquedas>> (busquedas, HttpStatus.OK);
		
	}


}
