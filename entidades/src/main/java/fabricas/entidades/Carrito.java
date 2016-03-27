package fabricas.entidades;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Date;


/**
 * The persistent class for the carrito database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Carrito.findAll", query="SELECT c FROM Carrito c"),
	@NamedQuery(name="Carrito.findByUser", query="SELECT c FROM Carrito c where c.usuario.idusuario = :id"),
	@NamedQuery(name="Carrito.deleteByUser", query="DELETE  FROM Carrito c where c.usuario.idusuario = :id"),
	@NamedQuery(name="Carrito.findById", query="SELECT c FROM Carrito c where c.idCarrito= :id")
})

public class Carrito implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idCarrito;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_anadido")
	private Date fechaAnadido;

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

	public Carrito() {
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