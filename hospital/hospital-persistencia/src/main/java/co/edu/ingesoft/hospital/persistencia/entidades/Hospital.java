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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author TOSHIBAP55W
 *
 */
@Entity
@Table(name="Hospitales")
public class Hospital implements Serializable{

	@Id
	@Column(name="id_hospital")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HOSPITAL_SEQ")
    @SequenceGenerator(sequenceName = "hospital_seq", allocationSize = 1, name = "HOSPITAL_SEQ")
	private int idHospital;
	
	@Column(name="nombre",length=40)
	private String nombre;
	
	@OneToOne
	@JoinColumn(name="Usuario")
	private Usuario usuario;
	
	public Hospital(){
		
	}
}
