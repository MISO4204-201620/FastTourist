package fabricas.presentacion.VOs;

import java.io.Serializable;
import java.util.Date;



public class TransaccionesVO implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private int idtransacciones;

	
	private Date fecha;


	private EstadoTransaccionVO estadoTransaccion;

	
	private ServicioVO servicio;

	
	private UsuarioVO usuario;

	public TransaccionesVO() {
	}

	public int getIdtransacciones() {
		return this.idtransacciones;
	}

	public void setIdtransacciones(int idtransacciones) {
		this.idtransacciones = idtransacciones;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public EstadoTransaccionVO getEstadoTransaccion() {
		return this.estadoTransaccion;
	}

	public void setEstadoTransaccion(EstadoTransaccionVO estadoTransaccion) {
		this.estadoTransaccion = estadoTransaccion;
	}

	public ServicioVO getServicio() {
		return this.servicio;
	}

	public void setServicio(ServicioVO servicio) {
		this.servicio = servicio;
	}

	public UsuarioVO getUsuario() {
		return this.usuario;
	}

	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}

}