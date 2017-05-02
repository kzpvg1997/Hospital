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
@Table(name="Medicos")
public class Medico implements Serializable{

	@Id
	@Column(name="documento",length=16)
	private String documento;
	
	@Column(name="nombre",length=40)
	private String nombre;
	
	@Column(name="apellido",length=40)
	private String apellido;
	
	
	@ManyToOne
	@JoinColumn(name="horarios_disponibles")
	private HorariosDisponibles horariosDisponibles;
	
	@ManyToOne
	@JoinColumn(name="hospital_id")
	private Hospital hospital;
	
	public Medico(){
		
	}
}
