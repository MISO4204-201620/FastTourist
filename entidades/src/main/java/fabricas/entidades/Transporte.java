package fabricas.entidades;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the transporte database table.
 * 
 */
@Entity
@Table(name="transporte")
@NamedQuery(name="Transporte.findAll", query="SELECT t FROM Transporte t")
public class Transporte implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idtransporte;

	private int cantPersonas;

	private String destino;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaSalida;

	private String nombre;

	private String origen;

	//bi-directional many-to-one association to Servicio
	@OneToMany(mappedBy="transporte")
	@JsonBackReference
	private List<Servicio> servicios;

	//bi-directional many-to-one association to Tipotransporte
	@ManyToOne
	@JoinColumn(name="tipo")
	@JsonManagedReference
	private Tipotransporte tipotransporte;

	public Transporte() {
	}

	public int getIdtransporte() {
		return this.idtransporte;
	}

	public void setIdtransporte(int idtransporte) {
		this.idtransporte = idtransporte;
	}

	public int getCantPersonas() {
		return this.cantPersonas;
	}

	public void setCantPersonas(int cantPersonas) {
		this.cantPersonas = cantPersonas;
	}

	public String getDestino() {
		return this.destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Date getFechaSalida() {
		return this.fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getOrigen() {
		return this.origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public List<Servicio> getServicios() {
		return this.servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	public Servicio addServicio(Servicio servicio) {
		getServicios().add(servicio);
		servicio.setTransporte(this);

		return servicio;
	}

	public Servicio removeServicio(Servicio servicio) {
		getServicios().remove(servicio);
		servicio.setTransporte(null);

		return servicio;
	}

	public Tipotransporte getTipotransporte() {
		return this.tipotransporte;
	}

	public void setTipotransporte(Tipotransporte tipotransporte) {
		this.tipotransporte = tipotransporte;
	}

}