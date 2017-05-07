/**
 * 
 */
package co.edu.ingesoft.hospital.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author TOSHIBAP55W
 *
 */
@Entity
@Table(name="Citas_Examenes")
public class CitaExamen implements Serializable{

	
	@Id
	@Column(name="id")
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CAMA_SEQ")
    //@SequenceGenerator(sequenceName = "camas_seq", allocationSize = 1, name = "null")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="Cita")
	private Cita cita;
	
	
	@ManyToOne
	@JoinColumn(name="Examen")
	private Examen examen;
	
	
	public CitaExamen(){
		
	}
}
