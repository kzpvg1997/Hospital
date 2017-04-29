/**
 * 
 */
package co.edu.ingesoft.hospital.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author TOSHIBAP55W
 *
 */
@IdClass(ProcedimientoQuirofanoPK.class)
@Entity
@Table(name="Procedimiento_Quirofano")
public class ProcedimientoQuirofano implements Serializable{

	@Id
	@ManyToOne
	@JoinColumn(name="procedimientos_id")
	private Procedimiento procedimiento;
	
	@Id
	@ManyToOne
	@JoinColumn(name="quirofanos_id")
	private Quirofano quirofano;
	
	
	@ManyToOne
	@JoinColumn(name="cirugia_id",nullable=true)
	private Cirugia cirugia;
	
	public ProcedimientoQuirofano(){
		
	}
}
