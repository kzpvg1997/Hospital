/**
 * 
 */
package co.edu.ingesoft.hospital.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.edu.ingesoft.hospital.persistencia.enumeraciones.GeneroEnum;
import co.edu.ingesoft.hospital.persistencia.enumeraciones.TipoCitaEnum;

/**
 * @author TOSHIBAP55W
 *
 */
@Entity
@Table(name="Pacientes")
public class Paciente extends Persona implements Serializable{

	
	@Column(name="email",length=200)
	private String email;
	
	@Column(name="fecha_nacimiento")
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;
	
	@Column(name="genero",length=100)
	@Enumerated(EnumType.STRING)
	private GeneroEnum genero;
	
	@ManyToOne
	@JoinColumn(name="EPS")
	private Eps eps;
	
	
	public Paciente(){
		
	}


	/**
	 * @param identificacion
	 * @param nombre
	 * @param apellido
	 * @param telefono
	 */
	public Paciente(int identificacion, String nombre, String apellido, String telefono,String email,Date fechaNacimiento,
			GeneroEnum genero,Eps eps) {
		super();
	
		this.email=email;
		this.genero=genero;
		this.fechaNacimiento=fechaNacimiento;
		this.eps=eps;
				
		}
	
	
}
