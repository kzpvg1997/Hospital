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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author TOSHIBAP55W
 *
 */
@Entity
@Table(name="Especializaciones")
public class Especializaciones implements Serializable{

	@Id
	@Column(name="id_especializacion")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ESPECIALIZACIONES_SEQ")
    @SequenceGenerator(sequenceName = "especializaciones_seq", allocationSize = 1, name = "ESPECIALIZACIONES_SEQ")
	private int idEspecializacion;
	
	@Column(name="nombre",length=40)
	private String nombre;
	
	public Especializaciones(){
		
	}
}
