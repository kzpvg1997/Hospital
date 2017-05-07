/**
 * 
 */
package co.edu.ingesoft.hospital.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author TOSHIBAP55W
 *
 */
@Entity
@Table(name="Medicamentos")
public class Medicamento implements Serializable{

	@Id
	@Column(name="id_medicamento")
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CAMA_SEQ")
    //@SequenceGenerator(sequenceName = "camas_seq", allocationSize = 1, name = "null")
	private int idMedicamento;
	
	@Column(name="nombre",length=40)
	private String nombre;
	
	@Column(name="descripcion",length=200)
	private String descripcion;
	
	@Column(name="precio",precision=40,scale=40)
	private double precio;

	public Medicamento(){
		
	}
}
