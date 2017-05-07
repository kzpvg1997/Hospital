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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author TOSHIBAP55W
 *
 */
@Entity
@Table(name="Examenes")
public class Examen implements Serializable{

	@Id
	@Column(name="id_examen")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXAMEN_SEQ")
    @SequenceGenerator(sequenceName = "examen_seq", allocationSize = 1, name = "EXAMEN_SEQ")
	private int idExamen;
	
	@Column(name="nombre_examen",length=40)
	private String nombreExamen;
	
	@Column(name="descripcion",length=200)
	private String descripcion;
	
	public Examen(){
		
	}
}
