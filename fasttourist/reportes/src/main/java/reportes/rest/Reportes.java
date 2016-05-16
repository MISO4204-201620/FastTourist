package reportes.rest;

import java.util.ArrayList;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fabricas.entidades.Busquedas;
import fabricas.entidades.Transacciones;

@RestController
@RequestMapping("/")

public class Reportes extends AbsReportes{

	//Busqueda	
	@SuppressWarnings("unchecked")
	@Override
	public ResponseEntity<ArrayList<Busquedas>> getBusquedas(int idProveedor) {

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();
		
		ArrayList<Busquedas> array = new ArrayList<>();
		array = (ArrayList<Busquedas>) em.createQuery("SELECT b FROM Busquedas b where"
		+ " b.id.usuario = "+idProveedor)
				.getResultList();
		
		return new ResponseEntity<ArrayList<Busquedas>> (array, HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<ArrayList<Transacciones>> getTransacciones(int idProveedor) {
		
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();
		
		ArrayList<Transacciones> array = new ArrayList<>();
		array = (ArrayList<Transacciones>) em.createQuery("SELECT b FROM Transacciones b " 
				+ "WHERE b.servicio.usuario = "+idProveedor)
				.getResultList();
		
		return new ResponseEntity<ArrayList<Transacciones>> (array, HttpStatus.OK);
	}
	
	
}
