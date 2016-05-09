package fabricas.persistencia;

import java.util.List;

import javax.persistence.EntityManager;

import fabricas.entidades.Mensajeria;


public class MensajeriaDao {

	public Mensajeria crearMensaje(Mensajeria mensaje) {

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();

		try {
			em.persist(mensaje);
			em.getTransaction().commit();
			return mensaje;

		} finally {
			em.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Mensajeria> getMensajesRecibidosUsuario(int id) {

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();

		try {
			List<Mensajeria> mensajes = (List<Mensajeria>) em
					.createNamedQuery("Mensajeria.findByDestinatario")
					.setParameter("id", id).getResultList();
			return mensajes;

		} finally {
			em.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Mensajeria> getMensajesEnviadosUsuario(int id) {

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();

		try {
			List<Mensajeria> mensajes = (List<Mensajeria>) em
					.createNamedQuery("Mensajeria.findByRemitente")
					.setParameter("id", id).getResultList();
			return mensajes;

		} finally {
			em.close();
		}
	}

	public Mensajeria findById(int id) {

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();

		try {
			Mensajeria mensaje = (Mensajeria) em
					.createNamedQuery("Mensajeria.findById")
					.setParameter("id", id).getSingleResult();
			return mensaje;

		} finally {
			em.close();
		}
	}
}
