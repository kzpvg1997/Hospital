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

import org.hibernate.annotations.ManyToAny;


/**
 * @author TOSHIBAP55W
 *
 */
@Entity
@Table(name="Quirofanos")
public class Quirofano implements Serializable{

	@Id
	@Column(name="id_quirofano")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QUIROFANO_SEQ")
    @SequenceGenerator(sequenceName = "quirofano_seq", allocationSize = 1, name = "QUIROFANO_SEQ")
	private int idQuirofano;
	
	@Column(name="localzacion",length=100)
	private String localizacion;
	
	@Column(name="descripcion",length=200)
	private String descripcion;
	
	@Column(name="tipo_quirofano",length=40)
	private String tipoQuirofano;
	
	@Column(name="ocupado")
	private boolean ocupado;
	
	
	@ManyToOne
	@JoinColumn(name="Hospital_id")
	private Hospital hospital;
	
	public Quirofano(){
		
	}
	
}
