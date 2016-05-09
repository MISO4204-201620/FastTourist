package fabricas.entidades.VOs;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the logs database table.
 * 
 */
public class LogVO implements Serializable {
	private static final long serialVersionUID = 1L;

	public int getIdlogs() {
		return idlogs;
	}

	public void setIdlogs(int idlogs) {
		this.idlogs = idlogs;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getModulo() {
		return modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = modulo;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public UsuarioVO getUsuarioBean() {
		return usuarioBean;
	}

	public void setUsuarioBean(UsuarioVO usuarioBean) {
		this.usuarioBean = usuarioBean;
	}

	private int idlogs;

	private int codigo;

	private String descripcion;

	private Date fecha;

	private String modulo;

	private String operacion;

	//bi-directional many-to-one association to Usuario

	private UsuarioVO usuarioBean;

	public LogVO() {
	}

	

}