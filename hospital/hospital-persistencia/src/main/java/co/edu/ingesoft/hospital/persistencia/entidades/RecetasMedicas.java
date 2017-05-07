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
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import co.edu.ingesoft.hospital.persistencia.enumeraciones.TipoCitaEnum;
import co.edu.ingesoft.hospital.persistencia.enumeraciones.TipoEntregaEnum;

/**
 * @author TOSHIBAP55W
 *
 */
@Entity
@Table(name="Recetas_Medicas")
public class RecetasMedicas implements Serializable{

	@Id
	@Column(name="id_receta")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RECETASMEDICAS_SEQ")
    @SequenceGenerator(sequenceName = "recetasmedicas_seq", allocationSize = 1, name = "RECETASMEDICAS_SEQ")
	private int idReceta;
	
	@ManyToOne
	@JoinColumn(name="Cita")
	private Cita cita;
	
	@Column(name="tipo_entrega",length=100,nullable=false)
	@Enumerated(EnumType.STRING)
	private TipoEntregaEnum tipoEntrega;
	
	
	public RecetasMedicas(){
		
	}
}
