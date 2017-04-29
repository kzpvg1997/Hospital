/**
 * 
 */
package co.edu.ingesoft.hospital.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author TOSHIBAP55W
 *
 */
@Entity
@Table(name="EPS")
public class Eps implements Serializable{

	@Id
	@Column(name="id_eps",length=16)
	private String idEps;
	
	@Column(name="nombre",length=40)
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name="Tipo_EPS")
	private TipoEPS tipoEPS;
	
	public Eps(){
		
	}
}
