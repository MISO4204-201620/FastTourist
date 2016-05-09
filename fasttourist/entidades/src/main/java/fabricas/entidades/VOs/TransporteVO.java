package fabricas.entidades.VOs;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TransporteVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idtransporte;

	private int cantPersonas;

	private String destino;
	
	private Date fechaSalida;

	private String nombre;

	private String origen;


	private List<ServicioVO> servicioVO;


	private TipotransporteVO tipotransporte;

	public TransporteVO() {
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

	public List<ServicioVO> getServicioVO() {
		return this.servicioVO;
	}

	public void setServicioVO(List<ServicioVO> serviciosVO) {
		this.servicioVO = serviciosVO;
	}
	public TipotransporteVO getTipotransporte() {
		return this.tipotransporte;
	}

	public void setTipotransporte(TipotransporteVO tipotransporte) {
		this.tipotransporte = tipotransporte;
	}

}