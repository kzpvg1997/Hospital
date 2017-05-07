/**
 * 
 */
package co.edu.ingesoft.hospital.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * @author TOSHIBAP55W
 *
 */
@Entity
@Table(name="EPS")
@NamedQueries({
	@NamedQuery(name=Eps.LISTA_EPS,query="SELECT e FROM Eps e ")
})
public class Eps implements Serializable{

	public static final String LISTA_EPS ="eps.Lista";
	
	@Id
	@Column(name="id_eps")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EPS_SEQ")
    @SequenceGenerator(sequenceName = "eps_seq", allocationSize = 1, name = "EPS_SEQ")
	private int idEps;
	
	@Column(name="nombre",length=40)
	private String nombre;
	
	@Column(name="tipo_eps",length=100)
	private String tipoEps;
	
	public Eps(){
		
	}

	/**
	 * @return the idEps
	 */
	public int getIdEps() {
		return idEps;
	}

	/**
	 * @param idEps the idEps to set
	 */
	public void setIdEps(int idEps) {
		this.idEps = idEps;
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
	 * @return the tipoEps
	 */
	public String getTipoEps() {
		return tipoEps;
	}

	/**
	 * @param tipoEps the tipoEps to set
	 */
	public void setTipoEps(String tipoEps) {
		this.tipoEps = tipoEps;
	}
	
	
}
