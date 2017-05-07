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
@IdClass(SintomasPatologiasPK.class)
@Entity
@Table(name="Sintomas_Patologias")
public class SintomasPatologias implements Serializable {

	@Id
	@ManyToOne
	@JoinColumn(name="Patologias_Descritas")
	private PatologiasDescritas patologiaDescrita;
	
	@Id
	@ManyToOne
	@JoinColumn(name="Sintomas_id")
	private Sintoma sintoma;
	
	public SintomasPatologias(){
		
	}
	
}
