package fabricas.presentacion.VOs;

import java.io.Serializable;
import java.util.List;


public class PaseosecologicoVO implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private int idPaseosEcologicos;

	private String lugar;

	private String nombre;

	private String descripcion;
	private String fotos;
	private int		precio;
	private String requerimientos;
	private int duracion;
	
	private List<ServicioVO> servicioVO;

	public PaseosecologicoVO() {
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

	public List<ServicioVO> getServicioVO() {
		return this.servicioVO;
	}

	public void setServicioVO(List<ServicioVO> servicioVO) {
		this.servicioVO = servicioVO;
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