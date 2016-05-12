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
import fabricas.entidades.Tipotransporte;
import fabricas.entidades.Usuario;


@RestController
@RequestMapping("/transporte")
public class RestTransporte {
	
	/**
	 * Metodo encargado de recuperar la lista de proveedores de servicios de alojamiento
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getproveedores", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <List<Usuario>> getListaProveedores() {
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();

		List<Usuario> proveedores = (List<Usuario>) em.createNamedQuery("Servicio.findProveedoresByTransporte").getResultList();
		em.close();
		return new ResponseEntity <List<Usuario>> (proveedores, HttpStatus.OK);

	}

	/**
	 * Metodo encargado de recuperar la lista de proveedores de servicios de alojamiento
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getipos", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE +"; charset=UTF-8"})
	public ResponseEntity <List<Tipotransporte>> getTiposTransporte() {
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();

		List<Tipotransporte> tipos = (List<Tipotransporte>) em.createNamedQuery("Tipotransporte.findAll").getResultList();
		em.close();
		return new ResponseEntity <List<Tipotransporte>> (tipos, HttpStatus.OK);

	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE +"; charset=UTF-8"})
	public ResponseEntity <List<Servicio>> getTransporte() {

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();

		List<Servicio> transportes;

		transportes = (List<Servicio>) em.createNamedQuery("Servicio.findTransporte").getResultList();
		em.close();
		return new ResponseEntity <List<Servicio>> (transportes, HttpStatus.OK);

	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/{filtros}", 
	method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE +"; charset=UTF-8"})
	public ResponseEntity <List<Servicio>> getTransporteByFilter(@PathVariable String filtros) {

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();

		String query= queryTransporteByFilter(filtros);	

		List<Servicio> transportes  = em.createQuery(query).getResultList();
		em.close();
		return new ResponseEntity <List<Servicio>> (transportes, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE +"; charset=UTF-8"})
	public ResponseEntity<Servicio> getProducto(@PathVariable int id) {

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();

		Servicio transporte = (Servicio) em.createNamedQuery("Servicio.findById")
				.setParameter("id", id)
				.getSingleResult();
		em.close();
		return new ResponseEntity<Servicio>(transporte, HttpStatus.OK);
	}

	
	/**
	 * Metodo encargado de generar el query a partir 
	 * de la lista de parametros recibidos
	 * @param filtros
	 * @return
	 */
	private String queryTransporteByFilter(String filtros){

		//Se recuperan los filtros de busqueda
		String origen="";
		String destino="";
		String proveedor="";
		String tipo="";
		String personas="";
		String fechaSalida="";

		for (String filtro : filtros.split(",")) {
			String[]values = filtro.split("=");
			if(values.length==2){
				if (values[0].equals("origen")){
					origen=values[1];
				}else if(values[0].equals("destino")){
					destino=values[1];
				}else if(values[0].equals("proveedor")){
					proveedor=values[1];
				}else if(values[0].equals("tipo")){
					tipo=values[1];
				}else if(values[0].equals("personas")){
					personas=values[1];
				}else if(values[0].equals("fechaSalida")){
					fechaSalida=values[1];
				}
			}
		}
		
		String query = "SELECT s FROM Servicio s WHERE s.activo=1 AND s.categoria=3 ";

		if (!origen.isEmpty()){
			query = query + "AND UPPER(s.transporte.origen) like UPPER('%" + origen +"%') ";
		}
		if (!destino.isEmpty()){
			query = query + "AND UPPER(s.transporte.destino) like UPPER('%" + destino +"%') ";
		}
		if(!proveedor.isEmpty()){
			query = query + "AND s.usuario="+proveedor+" ";
		}
		if(!tipo.isEmpty()){
			query = query + "AND s.transporte.tipotransporte = "+tipo+" ";
		}
		if(!personas.isEmpty()){
			query = query + "AND s.transporte.cantPersonas > "+personas+" ";
		}
		if(!fechaSalida.isEmpty()){
			query = query +" AND s.transporte.fechaSalida BETWEEN '"+fechaSalida+" 00:00:00' AND '"+fechaSalida+" 23:59:59' ";
		}

		//Se agrega ordenamiento predefinido
		query = query + "ORDER BY s.transporte.fechaSalida desc";

		return query;
	}
}