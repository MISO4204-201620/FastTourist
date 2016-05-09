package fabricas.entidades.VOs;

import java.io.Serializable;

import java.util.List;


public class TipoalimentacionVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idtipoAlimentacion;

	private String nombre;
	private List<AlimentacionVO> alimentacions;

	public TipoalimentacionVO() {
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

	public List<AlimentacionVO> getAlimentacions() {
		return this.alimentacions;
	}

	public void setAlimentacions(List<AlimentacionVO> alimentacions) {
		this.alimentacions = alimentacions;
	}

}