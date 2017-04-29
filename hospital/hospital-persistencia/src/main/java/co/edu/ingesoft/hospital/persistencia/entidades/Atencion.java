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
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

/**
 * @author TOSHIBAP55W
 *
 */
@Entity
@Table(name="Atenciones")
public class Atencion implements Serializable{

	@Id
	@Column(name="id",length=16)
	private String id;
	
	@Column(name="descripcion",length=200)
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name="Pacientes_id",unique=true)
	private Paciente paciente;
	
	@ManyToOne
	@JoinColumn(name="EPS_id")
	private Eps EPS;
	

	public Atencion(){
		
	}
}
