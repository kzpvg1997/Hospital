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
@Table(name="Hospitales")
public class Hospital implements Serializable{

	@Id
	@Column(name="id_hospital",length=16)
	private String idHospital;
	
	@Column(name="nombre",length=40)
	private String nombre;
	
	public Hospital(){
		
	}
}
