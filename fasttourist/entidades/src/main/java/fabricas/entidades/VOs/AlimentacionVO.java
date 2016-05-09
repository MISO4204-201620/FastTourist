package fabricas.entidades.VOs;

import java.io.Serializable;

import java.util.List;



public class AlimentacionVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idalimentacion;

	private String nombre;
	
	private TipoalimentacionVO tipoalimentacion;

	private List<ServicioVO> servicioVO;

	public AlimentacionVO() {
	}

	public int getIdalimentacion() {
		return this.idalimentacion;
	}

	public void setIdalimentacion(int idalimentacion) {
		this.idalimentacion = idalimentacion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TipoalimentacionVO getTipoalimentacion() {
		return this.tipoalimentacion;
	}

	public void setTipoalimentacion(TipoalimentacionVO tipoalimentacion) {
		this.tipoalimentacion = tipoalimentacion;
	}

	public List<ServicioVO> getServicioVO() {
		return this.servicioVO;
	}

	public void setServicioVO(List<ServicioVO> servicioVO) {
		this.servicioVO = servicioVO;
	}

}