package fabricas.entidades;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the alimentacion database table.
 * 
 */
@Entity
@Table(name="alimentacion")
@NamedQueries({
	@NamedQuery(name="Alimentacion.findAll", query="SELECT a FROM Alimentacion a"),
	@NamedQuery(name="Alimentacion.findById", query="SELECT a FROM Alimentacion a where a.idalimentacion = :id"),
	
})

public class Alimentacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idalimentacion;

	private String nombre;

	//bi-directional many-to-one association to Tipoalimentacion
	@ManyToOne
	@JoinColumn(name="tipo")
	private Tipoalimentacion tipoalimentacion;

	//bi-directional many-to-one association to Servicio
	@OneToMany(mappedBy="alimentacion")
	private List<Servicio> servicios;

	public Alimentacion() {
	}

	public int getIdalimentacion() {
		return this.idalimentacion;
	}

	public void setIdalimentacion(int idalimentacion) {
		this.idalimentacion = idalimentacion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Tipoalimentacion getTipoalimentacion() {
		return this.tipoalimentacion;
	}

	public void setTipoalimentacion(Tipoalimentacion tipoalimentacion) {
		this.tipoalimentacion = tipoalimentacion;
	}

	public List<Servicio> getServicios() {
		return this.servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	public Servicio addServicio(Servicio servicio) {
		getServicios().add(servicio);
		servicio.setAlimentacion(this);

		return servicio;
	}

	public Servicio removeServicio(Servicio servicio) {
		getServicios().remove(servicio);
		servicio.setAlimentacion(null);

		return servicio;
	}

}