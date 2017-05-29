/**
 * 
 */
package co.edu.ingesoft.hospital.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author TOSHIBAP55W
 *
 */
@Entity
@Table(name="Citas_Examenes")
public class CitaExamen implements Serializable{

	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CITAEXAMENA_SEQ")
    @SequenceGenerator(sequenceName = "citaexamen_seq", allocationSize = 1, name = "CITAEXAMENA_SEQ")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="Cita")
	private Cita cita;
	
	
	@ManyToOne
	@JoinColumn(name="Examen")
	private Examen examen;
	
	
	public CitaExamen(){
		
	}


	/**
	 * @param cita
	 * @param examen
	 */
	public CitaExamen(Cita cita, Examen examen) {
		super();
		this.cita = cita;
		this.examen = examen;
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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


	/**
	 * @return the examen
	 */
	public Examen getExamen() {
		return examen;
	}


	/**
	 * @param examen the examen to set
	 */
	public void setExamen(Examen examen) {
		this.examen = examen;
	}
	
	
}
