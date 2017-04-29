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
@IdClass(PatologiasTratablesPK.class)
@Entity
@Table(name="Patologias_Tratables")
public class PatologiasTratables implements Serializable{

	
	@Id
	@ManyToOne
	@JoinColumn(name="patologias_Descritas")
	private PatologiasDescritas patologias;
	
	@Id
	@ManyToOne
	@JoinColumn(name="sintoma_id")
	private Sintoma sintoma;
	
	public PatologiasTratables(){
		
	}
	
}
