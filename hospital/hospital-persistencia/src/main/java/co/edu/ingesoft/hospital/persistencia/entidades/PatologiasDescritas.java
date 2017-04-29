/**
 * 
 */
package co.edu.ingesoft.hospital.persistencia.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author TOSHIBAP55W
 *
 */
@Entity
@Table(name="Patologias_Descritas")
public class PatologiasDescritas {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_patologia")
	private int idPatologia;
	
	@Column(name="nombre_patologia",length=40)
	private String nombrePatologia;
	
	@Column(name="descripcion",length=200)
	private String descripcion;
	
	public PatologiasDescritas(){
		
	}
}
