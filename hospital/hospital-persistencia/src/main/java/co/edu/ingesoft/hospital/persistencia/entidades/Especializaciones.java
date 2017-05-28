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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author TOSHIBAP55W
 *
 */
@Entity
@Table(name="Especializaciones")
public class Especializaciones implements Serializable{

	@Id
	@Column(name="id_especializacion")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ESPECIALIZACIONES_SEQ")
    @SequenceGenerator(sequenceName = "especializaciones_seq", allocationSize = 1, name = "ESPECIALIZACIONES_SEQ")
	private int idEspecializacion;
	
	@Column(name="nombre",length=40)
	private String nombre;
	
	public Especializaciones(){
		
	}

	/**
	 * @return the idEspecializacion
	 */
	public int getIdEspecializacion() {
		return idEspecializacion;
	}

	/**
	 * @param idEspecializacion the idEspecializacion to set
	 */
	public void setIdEspecializacion(int idEspecializacion) {
		this.idEspecializacion = idEspecializacion;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return nombre;
	}
	
	
}
