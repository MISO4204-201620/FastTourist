package fabricas.presentacion.VOs;

import java.io.Serializable;
import java.util.Date;


public class PreguntasVO implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private int idPreguntas;

	private Date fechaCreacion;

	private String pregunta;

	private String respuesta;

	private ServicioVO servicio;

	private UsuarioVO usuario;

	public PreguntasVO() {
	}

	public int getIdPreguntas() {
		return this.idPreguntas;
	}

	public void setIdPreguntas(int idPreguntas) {
		this.idPreguntas = idPreguntas;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getPregunta() {
		return this.pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public String getRespuesta() {
		return this.respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
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