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
@Table(name="Farmacias")
public class Farmacia implements Serializable{

	@Id
	@Column(name="id_farmacia",length=16)
	private String idFarmacia;
	
	@Column(name="nombre",length=40)
	private String nombre;
	
	public Farmacia(){
		
	}
}
