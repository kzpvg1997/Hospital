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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author TOSHIBAP55W
 *
 */
@Entity
@Table(name="Accesos")
public class Accesos implements Serializable{

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCESO_SEQ")
    @SequenceGenerator(sequenceName = "acceso_seq", allocationSize = 1, name = "ACCESO_SEQ")
	private int id;
	
	@Column(name="url",length=40)
	private String url;
	
	@ManyToOne
	@JoinColumn(name="Rol")
	private Rol rol;
	
	public Accesos(){
		
	}
}
