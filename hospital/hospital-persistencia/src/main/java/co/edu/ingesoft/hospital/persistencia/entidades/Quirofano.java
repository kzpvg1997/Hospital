/**
 * 
 */
package co.edu.ingesoft.hospital.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

import co.edu.ingesoft.hospital.persistencia.enumeraciones.TipoCitaEnum;
import co.edu.ingesoft.hospital.persistencia.enumeraciones.TipoQuirofanoEnum;

/**
 * @author TOSHIBAP55W
 *
 */
@Entity
@Table(name="Quirofanos")
public class Quirofano implements Serializable{

	@Id
	@Column(name="id_quirofano")
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CAMA_SEQ")
    //@SequenceGenerator(sequenceName = "camas_seq", allocationSize = 1, name = "null")
	private int idQuirofano;
	
	@Column(name="localzacion",length=100)
	private String localizacion;
	
	@Column(name="descripcion",length=200)
	private String descripcion;
	
	@Column(name="tipo_quirofano",length=40)
	@Enumerated(EnumType.STRING)
	private TipoQuirofanoEnum tipoQuirofano;
	
	@Column(name="ocupado")
	private boolean ocupado;
	
	
	@ManyToOne
	@JoinColumn(name="Hospital_id")
	private Hospital hospital;
	
	public Quirofano(){
		
	}
	
}
