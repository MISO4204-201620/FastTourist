package fabricas.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the calificaciones database table.
 * 
 */
@Entity
@Table(name="calificaciones")
@NamedQuery(name="Calificacione.findAll", query="SELECT c FROM Calificacione c")
public class Calificaciones implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idcalificaciones;

	private String comentario;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	private int valor;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="cliente")
	private Usuario usuario;

	//bi-directional many-to-one association to Servicio
	@ManyToOne
	@JoinColumn(name="servicio")
	private Servicio servicioBean;

	public Calificaciones() {
	}

	public int getIdcalificaciones() {
		return this.idcalificaciones;
	}

	public void setIdcalificaciones(int idcalificaciones) {
		this.idcalificaciones = idcalificaciones;
	}

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getValor() {
		return this.valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Servicio getServicioBean() {
		return this.servicioBean;
	}

	public void setServicioBean(Servicio servicioBean) {
		this.servicioBean = servicioBean;
	}

}