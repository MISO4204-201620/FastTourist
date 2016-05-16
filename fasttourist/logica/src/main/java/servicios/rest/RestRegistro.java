package servicios.rest;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fabricas.entidades.*;
import fabricas.entidades.VOs.UsuarioVO;

@RestController
@RequestMapping("/registro")
public class RestRegistro {


	@RequestMapping(value = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <Usuario> getAlojamientoByFilter(@RequestBody  UsuarioVO usuarioVO) {

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();
	
		try{
			em.createNamedQuery("Usuario.findByEmail").setParameter("email", usuarioVO.getEmail()).getSingleResult();
		}catch(NoResultException e){
			Usuario usuario = new Usuario();
			usuario.setNombre(usuarioVO.getNombre());
			usuario.setApellido(usuarioVO.getApellido());
			usuario.setEmail(usuarioVO.getEmail());
			usuario.setPassword(usuarioVO.getPassword());
			usuario.setDireccion(usuarioVO.getDireccion());
			usuario.setTelefono(usuarioVO.getTelefono());
			usuario.setFechaCreacion(new Date());
			usuario.setLogin("1");
			usuario.setActivo(usuarioVO.isActivo());
			Perfiles perfiles = new Perfiles();
			perfiles.setIdperfil(usuarioVO.getPerfil().getIdperfil());
			usuario.setPerfil(perfiles);
			em.persist(usuario);
			em.flush();
			em.getTransaction().commit();
			em.close();
			return new ResponseEntity <Usuario> (usuario, HttpStatus.OK);
		}
		em.close();
		return new ResponseEntity <Usuario> (new Usuario(), HttpStatus.OK);		
	}
	
	@RequestMapping(value = "/auth/{filtros}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <Usuario> checkAuth(@PathVariable String filtros) {
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();

		String[]values = filtros.split(",");
		Usuario usuario = null;
		try{
			usuario = (Usuario) em.createNamedQuery("Usuario.authenticate")
					.setParameter("email", values[0])
					.setParameter("password",values[1])
					.getSingleResult();
			}catch(NoResultException e){
				em.close();
			return new ResponseEntity <Usuario> (new Usuario(), HttpStatus.OK);
		}	
		em.close();
		return new ResponseEntity <Usuario> (usuario, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/darUsuario/{email}")
	public ResponseEntity <String> darUsuario(@PathVariable String email){
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();

		
		try{Usuario usr = (Usuario) em.createNamedQuery("Usuario.findByEmail")
			.setParameter("email", email)
			.getSingleResult();
		
		String resp = usr.getNombre()+":"+usr.getApellido()+":"+usr.getIdusuario()+":"+usr.getEmail();
		em.close();
		return new ResponseEntity <String> (resp,HttpStatus.OK);
		}
		catch(NoResultException e){		
			em.close();
			return new ResponseEntity <String> ("No encontrado",HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/solicitarBaja/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> solicitarBaja(@PathVariable int id) {

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();
		
		Usuario usuario = em.find(Usuario.class, id);
		
		if(usuario == null)
		{
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
		usuario.setBaja(true);
		
		em.persist(usuario);
		em.getTransaction().commit();
		em.close();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/solicitarUsuariosBaja/", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE +"; charset=UTF-8"})
	public ResponseEntity<List<Usuario>> getTransacciones() {

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();

		List<Usuario> usuarios = (List<Usuario>) em.createNamedQuery("Usuario.findByBaja")				
				.getResultList();
		em.close();
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	} 
	
	@RequestMapping(value = "/desactivar/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> desactivar(@PathVariable int id) {

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();
		
		Usuario usuario = em.find(Usuario.class, id);
		
		if(usuario == null)
		{
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
		usuario.setActivo(false);
				
		em.persist(usuario);
		em.getTransaction().commit();
		em.close();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/usuarios", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Usuario>> getUsuarios() {

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();
		
		List<Usuario> usuarios = (List<Usuario>) em.createNamedQuery("Usuario.findAll").getResultList();
		em.close();
		return new ResponseEntity<List<Usuario>>(usuarios,HttpStatus.OK);
	}
}