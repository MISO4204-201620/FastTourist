package fabricas.presentacion.VOs;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class BusquedasVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	@DateTimeFormat(pattern = "dd-MM-yyyy hh:mm:ss")
	private Date fecha;

	private ServicioVO servicio;

	private UsuarioVO usuario;

	public BusquedasVO() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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
