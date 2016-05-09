package servicios.rest;


import java.util.Date;
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
import fabricas.entidades.Paseosecologico;
import fabricas.entidades.Servicio;
import fabricas.entidades.Tipoalimentacion;
import fabricas.entidades.Tipotransporte;
import fabricas.entidades.Transporte;
import fabricas.entidades.Usuario;
import fabricas.entidades.VOs.ServicioVO;


@RestController
@RequestMapping("/servicios")
public class RestCrudServicios {

	@RequestMapping(value = "/get/{id}/", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE +"; charset=UTF-8"})
	public ResponseEntity<Servicio> getProducto(@PathVariable int id) {

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();

		Servicio servicio = (Servicio) em.createNamedQuery("Servicio.findById")
				.setParameter("id", id)
				.getSingleResult();

		return new ResponseEntity<Servicio>(servicio, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getByProvider/{idProveedor}/", 
	method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE +"; charset=UTF-8"})
	public ResponseEntity <List<Servicio>> getAlojamientoByProveedor(@PathVariable int idProveedor) {

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();

		List<Servicio> servicio = (List<Servicio>) em.createNamedQuery("Servicio.findByProveedor")
				.setParameter("idProveedor", idProveedor)
				.getResultList();

		return new ResponseEntity <List<Servicio>> (servicio, HttpStatus.OK);

	}

	@RequestMapping(value = "/edit/", method = RequestMethod.POST,produces={MediaType.APPLICATION_JSON_VALUE +"; charset=UTF-8"})
	public ResponseEntity<Servicio> updateAlojamiento(@RequestBody  ServicioVO servicioVO){

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();

		Servicio servicio = em.find(Servicio.class, servicioVO.getIdservicios());

		em.getTransaction().begin();

		//Quitar caracteres especiales de la descripción
		String s = servicioVO.getDescripcion().replaceAll("\\P{Print}", "");

		servicio.setActivo(servicioVO.getActivo());
		servicio.setDescripcion(s);
		servicio.setDescuento(servicioVO.getDescuento());
		servicio.setNombre(servicioVO.getNombre());
		servicio.setPrecio(servicioVO.getPrecio());
		servicio.setRutaGaleria(servicioVO.getRutaGaleria());
		
		if(servicio.getCategoria().getIdcategorias()== EnumCategorias.ALOJAMIENTO.getValue()){
			servicio.getAlojamiento().setAire_acondicionado(servicioVO.getAlojamiento().getAire_acondicionado());
			servicio.getAlojamiento().setCantPersonas(servicioVO.getAlojamiento().getCantPersonas());
			servicio.getAlojamiento().setCiudad(servicioVO.getAlojamiento().getCiudad());
			servicio.getAlojamiento().setDireccion(servicioVO.getAlojamiento().getDireccion());
			servicio.getAlojamiento().setFechaEntrada(servicioVO.getAlojamiento().getFechaEntrada());
			servicio.getAlojamiento().setFechaSalida(servicioVO.getAlojamiento().getFechaSalida());
			servicio.getAlojamiento().setHabitaciones(servicioVO.getAlojamiento().getHabitaciones());
			servicio.getAlojamiento().setNombre(servicioVO.getAlojamiento().getNombre());
			servicio.getAlojamiento().setPiscina(servicioVO.getAlojamiento().getPiscina());
			servicio.getAlojamiento().setTelefono(servicioVO.getAlojamiento().getTelefono());
			servicio.getAlojamiento().setWifi(servicioVO.getAlojamiento().getWifi());
			
		}else if(servicio.getCategoria().getIdcategorias()== EnumCategorias.ALIMENTACION.getValue()){
			servicio.getAlimentacion().setNombre(servicioVO.getAlimentacion().getNombre());
			Tipoalimentacion tipo = new Tipoalimentacion();
			tipo.setIdtipoAlimentacion(servicioVO.getAlimentacion().getTipoalimentacion().getIdtipoAlimentacion());
			servicio.getAlimentacion().setTipoalimentacion(tipo);
			
		}else if(servicio.getCategoria().getIdcategorias()== EnumCategorias.TRANSPORTE.getValue()){
			servicio.getTransporte().setCantPersonas(servicioVO.getTransporte().getCantPersonas());
			servicio.getTransporte().setDestino(servicioVO.getTransporte().getDestino());
			servicio.getTransporte().setFechaSalida(servicioVO.getTransporte().getFechaSalida());
			servicio.getTransporte().setNombre(servicioVO.getTransporte().getNombre());
			servicio.getTransporte().setOrigen(servicioVO.getTransporte().getOrigen());
			Tipotransporte tipo = new Tipotransporte();
			tipo.setIdtipotransporte(servicioVO.getTransporte().getTipotransporte().getIdtipotransporte());
			servicio.getTransporte().setTipotransporte(tipo);
			
		}else if(servicio.getCategoria().getIdcategorias()== EnumCategorias.PASEOS.getValue()){
			servicio.getPaseosecologico().setDescripcion(servicioVO.getPaseosecologico().getDescripcion().replaceAll("\\P{Print}", ""));
			servicio.getPaseosecologico().setDuracion(servicioVO.getPaseosecologico().getDuracion());
			servicio.getPaseosecologico().setFotos(servicioVO.getPaseosecologico().getFotos());
			servicio.getPaseosecologico().setLugar(servicioVO.getPaseosecologico().getLugar());
			servicio.getPaseosecologico().setNombre(servicioVO.getPaseosecologico().getNombre());
			servicio.getPaseosecologico().setPrecio(servicioVO.getPaseosecologico().getPrecio());
			servicio.getPaseosecologico().setRequerimientos(servicioVO.getPaseosecologico().getRequerimientos());

		}

		em.getTransaction().commit();
		return new ResponseEntity<Servicio>(servicio, HttpStatus.OK);
	}

	@RequestMapping(value = "/create/", method = RequestMethod.POST,produces={MediaType.APPLICATION_JSON_VALUE +"; charset=UTF-8"})
	public ResponseEntity<Servicio> createAlojamiento(@RequestBody  ServicioVO servicioVO){

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();

		Servicio servicio = new Servicio();
		servicio.setFechaCreacion(new Date());
		servicio.setActivo(servicioVO.getActivo());
		servicio.setDescripcion(servicioVO.getDescripcion().replaceAll("\\P{Print}", ""));
		servicio.setDescuento(servicioVO.getDescuento());
		servicio.setNombre(servicioVO.getNombre());
		servicio.setPrecio(servicioVO.getPrecio());
		servicio.setRutaGaleria(servicioVO.getRutaGaleria());

		//Se asocia la categoria
		Categoria categoria = new Categoria();
		categoria.setIdcategorias(servicioVO.getCategoria().getIdcategorias());
		servicio.setCategoria(categoria);

		//Se asocia el proveedor
		Usuario usuario = new Usuario();
		usuario.setIdusuario(servicioVO.getUsuario().getIdusuario());
		servicio.setUsuario(usuario);

		if(servicio.getCategoria().getIdcategorias()== EnumCategorias.ALOJAMIENTO.getValue()){
			Alojamiento alojamiento = new Alojamiento();
			alojamiento.setCantPersonas(servicioVO.getAlojamiento().getCantPersonas());
			alojamiento.setCiudad(servicioVO.getAlojamiento().getCiudad());
			alojamiento.setDireccion(servicioVO.getAlojamiento().getDireccion());
			alojamiento.setTelefono(servicioVO.getAlojamiento().getTelefono());
			alojamiento.setFechaEntrada(servicioVO.getAlojamiento().getFechaEntrada());
			alojamiento.setFechaSalida(servicioVO.getAlojamiento().getFechaSalida());
			alojamiento.setHabitaciones(servicioVO.getAlojamiento().getHabitaciones());
			alojamiento.setNombre(servicioVO.getAlojamiento().getNombre());
			alojamiento.setPiscina(servicioVO.getAlojamiento().getPiscina());
			alojamiento.setWifi(servicioVO.getAlojamiento().getWifi());
			alojamiento.setAire_acondicionado(servicioVO.getAlojamiento().getAire_acondicionado());
			em.getTransaction().begin();
			em.persist(alojamiento);
			em.flush();
			servicio.setAlojamiento(alojamiento);
			
		}else if(servicio.getCategoria().getIdcategorias()== EnumCategorias.ALIMENTACION.getValue()){
			Alimentacion alimentacion = new Alimentacion();
			alimentacion.setNombre(servicioVO.getAlimentacion().getNombre());
			Tipoalimentacion tipo = new Tipoalimentacion();
			tipo.setIdtipoAlimentacion(servicioVO.getAlimentacion().getTipoalimentacion().getIdtipoAlimentacion());
			alimentacion.setTipoalimentacion(tipo);
			em.getTransaction().begin();
			em.persist(alimentacion);
			em.flush();
			servicio.setAlimentacion(alimentacion);
			
		}else if(servicio.getCategoria().getIdcategorias()== EnumCategorias.TRANSPORTE.getValue()){
			Transporte transporte = new Transporte();
			transporte.setCantPersonas(servicioVO.getTransporte().getCantPersonas());
			transporte.setDestino(servicioVO.getTransporte().getDestino());
			transporte.setFechaSalida(servicioVO.getTransporte().getFechaSalida());
			transporte.setNombre(servicioVO.getTransporte().getNombre());
			transporte.setOrigen(servicioVO.getTransporte().getOrigen());
			Tipotransporte tipo = new Tipotransporte();
			tipo.setIdtipotransporte(servicioVO.getTransporte().getTipotransporte().getIdtipotransporte());
			transporte.setTipotransporte(tipo);
			em.getTransaction().begin();
			em.persist(transporte);
			em.flush();
			servicio.setTransporte(transporte);
			
		}else if(servicio.getCategoria().getIdcategorias()== EnumCategorias.PASEOS.getValue()){
			Paseosecologico paseo = new Paseosecologico();
			paseo.setDescripcion(servicioVO.getPaseosecologico().getDescripcion().replaceAll("\\P{Print}", ""));
			paseo.setDuracion(servicioVO.getPaseosecologico().getDuracion());
			paseo.setFotos(servicioVO.getPaseosecologico().getFotos());
			paseo.setLugar(servicioVO.getPaseosecologico().getLugar());
			paseo.setNombre(servicioVO.getPaseosecologico().getNombre());
			paseo.setPrecio(servicioVO.getPaseosecologico().getPrecio());
			paseo.setRequerimientos(servicioVO.getPaseosecologico().getRequerimientos());
			em.getTransaction().begin();
			em.persist(paseo);
			em.flush();
			servicio.setPaseosecologico(paseo);
		}
		em.persist(servicio);
		em.getTransaction().commit();

		return new ResponseEntity<Servicio>(servicio, HttpStatus.OK);
	}

	@RequestMapping(value = "/delete/{id}/", method = RequestMethod.DELETE)
	public String deleteAlojamiento(@PathVariable("id") int id) {
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();

		Servicio servicio = em.find(Servicio.class, id);
		em.remove(servicio);

		if(servicio.getCategoria().getIdcategorias()== EnumCategorias.ALOJAMIENTO.getValue()){
			Alojamiento alojamiento = em.find(Alojamiento.class,servicio.getAlojamiento().getIdalojamiento());
			if (alojamiento != null) {
				em.remove(alojamiento);		
			}
		}else if(servicio.getCategoria().getIdcategorias()== EnumCategorias.ALIMENTACION.getValue()){
			Alimentacion alimentacion = em.find(Alimentacion.class,servicio.getAlimentacion().getIdalimentacion());
			if (alimentacion != null) {
				em.remove(alimentacion);
			}
		}else if(servicio.getCategoria().getIdcategorias()== EnumCategorias.TRANSPORTE.getValue()){
			Transporte transporte = em.find(Transporte.class,servicio.getTransporte().getIdtransporte());
			if (transporte != null) {
				em.remove(transporte);
			}

		}else if(servicio.getCategoria().getIdcategorias()== EnumCategorias.PASEOS.getValue()){
			Paseosecologico paseosecologico = em.find(Paseosecologico.class,servicio.getPaseosecologico().getIdPaseosEcologicos());
			if (paseosecologico != null) {
				em.remove(paseosecologico);
			}

		}

		try{
			em.getTransaction().commit();
			return "ok";
		}catch(Exception e){
			return "Nok";
		}		
	}
}