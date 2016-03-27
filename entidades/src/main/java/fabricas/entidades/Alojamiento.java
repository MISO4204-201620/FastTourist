package fabricas.entidades;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the alojamiento database table.
 * 
 */
@Entity
@Table(name="alojamiento")
@NamedQuery(name="Alojamiento.findAll", query="SELECT a FROM Alojamiento a")
public class Alojamiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idalojamiento;

	private int cantPersonas;

	private String ciudad;

	private String direccion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_entrada")
	private Date fechaEntrada;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_salida")
	private Date fechaSalida;

	private int habitaciones;

	private String nombre;

	private String telefono;
	
	private Boolean piscina;
	
	private Boolean wifi;
	
	private Boolean aire_acondicionado;

	//bi-directional many-to-one association to Servicio
	@OneToMany(mappedBy="alojamiento")
	@JsonBackReference
	private List<Servicio> servicios;
	

	public Alojamiento() {
	}

	public int getIdalojamiento() {
		return this.idalojamiento;
	}

	public void setIdalojamiento(int idalojamiento) {
		this.idalojamiento = idalojamiento;
	}

	public int getCantPersonas() {
		return this.cantPersonas;
	}

	public void setCantPersonas(int cantPersonas) {
		this.cantPersonas = cantPersonas;
	}

	public String getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Date getFechaEntrada() {
		return this.fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Date getFechaSalida() {
		return this.fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public int getHabitaciones() {
		return this.habitaciones;
	}

	public void setHabitaciones(int habitaciones) {
		this.habitaciones = habitaciones;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Servicio> getServicios() {
		return this.servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	public Servicio addServicio(Servicio servicio) {
		getServicios().add(servicio);
		servicio.setAlojamiento(this);

		return servicio;
	}

	public Servicio removeServicio(Servicio servicio) {
		getServicios().remove(servicio);
		servicio.setAlojamiento(null);

		return servicio;
	}

	public Boolean isPiscina() {
		return piscina;
	}

	public void setPiscina(Boolean piscina) {
		this.piscina = piscina;
	}

	public Boolean isWifi() {
		return wifi;
	}

	public void setWifi(Boolean wifi) {
		this.wifi = wifi;
	}

	public Boolean isAire_acondicionado() {
		return aire_acondicionado;
	}

	public void setAire_acondicionado(Boolean aire_acondicionado) {
		this.aire_acondicionado = aire_acondicionado;
	}

}