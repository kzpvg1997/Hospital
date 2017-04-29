/**
 * 
 */
package co.edu.ingesoft.hospital.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author TOSHIBAP55W
 *
 */
@Entity
@Table(name="Resultados")
public class Resultados implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_resultado")
	private int idResultado;
	
	@Column(name="nombre_Resultado",length=16)
	private String nombreResultado;
	
	@Column(name="descripcion",length=200)
	private String descripcion;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="cita_id",referencedColumnName="cita_id"),
		@JoinColumn(name="examen_id",referencedColumnName="examen_id")	
	})
	private CitaExamen citaExamen;
	
	public Resultados(){
		
	}
}
