package fabricas.entidades;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;


/**
 * The persistent class for the estado_transaccion database table.
 * 
 */
@Entity
@Table(name="estado_transaccion")
@NamedQueries({
	@NamedQuery(name="EstadoTransaccion.findAll", query="SELECT e FROM EstadoTransaccion e"),
	@NamedQuery(name="EstadoTransaccion.findById", query="SELECT e FROM EstadoTransaccion e where e.idestadoTransaccion = :id")
	})

public class EstadoTransaccion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="idestado_transaccion")
	private int idestadoTransaccion;

	private String descripcion;

	private String nombre;

	//bi-directional many-to-one association to Transacciones
	@OneToMany(mappedBy="estadoTransaccion")
	@JsonBackReference
	private List<Transacciones> transacciones;

	public EstadoTransaccion() {
	}

	public int getIdestadoTransaccion() {
		return this.idestadoTransaccion;
	}

	public void setIdestadoTransaccion(int idestadoTransaccion) {
		this.idestadoTransaccion = idestadoTransaccion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Transacciones> getTransacciones() {
		return this.transacciones;
	}

	public void setTransacciones(List<Transacciones> transacciones) {
		this.transacciones = transacciones;
	}

	public Transacciones addTransacciones(Transacciones transacciones) {
		getTransacciones().add(transacciones);
		transacciones.setEstadoTransaccion(this);

		return transacciones;
	}

	public Transacciones removeTransacciones(Transacciones transacciones) {
		getTransacciones().remove(transacciones);
		transacciones.setEstadoTransaccion(null);

		return transacciones;
	}

}