/**
 * 
 */
package co.edu.ingesoft.hospital.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author TOSHIBAP55W
 *
 */
@Entity
@Table(name="Procedimientos")
public class Procedimiento implements Serializable{

	@Id
	@Column(name="codigo_procedimiento",length=16)
	private String codigoProcedimiento;
	
	@Column(name="nombre_procedimiento",length=40)
	private String nombreProcedimiento;
	
	@Column(name="descripcion",length=200)
	private String descripcion;
	
	
	@ManyToOne
	@JoinColumn(name="medico_id")
	private Medico medico;
	
	@ManyToOne
	@JoinColumn(name="cita_id")
	private Cita cita;
	
	public Procedimiento(){
		
	}
}
