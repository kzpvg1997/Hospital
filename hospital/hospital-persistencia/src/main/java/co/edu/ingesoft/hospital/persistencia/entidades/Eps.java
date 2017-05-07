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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import co.edu.ingesoft.hospital.persistencia.enumeraciones.TipoEpsEnum;

/**
 * @author TOSHIBAP55W
 *
 */
@Entity
@Table(name="EPS")
public class Eps implements Serializable{

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EPS_SEQ")
    @SequenceGenerator(sequenceName = "eps_seq", allocationSize = 1, name = "EPS_SEQ")
	private int idEps;
	
	@Column(name="nombre",length=40)
	private String nombre;
	
	@Column(name="tipo_eps",length=100)
	@Enumerated(EnumType.STRING)
	private TipoEpsEnum tipoEps;
	
	public Eps(){
		
	}
}
