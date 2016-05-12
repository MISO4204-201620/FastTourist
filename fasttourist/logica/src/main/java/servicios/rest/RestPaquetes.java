package servicios.rest;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import utilidades.EnumCategorias;
import fabricas.entidades.Alimentacion;
import fabricas.entidades.Alojamiento;
import fabricas.entidades.Categoria;
import fabricas.entidades.Paquete;
import fabricas.entidades.Paseosecologico;
import fabricas.entidades.Servicio;
import fabricas.entidades.Transporte;
import fabricas.entidades.Usuario;
import fabricas.entidades.VOs.PaqueteVO;
import fabricas.entidades.VOs.ServicioVO;


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

		em.close();
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

		em.close();
		return new ResponseEntity <List<Servicio>> (servicios, HttpStatus.OK);

	}

	/*@SuppressWarnings("unchecked")
	@RequestMapping(value = "/{filtros}", 
	method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE +"; charset=UTF-8"})
	public ResponseEntity <List<Paquete>> getPaquetesByFilter(@PathVariable String filtros) {

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();

		String query= queryPaquetesByFilter(filtros);	

		List<Paquete> paquetes  = em.createQuery(query).getResultList();

		return new ResponseEntity <List<Paquete>> (paquetes, HttpStatus.OK);

	}*/

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE +"; charset=UTF-8"})
	public ResponseEntity<Paquete> getPaquete(@PathVariable int id) {

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();
		
		Paquete paquete = (Paquete) em.createNamedQuery("Paquete.findById")
				.setParameter("id", id)
				.getSingleResult();

		em.close();
		return new ResponseEntity<Paquete>(paquete, HttpStatus.OK);
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getByProvider/{idProveedor}/", method = RequestMethod.GET, 
	produces = {MediaType.APPLICATION_JSON_VALUE +"; charset=UTF-8"})
	public ResponseEntity <List<Paquete>> getPaquetesByProveedor(@PathVariable int idProveedor) {

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();

		List<Paquete> paquetes;

		paquetes = (List<Paquete>) em.createNamedQuery("Paquete.findByProvider").setParameter("idProveedor", idProveedor).
				getResultList();

		em.close();
		return new ResponseEntity <List<Paquete>> (paquetes, HttpStatus.OK);

	}
	
	
	
	@RequestMapping(value = "/edit/", method = RequestMethod.POST,produces={MediaType.APPLICATION_JSON_VALUE +"; charset=UTF-8"})
	public ResponseEntity<Paquete> updatePaquete(@RequestBody  PaqueteVO paqueteVO){

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();

		Paquete paquete = em.find(Paquete.class, paqueteVO.getIdpaquetes());

		em.getTransaction().begin();
		
		paquete.setDescripcion(paqueteVO.getDescripcion().replaceAll("\\P{Print}", ""));
		paquete.setFechaCreacion(paqueteVO.getFechaCreacion());
		paquete.setFechaExpiracion(paqueteVO.getFechaExpiracion());
		paquete.setNombre(paqueteVO.getNombre());
		
		paquete.getServicios().clear();
		
		List <Servicio> servicios = new ArrayList<Servicio>();
		if(paqueteVO.getServicios() != null){
			for (ServicioVO servicioVO : paqueteVO.getServicios()) {
				if(servicioVO.getCheckPaquete()){
					Categoria c = new Categoria();
					c.setNombre(servicioVO.getCategoria().getNombre());
					Servicio s = new Servicio();
					s.setNombre(servicioVO.getNombre());
					s.setCategoria(c);
					s.setIdservicios(servicioVO.getIdservicios());
					servicios.add(s);
				}
			}
		}
		
		paquete.setServicios(servicios);
		
		em.persist(paquete);
		
		em.getTransaction().commit();
		em.close();
		return new ResponseEntity<Paquete>(paquete, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/create/", method = RequestMethod.POST,produces={MediaType.APPLICATION_JSON_VALUE +"; charset=UTF-8"})
	public ResponseEntity<Paquete> createPaquete(@RequestBody  PaqueteVO paqueteVO){

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();

		Paquete paquete = new Paquete();

		
		em.getTransaction().begin();
		
		paquete.setDescripcion(paqueteVO.getDescripcion().replaceAll("\\P{Print}", ""));
		paquete.setFechaCreacion(paqueteVO.getFechaCreacion());
		paquete.setFechaExpiracion(paqueteVO.getFechaExpiracion());
		paquete.setNombre(paqueteVO.getNombre());
		Usuario proveedor = new Usuario();
		proveedor.setIdusuario(paqueteVO.getUsuario().getIdusuario());
		paquete.setUsuario(proveedor);
		
		List <Servicio> servicios = new ArrayList<Servicio>();
		if(paqueteVO.getServicios() != null){
			for (ServicioVO servicioVO : paqueteVO.getServicios()) {
				if(servicioVO.getCheckPaquete()){
					Categoria c = new Categoria();
					c.setNombre(servicioVO.getCategoria().getNombre());
					Servicio s = new Servicio();
					s.setNombre(servicioVO.getNombre());
					s.setCategoria(c);
					s.setIdservicios(servicioVO.getIdservicios());
					servicios.add(s);
				}
			}
		}
		
		paquete.setServicios(servicios);
		
		em.persist(paquete);
		
		em.getTransaction().commit();
		em.close();
		return new ResponseEntity<Paquete>(paquete, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/delete/{id}/", method = RequestMethod.DELETE)
	public String deletePaquete(@PathVariable("id") int id) {
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();

		Paquete paquete = em.find(Paquete.class, id);
		em.remove(paquete);

		em.getTransaction().commit();
		em.close();
		return "ok";
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
