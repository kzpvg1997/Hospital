/**
 * 
 */
package co.edu.ingesoft.hospital.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cita")
	private int idCita;
	
	@Column(name="descripciones",length=200)
	private String descripcion;
	
	@Column(name="anotaciones",length=2000)
	private String anotacion;
	
//	@Column(name="hora_cita")
//	@Temporal(TemporalType.TIME)
//	private Date horaCita;
	
	@Column(name="fecha_cita")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCita;
	
	@Column(name="atendida")
	private boolean atendida;
	
	@ManyToOne
	@JoinColumn(name="Pacientes_id")
	private Paciente paciente;
	
	@ManyToOne
	@JoinColumn(name="Tipo_cita")
	private TipoCita tipoCita;
	
	@ManyToOne
	@JoinColumn(name="horarios_disponibles")
	private HorariosDisponibles horarioDisponible;
	
	@ManyToOne
	@JoinColumn(name="medico_id")
	private Medico medico;
	
	@ManyToOne
	@JoinColumn(name="cita_id")
	private Cita cita;
	
	public Cita(){
		
	}
}
