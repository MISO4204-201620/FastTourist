package fabricas.entidades.VOs;

import java.io.Serializable;

import java.util.List;


public class PerfilesVO implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private int idperfil;

	private String descripcion;

	private String nombre;

	private List<UsuarioVO> usuarios;

	public PerfilesVO() {
	}

	public int getIdperfil() {
		return idperfil;
	}

	public void setIdperfil(int idperfil) {
		this.idperfil = idperfil;
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

	public List<UsuarioVO> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<UsuarioVO> usuarios) {
		this.usuarios = usuarios;
	}


}