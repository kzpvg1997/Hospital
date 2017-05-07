/**
 * 
 */
package co.edu.ingesoft.hospital.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import co.edu.ingesoft.hospital.persistencia.enumeraciones.TipoCitaEnum;
import co.edu.ingesoft.hospital.persistencia.enumeraciones.TiposMedicosEnum;

/**
 * @author TOSHIBAP55W
 *
 */
@Entity
@Table(name="Medicos")
public class Medico  extends Persona implements Serializable{

		
	@ManyToOne
	@JoinColumn(name="hospital_id")
	private Hospital hospital;
	
	@Column(name="tipo_medico",length=40)
	@Enumerated(EnumType.STRING)
	private TiposMedicosEnum tipoMedico;
	
	public Medico(){
		
	}

	/**
	 * @param identificacion
	 * @param nombre
	 * @param apellido
	 * @param telefono
	 */
	public Medico(int identificacion, String nombre, String apellido, String telefono,Hospital hospital,TiposMedicosEnum tipoMedico) {
		super();
		
		this.hospital = hospital;
		this.tipoMedico = tipoMedico;
	}
	
}
