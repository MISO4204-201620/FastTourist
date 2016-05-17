package fabricas.mensajeria;

import java.util.List;

import fabricas.entidades.Mensajeria;

public interface InterfaceMensajeria {
	
	public Mensajeria enviarMensaje(Mensajeria mensaje);
	public List<Mensajeria> cargarMensajesRecibidos(int id);
	public List<Mensajeria> cargarMensajesEnviados(int id);
	public Mensajeria getMensaje(int id);
	

}
