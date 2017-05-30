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
@IdClass(CitasPatologiasPK.class)
@Entity
@Table(name="Citas_Patologias")
public class CitasPatologias {

	@Id
	@ManyToOne
	@JoinColumn(name="Patologias_Descritas")
	private PatologiasDescritas patologiaDescrita;
	
	@Id
	@ManyToOne
	@JoinColumn(name="Cita")
	private Cita cita;
	
	public CitasPatologias(){
		
	}

	/**
	 * @return the patologiaDescrita
	 */
	public PatologiasDescritas getPatologiaDescrita() {
		return patologiaDescrita;
	}

	/**
	 * @param patologiaDescrita the patologiaDescrita to set
	 */
	public void setPatologiaDescrita(PatologiasDescritas patologiaDescrita) {
		this.patologiaDescrita = patologiaDescrita;
	}

	/**
	 * @return the cita
	 */
	public Cita getCita() {
		return cita;
	}

	/**
	 * @param cita the cita to set
	 */
	public void setCita(Cita cita) {
		this.cita = cita;
	}
	
	
	
}
