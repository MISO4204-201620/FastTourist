package fabricas.entidades.VOs;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the paquetes database table.
 * 
 */
public class PaqueteVO implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private int idpaquetes;

	private String descripcion;

	private BigDecimal precio;
		
	private Date fechaCreacion;	
	
	private Date fechaExpiracion;

	private String nombre;
	
	private List<ServicioVO> servicios;
	
	private UsuarioVO usuario;

	
	public PaqueteVO() {
	}

	public int getIdpaquetes() {
		return idpaquetes;
	}

	public void setIdpaquetes(int idpaquetes) {
		this.idpaquetes = idpaquetes;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaExpiracion() {
		return fechaExpiracion;
	}

	public void setFechaExpiracion(Date fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}
	
	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<ServicioVO> getServicios() {
		return servicios;
	}

	public void setServicios(List<ServicioVO> servicios) {
		this.servicios = servicios;
	}

	/**
	 * @return the usuario
	 */
	public UsuarioVO getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}	
}