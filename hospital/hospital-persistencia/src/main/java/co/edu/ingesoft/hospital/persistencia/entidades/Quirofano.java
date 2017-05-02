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

import org.hibernate.annotations.ManyToAny;

/**
 * @author TOSHIBAP55W
 *
 */
@Entity
@Table(name="Quirofanos")
public class Quirofano implements Serializable{

	@Id
	@Column(name="id_quirofano",length=16)
	private int idQuirofano;
	
	@Column(name="localzacion",length=100)
	private String localizacion;
	
	@Column(name="descripcion",length=200)
	private String descripcion;
	
	@Column(name="ocupado")
	private boolean ocupado;
	
	
	@ManyToOne
	@JoinColumn(name="hospital_id")
	private Hospital hospital;
	
	public Quirofano(){
		
	}
	
}
