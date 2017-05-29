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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author TOSHIBAP55W
 *
 */
@Entity
@Table(name="Sintomas")
@NamedQueries({
	@NamedQuery(name=Sintoma.BUSCAR_SINTOMA_NOMBRE,query="SELECT s FROM Sintoma s WHERE s.nombreSintoma=?1")
})
public class Sintoma implements Serializable{

	public static final String BUSCAR_SINTOMA_NOMBRE = "Sintoma.BuscarXNombre";
	
	@Id
	@Column(name="id_sintoma")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SINTOMA_SEQ")
    @SequenceGenerator(sequenceName = "sintoma_seq", allocationSize = 1, name = "SINTOMA_SEQ")
	private int idSintoma;
	
	@Column(name="nombre",length=40)
	private String nombreSintoma;
	
	@Column(name="descripcion",length=200)
	private String descripcion;
	
	public Sintoma(){
		
	}

	/**
	 * @param nombreSintoma
	 * @param descripcion
	 */
	public Sintoma(String nombreSintoma, String descripcion) {
		super();
		this.nombreSintoma = nombreSintoma;
		this.descripcion = descripcion;
	}

	/**
	 * @return the idSintoma
	 */
	public int getIdSintoma() {
		return idSintoma;
	}

	/**
	 * @param idSintoma the idSintoma to set
	 */
	public void setIdSintoma(int idSintoma) {
		this.idSintoma = idSintoma;
	}

	/**
	 * @return the nombreSintoma
	 */
	public String getNombreSintoma() {
		return nombreSintoma;
	}

	/**
	 * @param nombreSintoma the nombreSintoma to set
	 */
	public void setNombreSintoma(String nombreSintoma) {
		this.nombreSintoma = nombreSintoma;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
