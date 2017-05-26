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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * @author TOSHIBAP55W
 *
 */
@Entity
@Table(name="Citas")
@NamedQueries({
	@NamedQuery(name=Cita.LISTA_CITA,query="SELECT c FROM Cita c ")
})
public class Cita implements Serializable{
	
	public static final String LISTA_CITA ="cita.Lista";

	@Id
	@Column(name="id_cita")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CITA_SEQ")
    @SequenceGenerator(sequenceName = "cita_seq", allocationSize = 1, name = "CITA_SEQ")
	private Integer idCita;
	
	@Column(name="descripcion",length=400)
	private String descripcion;
	
	@Column(name="anotaciones",length=400)
	private String anotacion;
	
	@Column(name="tipo_cita",length=40)
	private String tipoCita;
	
	@Column(name="atendida")
	private boolean atendida;
	
	@ManyToOne
	@JoinColumn(name="Cita_id",nullable=true)
	private Cita cita;
	
	@Column(name="fecha_cita")
	@Temporal(TemporalType.DATE)
	private Date fechaCita;
	
	@Column(name="hora_cita")
	private String horaCita;	
	
	@ManyToOne
	@JoinColumn(name="Paciente")
	private Paciente paciente;
	
	@ManyToOne
	@JoinColumn(name="Medico")
	private Medico medico;
	
	
	public Cita(){
		
	}


	public Cita(String descripcion, String anotacion, String tipoCita, boolean atendida, Cita cita,
			Date fechaCita, String horaCita, Paciente paciente, Medico medico) {
		super();
		this.descripcion = descripcion;
		this.anotacion = anotacion;
		this.tipoCita = tipoCita;
		this.atendida = atendida;
		this.cita = cita;
		this.fechaCita = fechaCita;
		this.horaCita = horaCita;
		this.paciente = paciente;
		this.medico = medico;
	}




	/**
	 * @return the idCita
	 */
	public Integer getIdCita() {
		return idCita;
	}


	/**
	 * @param idCita the idCita to set
	 */
	public void setIdCita(Integer idCita) {
		this.idCita = idCita;
	}


	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}


	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	/**
	 * @return the anotacion
	 */
	public String getAnotacion() {
		return anotacion;
	}


	/**
	 * @param anotacion the anotacion to set
	 */
	public void setAnotacion(String anotacion) {
		this.anotacion = anotacion;
	}


	/**
	 * @return the tipoCita
	 */
	public String getTipoCita() {
		return tipoCita;
	}


	/**
	 * @param tipoCita the tipoCita to set
	 */
	public void setTipoCita(String tipoCita) {
		this.tipoCita = tipoCita;
	}


	/**
	 * @return the atendida
	 */
	public boolean isAtendida() {
		return atendida;
	}


	/**
	 * @param atendida the atendida to set
	 */
	public void setAtendida(boolean atendida) {
		this.atendida = atendida;
	}


	/**
	 * @return the cita
	 */
	public Cita getCita() {
		return cita;
	}


	/**
	 * @param cita the cita to set
	 */
	public void setCita(Cita cita) {
		this.cita = cita;
	}


	/**
	 * @return the fechaCita
	 */
	public Date getFechaCita() {
		return fechaCita;
	}


	/**
	 * @param fechaCita the fechaCita to set
	 */
	public void setFechaCita(Date fechaCita) {
		this.fechaCita = fechaCita;
	}


	/**
	 * @return the horaCita
	 */
	public String getHoraCita() {
		return horaCita;
	}


	/**
	 * @param horaCita the horaCita to set
	 */
	public void setHoraCita(String horaCita) {
		this.horaCita = horaCita;
	}


	/**
	 * @return the paciente
	 */
	public Paciente getPaciente() {
		return paciente;
	}


	/**
	 * @param paciente the paciente to set
	 */
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}


	/**
	 * @return the medico
	 */
	public Medico getMedico() {
		return medico;
	}


	/**
	 * @param medico the medico to set
	 */
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	
	
	
}
