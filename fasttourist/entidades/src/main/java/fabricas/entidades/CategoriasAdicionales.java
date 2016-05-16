package fabricas.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="categorias_adicionales")
@NamedQueries({
	@NamedQuery(name="CategoriasAdicionales.findAll", query="SELECT a FROM CategoriasAdicionales a"),
	@NamedQuery(name="CategoriasAdicionales.getCategoria", query="SELECT a FROM CategoriasAdicionales a where a.nombre = :nombre"),
	@NamedQuery(name="CategoriasAdicionales.getbyId", query="SELECT a FROM CategoriasAdicionales a where a.id =:id")
})
public class CategoriasAdicionales implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	
	
	private String nombre;
	
	private String atributo;
	
	private String valor;
	
	
	public CategoriasAdicionales() {
		
	}

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAtributo() {
		return atributo;
	}

	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
	
}
