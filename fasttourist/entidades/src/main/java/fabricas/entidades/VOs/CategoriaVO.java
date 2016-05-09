package fabricas.entidades.VOs;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the categorias database table.
 * 
 */
public class CategoriaVO implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private int idcategorias;

	private String descripcion;

	
	private Date fechaCreacion;

	private String nombre;

	
	private List<ServicioVO> servicios;
	
	public CategoriaVO() {
	}

	public int getIdcategorias() {
		return idcategorias;
	}

	public void setIdcategorias(int idcategorias) {
		this.idcategorias = idcategorias;
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

	
}