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
import javax.persistence.Table;

/**
 * @author TOSHIBAP55W
 *
 */
@Entity
@Table(name="Cirugias")
public class Cirugia implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cirugia")
	private int idCirugia;
	
	@Column(name="descripcion",length=200)
	private String descripcion;
	
	@Column(name="Tiempo_estimado")
	private int tiempoEstimado;
	
	public Cirugia(){
		
	}
	
}
