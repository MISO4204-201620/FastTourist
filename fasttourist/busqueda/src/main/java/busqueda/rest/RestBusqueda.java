package busqueda.rest;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fabricas.entidades.Busquedas;
import fabricas.entidades.Servicio;
import fabricas.entidades.Usuario;

@RestController
@RequestMapping("/")
public class RestBusqueda {

	private static final String DECODE = "; charset=UTF-8";

	@RequestMapping(value = "/", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE
			+ DECODE })
	public ResponseEntity<String> setBusqueda(
			@RequestBody Map<String, Integer> mapa) {

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();

		Busquedas busquedas = new Busquedas();

		busquedas.setFecha(new Date());
		Usuario usuario = new Usuario();
		usuario.setIdusuario(mapa.get("idUser"));
		busquedas.setUsuario(usuario);
		Servicio servicio = new Servicio();
		servicio.setIdservicios(mapa.get("idService"));
		busquedas.setServicio(servicio);

		em.persist(busquedas);
		em.getTransaction().commit();
		em.close();

		return new ResponseEntity<String>(HttpStatus.OK);

	}

	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = "/{idUser}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE
			+ DECODE })
	public ResponseEntity<List<Busquedas>> getBusquedas(
			@PathVariable("idUser") int iduser) {

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();

		List<Busquedas> busquedas = (List<Busquedas>) em
				.createNamedQuery("Busquedas.findByUser")
				.setParameter("iduser", iduser).getResultList();

		return new ResponseEntity<List<Busquedas>>(busquedas, HttpStatus.OK);

	}
}
