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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author TOSHIBAP55W
 *
 */
@Entity
@Table(name="PACIENTES")

@NamedQueries({
	@NamedQuery(name=Paciente.ListaPaciente,query="SELECT p FROM Paciente p")
})
public class Paciente extends Persona implements Serializable{
	
	public static final String ListaPaciente = "Paciente.listarPaciente";
		
	@Column(name="email",length=200)
	private String email;
	
	@Column(name="fecha_nacimiento")
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;
	
	@Column(name="genero",length=100)
	private String genero;
	
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
			String genero,Eps eps) {
		super();
	
		this.email=email;
		this.genero=genero;
		this.fechaNacimiento=fechaNacimiento;
		this.eps=eps;
				
		}


	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * @return the fechaNacimiento
	 */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}


	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	/**
	 * @return the genero
	 */
	public String getGenero() {
		return genero;
	}


	/**
	 * @param genero the genero to set
	 */
	public void setGenero(String genero) {
		this.genero = genero;
	}


	/**
	 * @return the eps
	 */
	public Eps getEps() {
		return eps;
	}


	/**
	 * @param eps the eps to set
	 */
	public void setEps(Eps eps) {
		this.eps = eps;
	}
	
	
}
