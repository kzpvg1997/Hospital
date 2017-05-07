/**
 * 
 */
package co.edu.ingesoft.hospital.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * @author TOSHIBAP55W
 *
 */
@Entity
@Table(name="Orden_cirugia")
public class OrdenCirugia implements Serializable {

	@Id
	@Column(name="numero_orden")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDENCIRUGIA_SEQ")
    @SequenceGenerator(sequenceName = "ordencirugia_seq", allocationSize = 1, name = "ORDENCIRUGIA_SEQ")
	private int numeroOrden;
	
	@Column(name="nombre_cirugia",length=40)
	private String nombreCirugia;
	
	@Column(name="descripcion",length=400)
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name="Cita_id")
	private Cita cita;
	
	@ManyToOne
	@JoinColumn(name="Medico")
	private Medico medico;
	
	@Column(name="Tipo_procedimiento",length=40)
	private String tipoProcedimiento;
	
	public OrdenCirugia(){
		
	}
}
