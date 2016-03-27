package fabricas.entidades;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the servicios database table.
 * 
 */
@Entity
@Table(name="servicios")
@NamedQueries({
	@NamedQuery(name="Servicio.findAll", query="SELECT s FROM Servicio s"),
	@NamedQuery(name="Servicio.findById", query="SELECT s FROM Servicio s where s.activo = :id"),
	@NamedQuery(name="Servicio.findAlimentacion", query="SELECT s FROM Servicio s where s.activo=1 and s.categoria=2"),
	@NamedQuery(name="Servicio.findProveedoresByAlimentacion", query="SELECT DISTINCT(s.usuario) FROM Servicio s WHERE UPPER(s.categoria.nombre) LIKE UPPER('%alimentacion%')")
}) 

public class Servicio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idservicios;

	private Boolean activo;

	private String descripcion;

	private BigDecimal descuento;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	private String nombre;

	private BigDecimal precio;

	@Column(name="ruta_galeria")
	private String rutaGaleria;

	//bi-directional many-to-one association to Calificacione
	@OneToMany(mappedBy="servicio")
	@JsonManagedReference
	private List<Calificaciones> calificaciones;


	//bi-directional many-to-one association to Pregunta
	@OneToMany(mappedBy="servicio")
	@JsonManagedReference
	private List<Preguntas> preguntas;
	
	//bi-directional many-to-one association to Carrito
	@OneToMany(mappedBy="servicio")
	@JsonBackReference
	private List<Carrito> carrito;

	//bi-directional many-to-one association to Categoria
	@ManyToOne
	@JoinColumn(name="categoria")
	private Categoria categoria;

	//bi-directional many-to-many association to Paquete
	@ManyToMany
	@JoinTable(
			name="servicios_por_paquete"
			, joinColumns={
					@JoinColumn(name="servicio")
			}
			, inverseJoinColumns={
					@JoinColumn(name="paquete")
			}
			)
	private List<Paquete> paquetes;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="proveedor")
	@JsonManagedReference
	private Usuario usuario;

	//bi-directional many-to-one association to Alimentacion
	@ManyToOne
	@JoinColumn(name="alimentacion")
	@JsonManagedReference
	private Alimentacion alimentacion;

	//bi-directional many-to-one association to Alojamiento
	@ManyToOne
	@JoinColumn(name="alojamiento")
	@JsonManagedReference
	private Alojamiento alojamiento;

	//bi-directional many-to-one association to Paseosecologico
	@ManyToOne
	@JoinColumn(name="paseo_eco")
	@JsonManagedReference
	private Paseosecologico paseosecologico;

	//bi-directional many-to-one association to Transporte
	@ManyToOne
	@JoinColumn(name="transporte")
	@JsonManagedReference
	private Transporte transporte;

	//bi-directional many-to-one association to Transacciones
	@OneToMany(mappedBy="servicio")
	@JsonBackReference
	private List<Transacciones> transacciones;
	
	public Servicio() {
	}

	public int getIdservicios() {
		return this.idservicios;
	}

	public void setIdservicios(int idservicios) {
		this.idservicios = idservicios;
	}

	public Boolean getActivo() {
		return this.activo;
	}

	public void setActivo(Boolean  activo) {
		this.activo = activo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getDescuento() {
		return this.descuento;
	}

	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getPrecio() {
		return this.precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public String getRutaGaleria() {
		return this.rutaGaleria;
	}

	public void setRutaGaleria(String rutaGaleria) {
		this.rutaGaleria = rutaGaleria;
	}

	public List<Calificaciones> getCalificaciones() {
		return this.calificaciones;
	}

	public void setCalificaciones(List<Calificaciones> calificaciones) {
		this.calificaciones = calificaciones;
	}

	public Calificaciones addCalificacione(Calificaciones calificaciones) {
		getCalificaciones().add(calificaciones);
		calificaciones.setServicio(this);

		return calificaciones;
	}

	public Calificaciones removeCalificacione(Calificaciones calificaciones) {
		getCalificaciones().remove(calificaciones);
		calificaciones.setServicio(null);

		return calificaciones;
	}


	public List<Preguntas> getPreguntas() {
		return this.preguntas;
	}

	public List<Carrito> getCarrito() {
		return carrito;
	}

	public void setCarrito(List<Carrito> carrito) {
		this.carrito = carrito;
	}

	public void setPreguntas(List<Preguntas> preguntas) {
		this.preguntas = preguntas;
	}

	public Preguntas addPreguntas(Preguntas preguntas) {
		getPreguntas().add(preguntas);
		preguntas.setServicio(this);

		return preguntas;
	}

	public Preguntas removePreguntas(Preguntas preguntas) {
		getPreguntas().remove(preguntas);
		preguntas.setServicio(null);

		return preguntas;
	}
	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Paquete> getPaquetes() {
		return this.paquetes;
	}

	public void setPaquetes(List<Paquete> paquetes) {
		this.paquetes = paquetes;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Alimentacion getAlimentacion() {
		return this.alimentacion;
	}

	public void setAlimentacion(Alimentacion alimentacion) {
		this.alimentacion = alimentacion;
	}

	public Alojamiento getAlojamiento() {
		return this.alojamiento;
	}

	public void setAlojamiento(Alojamiento alojamiento) {
		this.alojamiento = alojamiento;
	}

	public Paseosecologico getPaseosecologico() {
		return this.paseosecologico;
	}

	public void setPaseosecologico(Paseosecologico paseosecologico) {
		this.paseosecologico = paseosecologico;
	}

	public Transporte getTransporte() {
		return this.transporte;
	}

	public void setTransporte(Transporte transporte) {
		this.transporte = transporte;
	}

	public List<Transacciones> getTransacciones() {
		return transacciones;
	}

	public void setTransacciones(List<Transacciones> transacciones) {
		this.transacciones = transacciones;
	}

}