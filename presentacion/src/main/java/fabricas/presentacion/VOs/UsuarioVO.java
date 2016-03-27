package fabricas.presentacion.VOs;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the usuarios database table.
 * 
 */

public class UsuarioVO implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private int idusuario;

	private boolean activo;

	private String apellido;

	private String direccion;

	private String email;

	private Date fechaCreacion;

	private String login;

	private String nombre;

	private String password;

	private String telefono;
	
	
	private List<CalificacionesVO> Calificaciones;

	private List<LogVO> logs;
	
	private List<ServicioVO> servicios;

	private List<TransaccionesVO> transacciones;

	private PerfilesVO perfil;
	
	private List<PreguntasVO> preguntas;
	
	private List<CarritoVO> carrito;

	public UsuarioVO() {
	}

	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<CalificacionesVO> getCalificaciones() {
		return Calificaciones;
	}

	public void setCalificaciones(List<CalificacionesVO> calificaciones) {
		Calificaciones = calificaciones;
	}

	public List<LogVO> getLogs() {
		return logs;
	}

	public void setLogs(List<LogVO> logs) {
		this.logs = logs;
	}

	public List<ServicioVO> getServicios() {
		return servicios;
	}

	public void setServicios(List<ServicioVO> servicios) {
		this.servicios = servicios;
	}

	public List<TransaccionesVO> getTransacciones() {
		return transacciones;
	}

	public void setTransacciones(List<TransaccionesVO> transacciones) {
		this.transacciones = transacciones;
	}

	public PerfilesVO getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilesVO perfile) {
		this.perfil = perfile;
	}

	/**
	 * @return the preguntas
	 */
	public List<PreguntasVO> getPreguntas() {
		return preguntas;
	}

	/**
	 * @param preguntas the preguntas to set
	 */
	public void setPreguntas(List<PreguntasVO> preguntas) {
		this.preguntas = preguntas;
	}

	/**
	 * @return the carrito
	 */
	public List<CarritoVO> getCarrito() {
		return carrito;
	}

	/**
	 * @param carrito the carrito to set
	 */
	public void setCarrito(List<CarritoVO> carrito) {
		this.carrito = carrito;
	}
}