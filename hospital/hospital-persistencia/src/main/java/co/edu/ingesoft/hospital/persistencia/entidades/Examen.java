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
@Table(name="Examenes")
@NamedQueries({
	@NamedQuery(name=Examen.EXAMEN_POR_NOMBRE,query="SELECT e FROM Examen e where e.nombreExamen=?1")
})
public class Examen implements Serializable{

	/**
	 * Consulta que retorna los examenes por nombre
	 */
	public static final String EXAMEN_POR_NOMBRE ="Examen.examenXnombre";
	
	@Id
	@Column(name="id_examen")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXAMEN_SEQ")
    @SequenceGenerator(sequenceName = "examen_seq", allocationSize = 1, name = "EXAMEN_SEQ")
	private int idExamen;
	
	@Column(name="nombre_examen",length=40)
	private String nombreExamen;
	
	@Column(name="descripcion",length=200)
	private String descripcion;
	
	public Examen(){
		
	}

	/**
	 * @return the idExamen
	 */
	public int getIdExamen() {
		return idExamen;
	}

	/**
	 * @param idExamen the idExamen to set
	 */
	public void setIdExamen(int idExamen) {
		this.idExamen = idExamen;
	}

	/**
	 * @return the nombreExamen
	 */
	public String getNombreExamen() {
		return nombreExamen;
	}

	/**
	 * @param nombreExamen the nombreExamen to set
	 */
	public void setNombreExamen(String nombreExamen) {
		this.nombreExamen = nombreExamen;
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
