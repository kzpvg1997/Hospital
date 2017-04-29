/**
 * 
 */
package co.edu.ingesoft.hospital.persistencia.entidades;

import java.io.Serializable;

/**
 * @author TOSHIBAP55W
 *
 */
public class RecetasMedicasPK implements Serializable{

	private String medicamento;
	
	private int cita;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cita;
		result = prime * result + ((medicamento == null) ? 0 : medicamento.hashCode());
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
		RecetasMedicasPK other = (RecetasMedicasPK) obj;
		if (cita != other.cita)
			return false;
		if (medicamento == null) {
			if (other.medicamento != null)
				return false;
		} else if (!medicamento.equals(other.medicamento))
			return false;
		return true;
	}
	
	
}
