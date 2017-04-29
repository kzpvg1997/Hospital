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
@IdClass(CitasPendientesPK.class)
@Entity
@Table(name="Citas_Pendientes")
public class CitasPendientes implements Serializable{

	@Id
	@ManyToOne
	@JoinColumn(name="medico_id")
	private Medico medico;
	
	@Id
	@ManyToOne
	@JoinColumn(name="cita_id")
	private Cita cita;
	
	public CitasPendientes(){
		
	}
}
