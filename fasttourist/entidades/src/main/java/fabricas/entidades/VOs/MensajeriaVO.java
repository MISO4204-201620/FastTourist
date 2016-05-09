package fabricas.entidades.VOs;

import java.io.Serializable;
import java.util.Date;

public class MensajeriaVO implements Serializable{


	private static final long serialVersionUID = 1L;

	private int id;

	private String asunto;

	private Boolean estado;

	private Date fecha;

	private String mensaje;

	private UsuarioVO destinatario;

	private UsuarioVO remitente;

	public MensajeriaVO() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAsunto() {
		return this.asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getMensaje() {
		return this.mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public UsuarioVO getDestinatario() {
		return this.destinatario;
	}

	public void setDestinatario(UsuarioVO destinatario) {
		this.destinatario = destinatario;
	}

	public UsuarioVO getRemitente() {
		return this.remitente;
	}

	public void setRemitente(UsuarioVO remitente) {
		this.remitente = remitente;
	}

}
