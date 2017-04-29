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
@IdClass(HospitalizacionesPK.class)
@Entity
@Table(name="Hospitalizaciones")
public class Hospitalizaciones implements Serializable{

	
	@Id
	@OneToOne
	@JoinColumn(name="cama_id",unique=true)
	private Cama cama;
	
	@Id
	@OneToOne
	@JoinColumn(name="paciente_id",unique=true)
	private Paciente paciente;
	
	public Hospitalizaciones(){
		
	}
}
