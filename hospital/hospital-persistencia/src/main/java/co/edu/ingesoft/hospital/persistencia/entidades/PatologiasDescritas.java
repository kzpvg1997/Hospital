/**
 * 
 */
package co.edu.ingesoft.hospital.persistencia.entidades;

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
@Table(name="Patologias_Descritas")
@NamedQueries({
	@NamedQuery(name=PatologiasDescritas.BUSCAR_PATOLOGIA_NOMBRE,query="SELECT pd FROM PatologiasDescritas pd WHERE pd.nombrePatologia=?1")
})
public class PatologiasDescritas {

	public static final String BUSCAR_PATOLOGIA_NOMBRE = "PatologiasDescritas.BuscarXNombre";
	
	@Id
	@Column(name="id_patologia")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PATOLOGIASDESCRITAS_SEQ")
    @SequenceGenerator(sequenceName = "patologiasdescritas_seq", allocationSize = 1, name = "PATOLOGIASDESCRITAS_SEQ")
	private int idPatologia;
	
	@Column(name="nombre",length=40)
	private String nombrePatologia;
	
	@Column(name="descripcion",length=200)
	private String descripcion;
	
	public PatologiasDescritas(){
		
	}

	/**
	 * @param nombrePatologia
	 * @param descripcion
	 */
	public PatologiasDescritas(String nombrePatologia, String descripcion) {
		super();
		this.nombrePatologia = nombrePatologia;
		this.descripcion = descripcion;
	}

	/**
	 * @return the idPatologia
	 */
	public int getIdPatologia() {
		return idPatologia;
	}

	/**
	 * @param idPatologia the idPatologia to set
	 */
	public void setIdPatologia(int idPatologia) {
		this.idPatologia = idPatologia;
	}

	/**
	 * @return the nombrePatologia
	 */
	public String getNombrePatologia() {
		return nombrePatologia;
	}

	/**
	 * @param nombrePatologia the nombrePatologia to set
	 */
	public void setNombrePatologia(String nombrePatologia) {
		this.nombrePatologia = nombrePatologia;
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
