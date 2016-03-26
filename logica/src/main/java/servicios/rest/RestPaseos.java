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

@RestController
@RequestMapping("/paseos")
public class RestPaseos {

	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getServiciosPaseos", consumes = "application/json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Servicio>> getAllServicios() {
		
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();
		
		List<Servicio> array = (List<Servicio>) em.createNamedQuery("Servicio.findServicioPaseos")		
				.getResultList();
		
		return new ResponseEntity<List<Servicio>>(array, HttpStatus.OK);
	}	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/paseos/filtrarPaseos/{filtros}", 
	method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <List<Servicio>> getServicioByFilter(@PathVariable String filtros) {
		
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();
		
		String query= queryServicioByFilter(filtros);
		
		List<Servicio> servicios = em.createQuery(query).getResultList();
		
		return new ResponseEntity <List<Servicio>> (servicios, HttpStatus.OK);
		
	}
	
	private String queryServicioByFilter(String filtros) {
		
		//Se recuperan los filtros de busqueda
		String nombre="";
		String lugar="";
		String servicio="";
		
		for (String filtro : filtros.split(",")) {
			String[]values = filtro.split("=");
			if(values.length==2){
				if (values[0].equals("nombre")){
					nombre=values[1];
				}else if(values[0].equals("lugar")){
					lugar=values[1];
				}else if(values[0].equals("servicio")){
					servicio=values[1];
				}
			}
		}
		
		String query = "SELECT s FROM Servicio s WHERE s.activo=1 AND s.categoria=4 ";
		
		if (!nombre.isEmpty()){
			query = query + "AND s.paseosecologico.nombre="+nombre+" ";
		}
		if(!lugar.isEmpty()){
			query = query + "AND s.paseosecologico.lugar="+lugar+" ";
		}
		if(!servicio.isEmpty()){
			query = query + "AND s.nombre = "+servicio+" ";
		}
		
		return query;
		
	}
	
	
	
	
	
	
	//--------------No usados hasta el momento -----------------//
	
	
//	@SuppressWarnings("unchecked")
//	@RequestMapping(value = "/getPaseos", consumes = "application/json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<List<Paseosecologico>> getAllPaseos() {
//		
//		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
//		em.getTransaction().begin();
//		
//		List<Paseosecologico> array = (List<Paseosecologico>) em.createNamedQuery("Paseosecologico.findAll")		
//				.getResultList();
//		
//		return new ResponseEntity<List<Paseosecologico>>(array, HttpStatus.OK);
//	}	
	
		
//		@RequestMapping(value = "/filtrar-id", consumes = "application/json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//		public ResponseEntity<Paseosecologico> getById(@PathVariable int id) {
//
//			EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
//			em.getTransaction().begin();
//			
//			Paseosecologico producto = (Paseosecologico) em.createNamedQuery("Paseosecologico.findById")
//					.setParameter("id", id)
//					.getSingleResult();
//
//			System.out.println(producto.getNombre()+": "+producto.getLugar()+": "+producto.getIdPaseosEcologicos());			
//			System.out.println("Pero a veces también salir");
//			return new ResponseEntity<Paseosecologico>(producto, HttpStatus.OK);
//		}
//		
//		@RequestMapping(value = "/filtrar-nombre", consumes = "application/json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//		public ResponseEntity<ArrayList<Paseosecologico>> getByNombre(@PathVariable String nombre) {
//			
//			EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
//			em.getTransaction().begin();
//
//			@SuppressWarnings("unchecked")
//			ArrayList<Paseosecologico> array = (ArrayList<Paseosecologico>) em.createNamedQuery("Paseosecologico.findByName")
//					.setParameter("nombre", nombre)
//					.getResultList();
//			
//			return new ResponseEntity<ArrayList<Paseosecologico>>(array, HttpStatus.OK);
//		
//		}		
//		
//		@RequestMapping(value = "/filtrar-lugar", consumes = "application/json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//		public ResponseEntity<ArrayList<Paseosecologico>> getByLugar(@PathVariable String lugar) {
//			
//			EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
//			em.getTransaction().begin();
//
//			@SuppressWarnings("unchecked")
//			ArrayList<Paseosecologico> array = (ArrayList<Paseosecologico>) em.createNamedQuery("Paseosecologico.findByPlace")
//				.setParameter("lugar", lugar)
//				.getResultList();
//
//			return new ResponseEntity<ArrayList<Paseosecologico>>(array, HttpStatus.OK);
//		}		
//
//		
//		public static void main(String[] args) {
//			
//			RestPaseos p = new RestPaseos();
//			p.getAllServicios();
//			
//		}
}
