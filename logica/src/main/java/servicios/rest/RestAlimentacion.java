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
import fabricas.entidades.Usuario;


@RestController
@RequestMapping("/alimentacion")
public class RestAlimentacion {
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getProveedores", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Usuario>> getProveedores(){
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();
		
		List<Usuario> proveedores = (List<Usuario>) em.createNamedQuery("Servicio.findProveedoresByAlimentacion").getResultList();
		
		return new ResponseEntity<List<Usuario>>(proveedores, HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Servicio>> getAlimentacion(){
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();
		
		List<Servicio> servicios = (List<Servicio>) em.createNamedQuery("Servicio.findAlimentacion").getResultList();
		
		return new ResponseEntity<List<Servicio>>(servicios, HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/{filtros}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Servicio>> getAlimentacionByFilters(@PathVariable String filtros){
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();
		
		String query = queryAlimentacionByFilters(filtros); 
		
		List<Servicio> servicios = (List<Servicio>) em.createQuery(query).getResultList();
		
		return new ResponseEntity<List<Servicio>>(servicios, HttpStatus.OK);
	}	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Servicio> getAlimentacionById(@PathVariable int id) {

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();
		
		Servicio result = (Servicio) em.createNamedQuery("Servicio.findById")
				.setParameter("id", id)
				.getSingleResult();
						
		return new ResponseEntity<Servicio>(result, HttpStatus.OK);
	}

	private String queryAlimentacionByFilters(String filtros) {
		//Se recuperan los filtros de busqueda
		String ciudad="";
		String proveedor="";
		String tipoAlimentacion="";		

		for (String filtro : filtros.split(",")) {
			String[]values = filtro.split("=");
			if(values.length==2){
				if (values[0].equals("ciudad")){
					ciudad=values[1];
				}else if(values[0].equals("proveedor")){
					proveedor=values[1];
				}else if(values[0].equals("tipoAlimentacion")){
					tipoAlimentacion=values[1];
				}
			}
		}
		
		String query = "SELECT s FROM Servicio s WHERE s.activo=1 AND s.categoria=1 ";

		if (!ciudad.isEmpty()){
			query = query + "AND (UPPER(s.alimentacion.ciudad) like UPPER('%" + ciudad +"%') ";
			query = query + "OR UPPER(s.alimentacion.nombre) like UPPER('%" + ciudad +"%') ";
			query = query + "OR UPPER(s.nombre) like UPPER('%" + ciudad +"%')) ";
		}
		if(!proveedor.isEmpty()){
			query = query + "AND s.usuario="+proveedor+" ";
		}
		if(!tipoAlimentacion.isEmpty()){
			query = query + "AND s.alimentacion.nombre = "+tipoAlimentacion+" ";
		}		

		return query;
	}
}
