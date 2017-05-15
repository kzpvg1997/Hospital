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
@Table(name="Horarios_Disponibles")
@NamedQueries({
	@NamedQuery(name=HorariosDisponibles.ListaHorarios,query="SELECT h FROM HorariosDisponibles h")
})
public class HorariosDisponibles implements Serializable{
	
	public static final String ListaHorarios = "HorariosDisponibles.listarHorarios";

	@Id
	@Column(name="id_horario")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HORARIOS_SEQ")
    @SequenceGenerator(sequenceName = "horarios_seq", allocationSize = 1, name = "HORARIOS_SEQ")
	private int idHorario;
	
	@Column(name="hora_inicio")
	private int horaInicio;
	
	@Column(name="hora_fin")
	private int horaFin;
	
	@Column(name="fecha")
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	@ManyToOne
	@JoinColumn(name="Medico")
	private Medico medico;
	
	public HorariosDisponibles(){
		
	}

	/**
	 * @return the idHorario
	 */
	public int getIdHorario() {
		return idHorario;
	}

	/**
	 * @param idHorario the idHorario to set
	 */
	public void setIdHorario(int idHorario) {
		this.idHorario = idHorario;
	}

	/**
	 * @return the horaInicio
	 */
	public int getHoraInicio() {
		return horaInicio;
	}

	/**
	 * @param horaInicio the horaInicio to set
	 */
	public void setHoraInicio(int horaInicio) {
		this.horaInicio = horaInicio;
	}

	/**
	 * @return the horaFin
	 */
	public int getHoraFin() {
		return horaFin;
	}

	/**
	 * @param horaFin the horaFin to set
	 */
	public void setHoraFin(int horaFin) {
		this.horaFin = horaFin;
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getHoraInicio()+"am-"+getHoraFin()+"pm";
	}
	
	
	
	
}
