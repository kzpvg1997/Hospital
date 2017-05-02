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
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author TOSHIBAP55W
 *
 */
@IdClass(RecetasMedicasPK.class)
@Entity
@Table(name="Recetas_Medicas")
public class RecetasMedicas implements Serializable{

	@Id
	@ManyToOne
	@JoinColumn(name="id_cita")
	private Cita cita;
	
	@Id
	@ManyToOne
	@JoinColumn(name="medicamentos_id")
	private Medicamento medicamento;
	
	
	public RecetasMedicas(){
		
	}
}
