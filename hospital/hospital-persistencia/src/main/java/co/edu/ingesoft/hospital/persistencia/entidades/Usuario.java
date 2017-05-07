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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author TOSHIBAP55W
 *
 */
@Entity
@Table(name="Usuario")
public class Usuario implements Serializable{

	@Id
	@Column(name="codigo")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_SEQ")
    @SequenceGenerator(sequenceName = "usuario_seq", allocationSize = 1, name = "USUARIO_SEQ")
	private int codigo;
	
	@Column(name="usuario",length=40)
	private String usuario;
	
	@Column(name="password",length=40)
	private String password;
	
	@ManyToOne
	@JoinColumn(name="Persona",unique=true)
	private Persona persona;/**/
	
	public Usuario(){
		
	}
	
}
