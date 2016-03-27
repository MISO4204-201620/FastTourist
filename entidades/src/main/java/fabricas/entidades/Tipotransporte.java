package fabricas.entidades;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.List;


/**
 * The persistent class for the tipotransporte database table.
 * 
 */
@Entity
@Table(name="tipotransporte")
@NamedQuery(name="Tipotransporte.findAll", query="SELECT t FROM Tipotransporte t")
public class Tipotransporte implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idtipotransporte;

	private String nombre;

	//bi-directional many-to-one association to Transporte
	@OneToMany(mappedBy="tipotransporte")
	@JsonBackReference
	private List<Transporte> transportes;

	public Tipotransporte() {
	}

	public int getIdtipotransporte() {
		return this.idtipotransporte;
	}

	public void setIdtipotransporte(int idtipotransporte) {
		this.idtipotransporte = idtipotransporte;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Transporte> getTransportes() {
		return this.transportes;
	}

	public void setTransportes(List<Transporte> transportes) {
		this.transportes = transportes;
	}

	public Transporte addTransporte(Transporte transporte) {
		getTransportes().add(transporte);
		transporte.setTipotransporte(this);

		return transporte;
	}

	public Transporte removeTransporte(Transporte transporte) {
		getTransportes().remove(transporte);
		transporte.setTipotransporte(null);

		return transporte;
	}

}