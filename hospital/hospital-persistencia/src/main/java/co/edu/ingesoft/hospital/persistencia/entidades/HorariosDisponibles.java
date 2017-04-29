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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author TOSHIBAP55W
 *
 */
@Entity
@Table(name="Horarios_Disponibles")
public class HorariosDisponibles implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_horario")
	private int idHorario;
	
	@Column(name="hora_inicio")
	@Temporal(TemporalType.TIME)
	private Date horaInicio;
	
	@Column(name="hora_fin")
	@Temporal(TemporalType.TIME)
	private Date horaFin;
	
	@Column(name="fecha")
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	public HorariosDisponibles(){
		
	}
	
}
