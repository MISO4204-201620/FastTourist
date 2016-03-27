package fabricas.entidades;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.List;


/**
 * The persistent class for the tipoalimentacion database table.
 * 
 */
@Entity
@Table(name="tipoalimentacion")
@NamedQuery(name="Tipoalimentacion.findAll", query="SELECT t FROM Tipoalimentacion t")
public class Tipoalimentacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idtipoAlimentacion;

	private String nombre;

	//bi-directional many-to-one association to Alimentacion
	@OneToMany(mappedBy="tipoalimentacion")
	@JsonBackReference
	private List<Alimentacion> alimentacions;

	public Tipoalimentacion() {
	}

	public int getIdtipoAlimentacion() {
		return this.idtipoAlimentacion;
	}

	public void setIdtipoAlimentacion(int idtipoAlimentacion) {
		this.idtipoAlimentacion = idtipoAlimentacion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Alimentacion> getAlimentacions() {
		return this.alimentacions;
	}

	public void setAlimentacions(List<Alimentacion> alimentacions) {
		this.alimentacions = alimentacions;
	}

	public Alimentacion addAlimentacion(Alimentacion alimentacion) {
		getAlimentacions().add(alimentacion);
		alimentacion.setTipoalimentacion(this);

		return alimentacion;
	}

	public Alimentacion removeAlimentacion(Alimentacion alimentacion) {
		getAlimentacions().remove(alimentacion);
		alimentacion.setTipoalimentacion(null);

		return alimentacion;
	}

}