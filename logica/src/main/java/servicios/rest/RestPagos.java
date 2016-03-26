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

import fabricas.entidades.Carrito;
import fabricas.entidades.EstadoTransaccion;
import fabricas.entidades.Transacciones;


@RestController
@RequestMapping("/pagos")
public class RestPagos {


	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/checkout/{id}", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE +"; charset=UTF-8"})
	public ResponseEntity<List<Carrito>> getCheckout(@PathVariable int id) {

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();

		
		List<Carrito> carrito = em.createNamedQuery("Carrito.findByUser")
				.setParameter("id", id)
				.getResultList();

		return new ResponseEntity<List<Carrito>>(carrito, HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE +"; charset=UTF-8"})
	public String removeCarrito(@PathVariable String id) {

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();

		
		Carrito carrito = (Carrito) em.createNamedQuery("Carrito.findById")
				.setParameter("id", new Integer(id))
				.getSingleResult();

		em.remove(carrito);
		em.getTransaction().commit();
		return "ok";
	}
	
	@RequestMapping(value = "/setCheckout/{id}", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE +"; charset=UTF-8"})
	public String setCheckout(@PathVariable int id) {

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();

		// obtiene los servicios ha comprar del carrito
		@SuppressWarnings("unchecked")
		List<Carrito> carrito = em.createNamedQuery("Carrito.findByUser")
				.setParameter("id", id)
				.getResultList();
		//Se obtiene estado de trasnsaccion exitosa
		EstadoTransaccion estado = (EstadoTransaccion) em.createNamedQuery("EstadoTransaccion.findById").setParameter("id", 1).getSingleResult();
		int cont = (int) em.createNamedQuery("Transacciones.getMaxId").getSingleResult();
		//Se crean las transacciones de los servivios comprados
		Transacciones t;
		cont +=1;
		for (Carrito carro : carrito) {
			t = new Transacciones();
			t.setUsuario(carro.getUsuario());
			t.setServicio(carro.getServicio());
			t.setFecha(new Date());
			t.setIdtransacciones(cont);
			t.setEstadoTransaccion(estado);
			em.persist(t);
			cont ++;
		}
		
		//Se limpia el carrito de compras del usuario
		for (Carrito carro : carrito) {
			em.remove(carro);
		}
		em.getTransaction().commit();
		return "ok";
	}


}