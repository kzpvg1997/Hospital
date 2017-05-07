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
}
