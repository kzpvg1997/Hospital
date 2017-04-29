/**
 * 
 */
package co.edu.ingesoft.hospital.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * @author TOSHIBAP55W
 *
 */
@IdClass(FarmaciaHospitalPK.class)
@Entity
@Table(name="Farmacia_Hospital")
public class FarmaciaHospital implements Serializable{

	
	@Id
	@ManyToOne
	@JoinColumn(name="id_farmacia")
	private Farmacia farmacia;
	
	
	@Id
	@ManyToOne
	@JoinColumn(name="id_hospital")
	private Hospital hospital;
	
	public FarmaciaHospital(){
		
	}
}
