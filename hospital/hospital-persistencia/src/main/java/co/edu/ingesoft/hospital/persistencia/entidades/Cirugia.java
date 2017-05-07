/**
 * 
 */
package co.edu.ingesoft.hospital.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;

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
@Table(name="Cirugias")
public class Cirugia implements Serializable{

	@Id
	@Column(name="id_cirugia")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CIRUGIA_SEQ")
    @SequenceGenerator(sequenceName = "cirugia_seq", allocationSize = 1, name = "CIRUGIA_SEQ")
	private int idCirugia;
	
	@Column(name="descripcion",length=200)
	private String descripcion;
	
	@Column(name="tiempo_estimado",length=40)
	private String tiempoEstimado;
	
	@ManyToOne
	@JoinColumn(name="Orden_Cirugia",nullable=true)
	private OrdenCirugia ordenCirugia;
	
	public Cirugia(){
		
	}
	
}
