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

import fabricas.entidades.Servicio;
import fabricas.entidades.Tipoalimentacion;
import fabricas.entidades.Usuario;


@RestController
@RequestMapping("/alimentacion")
public class RestAlimentacion {
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getProveedores2", method=RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE +"; charset=UTF-8"})
	public ResponseEntity<List<Usuario>> getProveedores(){
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();
		
		List<Usuario> proveedores = (List<Usuario>) em.createNamedQuery("Servicio.findProveedoresByAlimentacion").getResultList();
		em.close();
		return new ResponseEntity<List<Usuario>>(proveedores, HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getServiciosAlimentacion2", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE +"; charset=UTF-8"})
	public ResponseEntity<List<Servicio>> getAllAlimentacion() {
		
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();
		
		List<Servicio> serviciosAlimentacion = (List<Servicio>) em.createNamedQuery("Servicio.findAlimentacion")		
				.getResultList();
		em.close();
		return new ResponseEntity<List<Servicio>>(serviciosAlimentacion, HttpStatus.OK);
	}	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/gettipos", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE +"; charset=UTF-8"})
	public ResponseEntity <List<Tipoalimentacion>> getTiposAlimentacion() {
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();

		List<Tipoalimentacion> tipos;
		
		tipos = (List<Tipoalimentacion>) em.createNamedQuery("Tipoalimentacion.findAll").getResultList();
		em.close();
		return new ResponseEntity <List<Tipoalimentacion>> (tipos, HttpStatus.OK);

	}
	
	/*@SuppressWarnings("unchecked")
	@RequestMapping(value="/", method=RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE +"; charset=UTF-8"})
	public ResponseEntity<List<Servicio>> getAlimentacion(){
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();
		
		List<Servicio> servicios = (List<Servicio>) em.createNamedQuery("Servicio.findAlimentacion").getResultList();
		
		return new ResponseEntity<List<Servicio>>(servicios, HttpStatus.OK);
	}*/
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/{filtros}", method=RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE +"; charset=UTF-8"})
	public ResponseEntity<List<Servicio>> getAlimentacionByFilters(@PathVariable String filtros){
		
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();
		
		String query = queryAlimentacionByFilters(filtros); 
		
		List<Servicio> servicios = (List<Servicio>) em.createQuery(query).getResultList();
		em.close();
		return new ResponseEntity<List<Servicio>>(servicios, HttpStatus.OK);
	}	
	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE +"; charset=UTF-8"})
	public ResponseEntity<Servicio> getAlimentacionById(@PathVariable int id) {

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();
		
		Servicio alimentacion = (Servicio) em.createNamedQuery("Servicio.findAlimentacionById")
				.setParameter("id", id)
				.getSingleResult();
		em.close();
				
		return new ResponseEntity<Servicio>(alimentacion, HttpStatus.OK);
	}

	private String queryAlimentacionByFilters(String filtros) {
		//Se recuperan los filtros de busqueda
		String proveedor="";
		String tipoAlimentacion="";		

		for (String filtro : filtros.split(",")) {
			String[]values = filtro.split("=");
			if(values.length==2){
				if(values[0].equals("proveedor")){
					proveedor=values[1];				
				}else if(values[0].equals("tipoAlimentacion")){
					tipoAlimentacion=values[1];
				}
			}
		}
		
		String query = "SELECT s FROM Servicio s WHERE s.activo=1 AND s.categoria=2";
		
		if(!proveedor.isEmpty()){
			query = query + " AND UPPER(s.usuario) LIKE UPPER('%"+proveedor+"%')";
		}
		if(!tipoAlimentacion.isEmpty()){
			query = query + " AND UPPER(s.alimentacion.nombre) LIKE UPPER('%"+tipoAlimentacion+"%')";
		}	
		
		System.out.println(query);

		return query;
	}
}
