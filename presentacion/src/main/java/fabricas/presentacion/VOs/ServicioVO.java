package fabricas.presentacion.VOs;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the servicios database table.
 * 
 */

public class ServicioVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idservicios;
	private Boolean activo;
	private String descripcion;
	private BigDecimal descuento;
	private Date fechaCreacion;
	private String nombre;
	private BigDecimal precio;
	private String rutaGaleria;
	private List<CalificacionesVO> calificaciones;
	private CategoriaVO categoria;	
	private UsuarioVO usuario;
	private List<PaqueteVO> paquetes;
	private AlimentacionVO alimentacion;
	private AlojamientoVO alojamiento;
	private PaseosecologicoVO paseosecologico;
	private TransporteVO transporte;
	private List<PreguntasVO> preguntas;
	private List<CarritoVO> carrito;
	private List<TransaccionesVO> transacciones;

	public ServicioVO() {
	}

	/**
	 * @return the idservicios
	 */
	public int getIdservicios() {
		return idservicios;
	}

	/**
	 * @param idservicios the idservicios to set
	 */
	public void setIdservicios(int idservicios) {
		this.idservicios = idservicios;
	}

	public Boolean isActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getDescuento() {
		return descuento;
	}

	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
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

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public String getRutaGaleria() {
		return rutaGaleria;
	}

	public void setRutaGaleria(String rutaGaleria) {
		this.rutaGaleria = rutaGaleria;
	}

	public List<CalificacionesVO> getCalificaciones() {
		return calificaciones;
	}

	public void setCalificaciones(List<CalificacionesVO> calificaciones) {
		this.calificaciones = calificaciones;
	}

	public CategoriaVO getCategoria() {
		return categoria;
	}

	public void setCategoriaBean(CategoriaVO categoria) {
		this.categoria = categoria;
	}

	public UsuarioVO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}

	public List<PaqueteVO> getPaquetes() {
		return paquetes;
	}

	public void setPaquetes(List<PaqueteVO> paquetes) {
		this.paquetes = paquetes;
	}

	public AlimentacionVO getAlimentacion() {
		return alimentacion;
	}

	public void setAlimentacion(AlimentacionVO alimentacion) {
		this.alimentacion = alimentacion;
	}

	public AlojamientoVO getAlojamiento() {
		return alojamiento;
	}

	public void setAlojamiento(AlojamientoVO alojamiento) {
		this.alojamiento = alojamiento;
	}

	public PaseosecologicoVO getPaseosecologico() {
		return paseosecologico;
	}

	public void setPaseosecologico(PaseosecologicoVO paseosecologico) {
		this.paseosecologico = paseosecologico;
	}

	public TransporteVO getTransporte() {
		return transporte;
	}

	public void setTransporte(TransporteVO transporte) {
		this.transporte = transporte;
	}

	public void setCategoria(CategoriaVO categoria) {
		this.categoria = categoria;
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

	public List<TransaccionesVO> getTransacciones() {
		return transacciones;
	}

	public void setTransacciones(List<TransaccionesVO> transacciones) {
		this.transacciones = transacciones;
	}	
}
