package fabricas.entidades;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Date;


/**
 * The persistent class for the transacciones database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Transacciones.findAll", query="SELECT t FROM Transacciones t"),
	@NamedQuery(name="Transacciones.findAllDistinct", query="SELECT t.servicio.nombre FROM Transacciones t WHERE t.servicio.usuario.idusuario= :id"),
	@NamedQuery(name="Transacciones.findByProvider", query="SELECT t FROM Transacciones t WHERE t.servicio.usuario.idusuario= :id"),
	@NamedQuery(name="Transacciones.getMaxId", query="SELECT MAX(t.idtransacciones) FROM Transacciones t"),
	@NamedQuery(name="Transacciones.getByUser", query="SELECT t FROM Transacciones t WHERE t.usuario.idusuario= :idUser AND t.servicio.idservicios = :idServicio"),
	@NamedQuery(name="Transacciones.findByUser", query="SELECT t FROM Transacciones t WHERE t.usuario.idusuario= :idUser")
})
public class Transacciones implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idtransacciones;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	//bi-directional many-to-one association to EstadoTransaccion
	@ManyToOne
	@JoinColumn(name="estadoTransaccion")
	@JsonManagedReference
	private EstadoTransaccion estadoTransaccion;

	//bi-directional many-to-one association to Servicio
	@ManyToOne
	@JoinColumn(name="servicio")
	@JsonManagedReference
	private Servicio servicio;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="usuario")
	@JsonManagedReference
	private Usuario usuario;

	public Transacciones() {
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

	public EstadoTransaccion getEstadoTransaccion() {
		return this.estadoTransaccion;
	}

	public void setEstadoTransaccion(EstadoTransaccion estadoTransaccion) {
		this.estadoTransaccion = estadoTransaccion;
	}

	public Servicio getServicio() {
		return this.servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}