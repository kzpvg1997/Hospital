/**
 * 
 */
package co.edu.ingesoft.hospital.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
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
@Entity
@Table(name="Hospitalizaciones")
public class Hospitalizaciones implements Serializable{

	@Id
	@Column(name="id")
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CAMA_SEQ")
    //@SequenceGenerator(sequenceName = "camas_seq", allocationSize = 1, name = "null")
	private int id;
	
	@OneToOne
	@JoinColumn(name="Cama",unique=true)
	private Cama cama;
	
	
	@OneToOne
	@JoinColumn(name="Cita",unique=true)
	private Cita cita;
	
	public Hospitalizaciones(){
		
	}
}
