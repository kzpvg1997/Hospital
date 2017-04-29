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
@IdClass(ProcedimientoPatologiaPK.class)
@Entity
@Table(name="Procedimientos_Patologias")
public class ProcedimientoPatologia implements Serializable{

	@Id
	@ManyToOne
	@JoinColumn(name="patologias_Descritas")
	private PatologiasDescritas patologiaDescrita;
	
	@Id
	@ManyToOne
	@JoinColumn(name="procedimientos")
	private Procedimiento procedimiento;
	
	public ProcedimientoPatologia(){
		
	}
}
