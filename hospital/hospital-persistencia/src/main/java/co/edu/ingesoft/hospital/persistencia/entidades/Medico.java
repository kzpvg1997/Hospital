/**
 * 
 */
package co.edu.ingesoft.hospital.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * @author TOSHIBAP55W
 *
 */
@Entity
@Table(name="Medicos")
@NamedQueries({
	@NamedQuery(name=Medico.ListaMedico,query="SELECT m FROM Medico m")
})
public class Medico  extends Persona implements Serializable{

	
	public static final String ListaMedico = "Medico.listarMedico";
		
	@ManyToOne
	@JoinColumn(name="Hospital")
	private Hospital hospital;
	
	@Column(name="tipo_medico",length=40)
	private String tipoMedico;
	
	public Medico(){
		
	}

	/**
	 * @param identificacion
	 * @param nombre
	 * @param apellido
	 * @param telefono
	 */
	public Medico(int identificacion, String nombre, String apellido, String telefono,Hospital hospital,String tipoMedico) {
		super();
		
		this.hospital = hospital;
		this.tipoMedico = tipoMedico;
	}

	/**
	 * @return the hospital
	 */
	public Hospital getHospital() {
		return hospital;
	}

	/**
	 * @param hospital the hospital to set
	 */
	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	/**
	 * @return the tipoMedico
	 */
	public String getTipoMedico() {
		return tipoMedico;
	}

	/**
	 * @param tipoMedico the tipoMedico to set
	 */
	public void setTipoMedico(String tipoMedico) {
		this.tipoMedico = tipoMedico;
	}


	
	
	
	
	
}
