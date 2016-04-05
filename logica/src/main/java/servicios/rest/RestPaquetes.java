package servicios.rest;


import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fabricas.entidades.Paquete;
import fabricas.entidades.Servicio;


@RestController
@RequestMapping("/paquetes")
public class RestPaquetes {
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE +"; charset=UTF-8"})
	public ResponseEntity <List<Paquete>> getPaquetes() {

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();

		List<Paquete> paquetes;

		paquetes = (List<Paquete>) em.createNamedQuery("Paquete.findAll").getResultList();

		return new ResponseEntity <List<Paquete>> (paquetes, HttpStatus.OK);

	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getServicios/{id}", 
	method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE +"; charset=UTF-8"})
	public ResponseEntity <List<Servicio>> getServicios(@PathVariable int id) {

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();

		List<Servicio> servicios  = em.createNamedQuery("Servicio.findPaquetes")
				.setParameter("id", id)
				.getResultList();

		return new ResponseEntity <List<Servicio>> (servicios, HttpStatus.OK);

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/{filtros}", 
	method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE +"; charset=UTF-8"})
	public ResponseEntity <List<Paquete>> getPaquetesByFilter(@PathVariable String filtros) {

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();

		String query= queryPaquetesByFilter(filtros);	

		List<Paquete> paquetes  = em.createQuery(query).getResultList();

		return new ResponseEntity <List<Paquete>> (paquetes, HttpStatus.OK);

	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE +"; charset=UTF-8"})
	public ResponseEntity<Paquete> getPaquete(@PathVariable int id) {

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();
		
		Paquete paquete = (Paquete) em.createNamedQuery("Paquete.findById")
				.setParameter("id", id)
				.getSingleResult();

		return new ResponseEntity<Paquete>(paquete, HttpStatus.OK);
	}
	
	/**
	 * Metodo encargado de generar el query a partir 
	 * de la lista de parametros recibidos
	 * @param filtros
	 * @return
	 */
	private String queryPaquetesByFilter(String filtros){

		//Se recuperan los filtros de busqueda		
		String fechaEntrada="";
		String fechaSalida="";
		String origen="";
		String destino="";
		String tipoTransporte="";
		String tipoAlimentacion="";
		
		for (String filtro : filtros.split(",")) {
			String[]values = filtro.split("=");
			if(values.length==2){
				System.out.println(values[1]);
				if (values[0].equals("origen") && values[1] != "null"){
					origen=values[1];
				}else if(values[0].equals("destino") && values[1] != "null"){
					destino=values[1];
				}else if(values[0].equals("fechaEntrada") && values[1] != "null"){
					fechaEntrada=values[1];
				}else if(values[0].equals("fechaSalida") && values[1] != "null"){
					fechaSalida=values[1];
				}else if (values[0].equals("tipoTransporte")){
					tipoTransporte=values[1];
				}else if(values[0].equals("tipoAlimentacion")){
					tipoAlimentacion=values[1];
				}
			}
		}
		
		String queryBase = "SELECT DISTINCT(p) FROM Paquete p inner join p.servicios servicio ";
		
		String query = "";

		if (!origen.isEmpty()){
			if(query == ""){
				query = "WHERE";
			}else{
				query = query + " AND";
			}
			
			query = query + " (UPPER(servicio.transporte.origen) like UPPER('%" + origen +"%') ";
			query = query + "OR UPPER(servicio.transporte.nombre) like UPPER('%" + origen +"%') ";
			query = query + "OR UPPER(servicio.nombre) like UPPER('%" + origen +"%'))";
		}
		if(!destino.isEmpty()){
			if(query == ""){
				query = "WHERE";
			}else{
				query = query + " AND";
			}
			
			query = query + " (UPPER(servicio.transporte.destino) like UPPER('%" + destino +"%')) ";
		}		
		if(!fechaEntrada.isEmpty()){
			if(query == ""){
				query = "WHERE";
			}else{
				query = query + " AND";
			}
			
			query = query + " servicio.alojamiento.fechaEntrada <= '"+fechaEntrada+"' ";
		}
		if(!fechaSalida.isEmpty()){
			if(query == ""){
				query = "WHERE";
			}else{
				query = query + " AND";
			}
			
			query = query +" servicio.alojamiento.fechaSalida >= '"+fechaSalida+"' ";
		}
		if(!tipoTransporte.isEmpty()){
			if(query == ""){
				query = "WHERE";
			}else{
				query = query + " AND";
			}
			
			query = query + " servicio.transporte.tipotransporte.idtipotransporte = "+tipoTransporte+" ";
		}
		if(!tipoAlimentacion.isEmpty()){
			if(query == ""){
				query = "WHERE";
			}else{
				query = query + " AND";
			}
			
			query = query + " servicio.alimentacion.tipoalimentacion.idtipoAlimentacion="+tipoAlimentacion;
		}
		
		System.out.println(queryBase + query);

		return queryBase + query;
	}
}