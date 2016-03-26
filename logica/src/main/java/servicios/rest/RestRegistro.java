package servicios.rest;

import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.EntityManager;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fabricas.entidades.Usuario;

@RestController
@RequestMapping("/registro")
public class RestRegistro {
	
	
	@RequestMapping(value = "/nuevo/{filtros}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <String> getAlojamientoByFilter(@PathVariable String filtros) {

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();

		int id = ThreadLocalRandom.current().nextInt(15, 150 + 1);;
		String nombre="";
		String apellido="";
		String email="";
		String direccion="";
		String telefono="";
		String fecha="2016-03-20 00:00:00";
		int login = 1;
		String password="";
		int activo = 1;
		String perfil = "";
		String response= "Error, revise su correo";
		
		for (String filtro : filtros.split(",")) {
			String[]values = filtro.split("=");
			if(values.length==2){
				if (values[0].equals("nombre")){
					nombre=values[1];
				}else if (values[0].equals("apellido")){
						apellido=values[1];
				}else if(values[0].equals("email")){
					email=values[1];
				}else if(values[0].equals("password")){
					password=values[1];
				}else if(values[0].equals("direccion")){
					direccion=values[1];
				}else if(values[0].equals("telefono")){
					telefono=values[1];
				}else if(values[0].equals("tipoUsuario")){
					perfil=values[1];
				}
			}
		}
		
		String query = "Select * from Usuarios where email = "+email;
		if((Usuario) em.createQuery(query).getSingleResult() != null){
//			query="insert into usuarios values (8,"Diego","Farfan","d.farfan10","direccion",12343,"2012-06-20 00:00:00",2,"diego",1,2)";
			query = "insert into usuarios values ("+id+","+nombre+","+apellido+","+email+","+direccion+","+telefono+","+fecha+","+login+","+
					password+","+activo+","+perfil+");";
			
			em.createQuery(query);
			response="Se ha registrado con éxito";				
		}

		return new ResponseEntity <String> (response, HttpStatus.OK);
	}
}
