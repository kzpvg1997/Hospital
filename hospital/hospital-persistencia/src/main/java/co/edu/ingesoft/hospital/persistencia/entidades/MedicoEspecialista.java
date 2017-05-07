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
@IdClass(MedicoEspecialistaPK.class)
@Entity
@Table(name="Medicos_Especialistas")
public class MedicoEspecialista implements Serializable{

	
	@Id
	@ManyToOne
	@JoinColumn(name="Especializacion")
	private Especializaciones especializacion;
	
	
	@Id
	@ManyToOne
	@JoinColumn(name="Medico")
	private Medico medico;
	
	public MedicoEspecialista(){
		
	}
}
