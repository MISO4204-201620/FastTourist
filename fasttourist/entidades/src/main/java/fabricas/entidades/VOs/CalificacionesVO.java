package fabricas.entidades.VOs;

import java.io.Serializable;
import java.util.Date;


public class CalificacionesVO implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private int idcalificaciones;

	private String comentario;

	
	private Date fecha;

	private int valor;

	
	private UsuarioVO usuario;

	private ServicioVO servicioBean;

	public CalificacionesVO() {
	}

	public int getIdcalificaciones() {
		return idcalificaciones;
	}

	public void setIdcalificaciones(int idcalificaciones) {
		this.idcalificaciones = idcalificaciones;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public UsuarioVO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}

	public ServicioVO getServicioBean() {
		return servicioBean;
	}

	public void setServicioBean(ServicioVO servicioBean) {
		this.servicioBean = servicioBean;
	}

	}