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
@Table(name="Medicamentos_Rescetas")
public class MedicamentosRecetas implements Serializable{

	
	@Id
	@Column(name="id_receta")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEDICAMENTOSRECETA_SEQ")
    @SequenceGenerator(sequenceName = "medicamentosrecetas_seq", allocationSize = 1, name = "MEDICAMENTOSRECETA_SEQ")
	private int idReceta;
	
	@ManyToOne
	@JoinColumn(name="Cita_id")
	private Cita cita;
	
	
	@Column(name="tipo_entrega",length=100)
	private String tipoEntrega;
	
	public MedicamentosRecetas(){
		
	}
}
