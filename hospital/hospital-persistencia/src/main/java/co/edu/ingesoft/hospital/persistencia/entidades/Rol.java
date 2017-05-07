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
@Table(name="Roles")
public class Rol implements Serializable{

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROL_SEQ")
    @SequenceGenerator(sequenceName = "rol_seq", allocationSize = 1, name = "ROL_SEQ")
	private int idRol;
	
	@Column(name="nombre",length=40)
	private String nombre;
	
	public Rol(){
		
	}
}
