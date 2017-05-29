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
import javax.persistence.JoinColumns;
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
@Table(name="Resultados")
public class Resultados implements Serializable{

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RESULTADOS_SEQ")
    @SequenceGenerator(sequenceName = "resultados_seq", allocationSize = 1, name = "RESULTADOS_SEQ")
	private int id;
	
	@Column(name="nombre_resultado",length=40)
	private String nombreResultado;
	
	@Column(name="descripcion",length=200)
	private String descripcion;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="cita_id",referencedColumnName="Cita"),
		@JoinColumn(name="examen_id",referencedColumnName="Examen")	
	})
	private CitaExamen citaExamen;
	
	
	@Column(name="fecha_resultado")
	@Temporal(TemporalType.DATE)
	private Date fechaResultado;
	
	public Resultados(){
		
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the nombreResultado
	 */
	public String getNombreResultado() {
		return nombreResultado;
	}

	/**
	 * @param nombreResultado the nombreResultado to set
	 */
	public void setNombreResultado(String nombreResultado) {
		this.nombreResultado = nombreResultado;
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
	 * @return the citaExamen
	 */
	public CitaExamen getCitaExamen() {
		return citaExamen;
	}

	/**
	 * @param citaExamen the citaExamen to set
	 */
	public void setCitaExamen(CitaExamen citaExamen) {
		this.citaExamen = citaExamen;
	}

	/**
	 * @return the fechaResultado
	 */
	public Date getFechaResultado() {
		return fechaResultado;
	}

	/**
	 * @param fechaResultado the fechaResultado to set
	 */
	public void setFechaResultado(Date fechaResultado) {
		this.fechaResultado = fechaResultado;
	}
	
	
}
