package fabricas.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
	@NamedQuery(name="Busquedas.findAll", query="SELECT b FROM Busquedas b"),
	@NamedQuery(name="Busquedas.findByUser", query="SELECT  b FROM Busquedas b WHERE b.usuario.idusuario= :iduser order by b.fecha desc")
})
public class Busquedas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6517080757102444764L;

	@Id
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	
	//bi-directional many-to-one association to Servicio
	@ManyToOne
	@JoinColumn(name="servicio")
	private Servicio servicio;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="usuario")
	private Usuario usuario;

	public Busquedas() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public Servicio getServicio() {
		return this.servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
