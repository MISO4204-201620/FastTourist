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
import utilidades.Constantes;
import utilidades.PropertiesLoader;


@RestController
@RequestMapping("/alojamiento")
public class RestAlojamiento {

	/**
	 * Metodo encargado de recuperar la lista de proveedores de servicios de alojamiento
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getproveedores", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <List<Usuario>> getListaProveedores() {
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();

		List<Usuario> proveedores = (List<Usuario>) em.createNamedQuery("Servicio.findProveedoresByAlojamiento").getResultList();
		em.close();
		return new ResponseEntity <List<Usuario>> (proveedores, HttpStatus.OK);

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <List<Servicio>> getAlojamiento() {

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();

		List<Servicio> alojamientos;

		alojamientos = (List<Servicio>) em.createNamedQuery("Servicio.findAlojamiento").getResultList();
		em.close();
		return new ResponseEntity <List<Servicio>> (alojamientos, HttpStatus.OK);

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/{filtros}", 
	method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <List<Servicio>> getAlojamientoByFilter(@PathVariable String filtros) {

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();
		
		String query= queryAlojamientoByFilter(filtros);	
		
		List<Servicio> alojamientos  = em.createQuery(query).getResultList();
		em.close();
		return new ResponseEntity <List<Servicio>> (alojamientos, HttpStatus.OK);

	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE +"; charset=UTF-8"})
	public ResponseEntity<Servicio> getProducto(@PathVariable int id) {

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();

		Servicio alojamiento = (Servicio) em.createNamedQuery("Servicio.findById")
				.setParameter("id", id)
				.getSingleResult();
		em.close();
		return new ResponseEntity<Servicio>(alojamiento, HttpStatus.OK);
	}

	/**
	 * Metodo encargado de generar el query a partir 
	 * de la lista de parametros recibidos
	 * @param filtros
	 * @return
	 */
	private String queryAlojamientoByFilter(String filtros){

		//Se recuperan los filtros de busqueda
		String ciudad="";
		String proveedor="";
		String habitaciones="";
		String personas="";
		String fechaEntrada="";
		String fechaSalida="";

		for (String filtro : filtros.split(",")) {
			String[]values = filtro.split("=");
			if(values.length==2){
				if (values[0].equals("ciudad")){
					ciudad=values[1];
				}else if(values[0].equals("proveedor")){
					proveedor=values[1];
				}else if(values[0].equals("habitaciones")){
					habitaciones=values[1];
				}else if(values[0].equals("personas")){
					personas=values[1];
				}else if(values[0].equals("fechaEntrada")){
					fechaEntrada=values[1];
				}else if(values[0].equals("fechaSalida")){
					fechaSalida=values[1];
				}
			}
		}

		String query = "SELECT s FROM Servicio s WHERE s.activo=1 AND s.categoria=1 ";

		if (!ciudad.isEmpty()){
			query = query + "AND (UPPER(s.alojamiento.ciudad) like UPPER('%" + ciudad +"%') ";
			query = query + "OR UPPER(s.alojamiento.nombre) like UPPER('%" + ciudad +"%') ";
			query = query + "OR UPPER(s.nombre) like UPPER('%" + ciudad +"%')) ";
		}
		if(!proveedor.isEmpty()){
			query = query + "AND s.usuario="+proveedor+" ";
		}
		if(!habitaciones.isEmpty()){
			query = query + "AND s.alojamiento.habitaciones > "+habitaciones+" ";
		}
		if(!personas.isEmpty()){
			query = query + "AND s.alojamiento.cantPersonas > "+personas+" ";
		}
		if(!fechaEntrada.isEmpty()){
			query = query + "AND s.alojamiento.fechaEntrada <= '"+fechaEntrada+"' ";
		}
		if(!fechaSalida.isEmpty()){
			query = query +" AND s.alojamiento.fechaSalida >= '"+fechaSalida+"' ";
		}

		//Se agrega ordenamiento predefinido
		query = query + "ORDER BY s.alojamiento.fechaEntrada desc";

		return query;
	}
}