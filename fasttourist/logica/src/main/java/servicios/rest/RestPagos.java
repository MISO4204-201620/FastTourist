package servicios.rest;


import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import fabricas.entidades.Carrito;
import fabricas.entidades.EstadoTransaccion;
import fabricas.entidades.Servicio;
import fabricas.entidades.Transacciones;
import fabricas.entidades.Usuario;


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
		em.close();
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
		em.close();
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
		em.close();
		return "ok";
	}


	@RequestMapping(value = "/addCarrito/{idServicio}/{idUsuario}", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE +"; charset=UTF-8"})
	public String addCarito(@PathVariable int idServicio, @PathVariable int idUsuario) {

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();

		Carrito carrito = new Carrito();
		carrito.setFechaAnadido(new Date());
		Servicio servicio = new Servicio();
		servicio.setIdservicios(idServicio);
		carrito.setServicio(servicio);
		Usuario usuario= new Usuario();
		usuario.setIdusuario(idUsuario);
		carrito.setUsuario(usuario);
		em.persist(carrito);
		em.getTransaction().commit();
		em.close();
		return "El servicio se ha agregado a carrito";
	}

	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/validarCompra/{idServicio}/{idUser}", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE +"; charset=UTF-8"})
	public String validateCheckoutByUser(@PathVariable int idServicio,@PathVariable int idUser) {

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();

		List<Carrito> carrito = em.createNamedQuery("Transacciones.getByUser").
					setParameter("idUser", idUser).
					setParameter("idServicio", idServicio).getResultList();
		em.close();
		if(carrito == null || carrito.isEmpty()){
			return "Nok";
		}else{
			return "ok";
		}

		
	}
}