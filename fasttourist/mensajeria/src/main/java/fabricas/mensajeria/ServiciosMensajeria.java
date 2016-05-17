package fabricas.mensajeria;

import java.util.List;

import fabricas.entidades.Mensajeria;
import fabricas.persistencia.MensajeriaDao;

public class ServiciosMensajeria implements InterfaceMensajeria {
	private MensajeriaDao dao;

	public ServiciosMensajeria() {
		dao = new MensajeriaDao();
	}
	@Override
	public Mensajeria enviarMensaje(Mensajeria mensaje) {
		return dao.crearMensaje(mensaje);
	}
	@Override
	public List<Mensajeria> cargarMensajesRecibidos(int id) {
		return dao.getMensajesRecibidosUsuario(id);
	}
	@Override
	public List<Mensajeria> cargarMensajesEnviados(int id) {
		return dao.getMensajesEnviadosUsuario(id);
	}
	@Override
	public Mensajeria getMensaje(int id) {
		return dao.findById(id);
	}

}
