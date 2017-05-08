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
public class Cita implements Serializable{

	@Id
	@Column(name="id_cita")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CITA_SEQ")
    @SequenceGenerator(sequenceName = "cita_seq", allocationSize = 1, name = "CITA_SEQ")
	private int idCita;
	
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
	@Temporal(TemporalType.TIME)
	private Date horaCita;	
	
	@ManyToOne
	@JoinColumn(name="Paciente")
	private Paciente paciente;
	
	@ManyToOne
	@JoinColumn(name="Medico")
	private Medico medico;
	
	
	public Cita(){
		
	}
}
