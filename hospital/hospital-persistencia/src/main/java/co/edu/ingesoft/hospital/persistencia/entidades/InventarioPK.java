/**
 * 
 */
package co.edu.ingesoft.hospital.persistencia.entidades;

import java.io.Serializable;

/**
 * @author TOSHIBAP55W
 *
 */
public class InventarioPK implements Serializable{

	private int medicamento;
	
	private int farmacia;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + farmacia;
		result = prime * result + medicamento;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InventarioPK other = (InventarioPK) obj;
		if (farmacia != other.farmacia)
			return false;
		if (medicamento != other.medicamento)
			return false;
		return true;
	}

	/**
	 * @return the medicamento
	 */
	public int getMedicamento() {
		return medicamento;
	}

	/**
	 * @param medicamento the medicamento to set
	 */
	public void setMedicamento(int medicamento) {
		this.medicamento = medicamento;
	}

	/**
	 * @return the farmacia
	 */
	public int getFarmacia() {
		return farmacia;
	}

	/**
	 * @param farmacia the farmacia to set
	 */
	public void setFarmacia(int farmacia) {
		this.farmacia = farmacia;
	}

	
	
	
}
