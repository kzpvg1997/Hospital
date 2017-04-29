/**
 * 
 */
package co.edu.ingesoft.hospital.persistencia.entidades;

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
@IdClass(ExamenesPatologiasPK.class)
@Entity
@Table(name="Examenes_Patologias")
public class ExamenesPatologias {

	@Id
	@ManyToOne
	@JoinColumn(name="examen_id")
	private Examen examen;
	
	@Id
	@ManyToOne
	@JoinColumn(name="patologias_descritas")
	private PatologiasDescritas patologiasDescritas;
	
	@ManyToOne
	@JoinColumn(name="cita_id")
	private Cita cita;
	
	
	public ExamenesPatologias(){
		
	}
}
