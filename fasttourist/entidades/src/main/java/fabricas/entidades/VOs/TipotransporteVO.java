package fabricas.entidades.VOs;

import java.io.Serializable;


import java.util.List;

public class TipotransporteVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idtipotransporte;

	private String nombre;

	private List<TransporteVO> transportes;

	public TipotransporteVO() {
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

	public List<TransporteVO> getTransportes() {
		return this.transportes;
	}

	public void setTransportes(List<TransporteVO> transportes) {
		this.transportes = transportes;
	}
}