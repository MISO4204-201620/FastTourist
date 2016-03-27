package fabricas.presentacion.VOs;

import java.io.Serializable;
import java.util.Date;



public class CarritoVO implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private int idCarrito;

	private Date fechaAnadido;
	private ServicioVO servicio;
	private UsuarioVO usuario;

	public CarritoVO() {
	}

	public int getIdCarrito() {
		return this.idCarrito;
	}

	public void setIdCarrito(int idCarrito) {
		this.idCarrito = idCarrito;
	}

	public Date getFechaAnadido() {
		return this.fechaAnadido;
	}

	public void setFechaAnadido(Date fechaAnadido) {
		this.fechaAnadido = fechaAnadido;
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