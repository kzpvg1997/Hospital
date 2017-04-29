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
import javax.persistence.Table;

/**
 * @author TOSHIBAP55W
 *
 */
@Entity
@Table(name="Camas")
public class Cama implements Serializable{

	
	@Id	
	@Column(name="numero_cama")
	private int numeroCama;
	
	@Column(name="disponible")
	private boolean disponible;
	
	@ManyToOne
	@JoinColumn(name="hospital_id")
	private Hospital hospital;
	
	public Cama(){
		
	}
}
