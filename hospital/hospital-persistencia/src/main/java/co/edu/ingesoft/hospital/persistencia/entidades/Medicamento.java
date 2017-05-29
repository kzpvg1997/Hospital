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
@Table(name="Medicamentos")
public class Medicamento implements Serializable{

	@Id
	@Column(name="id_medicamento")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEDICAMENTO_SEQ")
    @SequenceGenerator(sequenceName = "medicamento_seq", allocationSize = 1, name = "MEDICAMENTO_SEQ")
	private int idMedicamento;
	
	@Column(name="nombre",length=40)
	private String nombre;
	
	@Column(name="descripcion",length=200)
	private String descripcion;
	
	@Column(name="precio",precision=40,scale=40)
	private double precio;

	public Medicamento(){
		
	}

	/**
	 * @return the idMedicamento
	 */
	public int getIdMedicamento() {
		return idMedicamento;
	}

	/**
	 * @param idMedicamento the idMedicamento to set
	 */
	public void setIdMedicamento(int idMedicamento) {
		this.idMedicamento = idMedicamento;
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
	 * @return the precio
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	
	
	
}
