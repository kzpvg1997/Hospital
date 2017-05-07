/**
 * 
 */
package co.edu.ingesoft.hospital.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author TOSHIBAP55W
 *
 */
@Entity
@Table(name="Farmacias")
public class Farmacia implements Serializable{

	@Id
	@Column(name="id_farmacia")
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CAMA_SEQ")
    //@SequenceGenerator(sequenceName = "camas_seq", allocationSize = 1, name = "null")
	private int idFarmacia;
	
	@Column(name="nombre",length=40,nullable=false)
	private String nombre;
	
	@OneToOne
	@JoinColumn(name="Usuario")
	private Usuario usuario;
	
	public Farmacia(){
		
	}
}
