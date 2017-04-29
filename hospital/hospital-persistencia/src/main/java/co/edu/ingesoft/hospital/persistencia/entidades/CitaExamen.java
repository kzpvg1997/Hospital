/**
 * 
 */
package co.edu.ingesoft.hospital.persistencia.entidades;

import java.io.Serializable;

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
@IdClass(CitaExamenPK.class)
@Entity
@Table(name="Citas_Examenes")
public class CitaExamen implements Serializable{

	
	@Id
	@ManyToOne
	@JoinColumn(name="cita_id")
	private Cita cita;
	
	@Id
	@ManyToOne
	@JoinColumn(name="examen_id")
	private Examen examen;
	
	
	public CitaExamen(){
		
	}
}
