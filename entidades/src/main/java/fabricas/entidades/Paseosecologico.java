package fabricas.entidades;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.List;


/**
 * The persistent class for the paseosecologicos database table.
 * 
 */
@Entity
@Table(name="paseosecologicos")
@NamedQuery(name="Paseosecologico.findAll", query="SELECT p FROM Paseosecologico p")
public class Paseosecologico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idPaseosEcologicos;

	private String lugar;

	private String nombre;
	
	private String descripcion;
	private String fotos;
	private int		precio;
	private String requerimientos;
	private int duracion;


	//bi-directional many-to-one association to Servicio
	@OneToMany(mappedBy="paseosecologico")
	@JsonBackReference
	private List<Servicio> servicios;

	public Paseosecologico() {
	}

	public int getIdPaseosEcologicos() {
		return this.idPaseosEcologicos;
	}

	public void setIdPaseosEcologicos(int idPaseosEcologicos) {
		this.idPaseosEcologicos = idPaseosEcologicos;
	}

	public String getLugar() {
		return this.lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Servicio> getServicios() {
		return this.servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	public Servicio addServicio(Servicio servicio) {
		getServicios().add(servicio);
		servicio.setPaseosecologico(this);

		return servicio;
	}

	public Servicio removeServicio(Servicio servicio) {
		getServicios().remove(servicio);
		servicio.setPaseosecologico(null);

		return servicio;
	}
	
	
	
	
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFotos() {
		return this.fotos;
	}

	public void setFotos(String fotos) {
		this.fotos = fotos;
	}

	public int getPrecio() {
		return this.precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getRequerimientos() {
		return this.requerimientos;
	}

	public void setRequerimientos(String requerimientos) {
		this.requerimientos = requerimientos;
	}

	public int getDuracion() {
		return this.duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}


}