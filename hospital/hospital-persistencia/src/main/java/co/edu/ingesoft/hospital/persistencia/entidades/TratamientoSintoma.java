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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author TOSHIBAP55W
 *
 */
@IdClass(TratamientoSintomaPK.class)
@Entity
@Table(name="Tratamientos_Sintomas")
public class TratamientoSintoma implements Serializable{


	@Id
	@ManyToOne
	@JoinColumn(name="Tratamientos_id")
	private Tratamiento tratamiento;
	
	@Id
	@ManyToOne
	@JoinColumn(name="Sintomas_id")
	private Sintoma sintoma;
	
	public TratamientoSintoma(){
		
	}
}
