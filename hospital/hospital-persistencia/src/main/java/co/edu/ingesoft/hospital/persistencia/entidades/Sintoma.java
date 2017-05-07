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
@Table(name="Sintoma")
public class Sintoma implements Serializable{

	@Id
	@Column(name="id_sintoma")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SINTOMA_SEQ")
    @SequenceGenerator(sequenceName = "sintoma_seq", allocationSize = 1, name = "SINTOMA_SEQ")
	private int idSintoma;
	
	@Column(name="nombre",length=40)
	private String nombreSintoma;
	
	@Column(name="descripcion",length=200)
	private String descripcion;
	
	public Sintoma(){
		
	}
}
