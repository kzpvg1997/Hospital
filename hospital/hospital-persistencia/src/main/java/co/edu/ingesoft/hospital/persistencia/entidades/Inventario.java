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
@IdClass(InventarioPK.class)
@Entity
@Table(name="Inventario")
public class Inventario implements Serializable{

	@Id
	@ManyToOne
	@JoinColumn(name="Medicamento")
	private Medicamento medicamento;
	
	@Id
	@ManyToOne
	@JoinColumn(name="Farmacia")
	private Farmacia farmacia;
	
	@Column(name="cantidad")
	private int cantidad;
	
	public Inventario(){
		
	}

	/**
	 * @return the medicamento
	 */
	public Medicamento getMedicamento() {
		return medicamento;
	}

	/**
	 * @param medicamento the medicamento to set
	 */
	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	/**
	 * @return the farmacia
	 */
	public Farmacia getFarmacia() {
		return farmacia;
	}

	/**
	 * @param farmacia the farmacia to set
	 */
	public void setFarmacia(Farmacia farmacia) {
		this.farmacia = farmacia;
	}

	/**
	 * @return the cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
	
	
	
}
