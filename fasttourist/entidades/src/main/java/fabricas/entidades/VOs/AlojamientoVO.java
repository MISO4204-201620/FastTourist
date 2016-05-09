package fabricas.entidades.VOs;

import java.io.Serializable;
import java.util.Date;
import java.util.List;



public class AlojamientoVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idalojamiento;

	private int cantPersonas;

	private String ciudad;

	private String direccion;

	private Date fechaEntrada;

	private Date fechaSalida;

	private int habitaciones;

	private String nombre;

	private String telefono;

	private Boolean piscina;
	
	private Boolean wifi;
	
	private Boolean aire_acondicionado;
	
	private List<ServicioVO> servicioVO;

	public AlojamientoVO() {
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

	public List<ServicioVO> getServicioVO() {
		return this.servicioVO;
	}

	public void setServicioVO(List<ServicioVO> servicioVO) {
		this.servicioVO = servicioVO;
	}

	public Boolean getPiscina() {
		return piscina;
	}

	public void setPiscina(Boolean piscina) {
		this.piscina = piscina;
	}

	public Boolean getWifi() {
		return wifi;
	}

	public void setWifi(Boolean wifi) {
		this.wifi = wifi;
	}

	public Boolean getAire_acondicionado() {
		return aire_acondicionado;
	}

	public void setAire_acondicionado(Boolean aire_acondicionado) {
		this.aire_acondicionado = aire_acondicionado;
	}

}