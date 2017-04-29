/**
 * 
 */
package co.edu.ingesoft.hospital.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author TOSHIBAP55W
 *
 */
@Entity
@Table(name="Pacientes")
public class Paciente implements Serializable{

	@Id
	@Column(name="identificacion",length=16)
	private String identificacion;
	
	@Column(name="nombre",length=40)
	private String nombre;
	
	@Column(name="apeliido",length=40)
	private String apellido;
	
	@Column(name="fecha_nacimiento")
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;
	
	public Paciente(){
		
	}
}
