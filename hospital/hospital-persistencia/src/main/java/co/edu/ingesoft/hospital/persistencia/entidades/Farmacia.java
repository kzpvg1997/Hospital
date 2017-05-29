/**
 * 
 */
package co.edu.ingesoft.hospital.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author TOSHIBAP55W
 *
 */
@Entity
@Table(name="Farmacias")

@NamedQueries({
	@NamedQuery(name=Farmacia.usuarioFarmacia,query="SELECT f FROM Farmacia f WHERE f.usuario=?1")
})
public class Farmacia implements Serializable{
	
	public static final String usuarioFarmacia ="Farmacia.UsuarioXFarmacia";

	@Id
	@Column(name="id_farmacia")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FARMACIA_SEQ")
    @SequenceGenerator(sequenceName = "farmacia_seq", allocationSize = 1, name = "FARMACIA_SEQ")
	private int idFarmacia;
	
	@Column(name="nombre",length=40,nullable=false)
	private String nombre;
	
	@OneToOne
	@JoinColumn(name="Usuario")
	private Usuario usuario;
	
	public Farmacia(){
		
	}

	/**
	 * @return the idFarmacia
	 */
	public int getIdFarmacia() {
		return idFarmacia;
	}

	/**
	 * @param idFarmacia the idFarmacia to set
	 */
	public void setIdFarmacia(int idFarmacia) {
		this.idFarmacia = idFarmacia;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
	
}
