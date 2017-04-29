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
import javax.persistence.Table;

/**
 * @author TOSHIBAP55W
 *
 */
@Entity
@Table(name="Tratamientos")
public class Tratamiento implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_tratamiento")
	private int idTratamiento;
	
	@Column(name="nombre_tratamiento",length=40)
	private String nombreTratamiento;
	
	@Column(name="descripcion",length=200)
	private String descripcion;
	
	public Tratamiento(){
		
	}
}
