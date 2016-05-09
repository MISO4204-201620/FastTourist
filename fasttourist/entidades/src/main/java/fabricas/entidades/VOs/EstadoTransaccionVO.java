package fabricas.entidades.VOs;

import java.io.Serializable;

import java.util.List;

public class EstadoTransaccionVO implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private int idestadoTransaccion;

	private String descripcion;

	private String nombre;

	private List<TransaccionesVO> transacciones;

	public EstadoTransaccionVO() {
	}

	public int getIdestadoTransaccion() {
		return idestadoTransaccion;
	}

	public void setIdestadoTransaccion(int idestadoTransaccion) {
		this.idestadoTransaccion = idestadoTransaccion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<TransaccionesVO> getTransacciones() {
		return transacciones;
	}

	public void setTransacciones(List<TransaccionesVO> transacciones) {
		this.transacciones = transacciones;
	}

}