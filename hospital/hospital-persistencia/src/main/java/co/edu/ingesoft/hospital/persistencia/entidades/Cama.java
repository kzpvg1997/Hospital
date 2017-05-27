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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author TOSHIBAP55W
 *
 */
@Entity
@Table(name="Camas")
@NamedQueries({
	@NamedQuery(name=Cama.ListaCamas,query="SELECT c FROM Cama c WHERE c.hospital=?1"),
})
public class Cama implements Serializable{

	public static final String ListaCamas = "Cama.listarCamas";
	
	@Id	
	@Column(name="numero_cama")
	private int numeroCama;
	
	@Column(name="disponible")
	private boolean disponible;
	
	@Column(name="descripcion",length=400)
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name="Hospital")
	private Hospital hospital;
	
	public Cama(){
		
	}

	/**
	 * @param numeroCama
	 * @param disponible
	 * @param descripcion
	 * @param hospital
	 */
	public Cama(int numeroCama, boolean disponible, String descripcion, Hospital hospital) {
		super();
		this.numeroCama = numeroCama;
		this.disponible = disponible;
		this.descripcion = descripcion;
		this.hospital = hospital;
	}

	/**
	 * @return the numeroCama
	 */
	public int getNumeroCama() {
		return numeroCama;
	}

	/**
	 * @param numeroCama the numeroCama to set
	 */
	public void setNumeroCama(int numeroCama) {
		this.numeroCama = numeroCama;
	}

	/**
	 * @return the disponible
	 */
	public boolean isDisponible() {
		return disponible;
	}

	/**
	 * @param disponible the disponible to set
	 */
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the hospital
	 */
	public Hospital getHospital() {
		return hospital;
	}

	/**
	 * @param hospital the hospital to set
	 */
	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.valueOf(numeroCama);
	}
	
	
}
