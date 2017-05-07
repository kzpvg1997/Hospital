/**
 * 
 */
package co.edu.ingesoft.hospital.persistencia.entidades;

import java.io.Serializable;

/**
 * @author TOSHIBAP55W
 *
 */
public class MedicoEspecialistaPK implements Serializable{

	private int especializacion;
	
	private int medico;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + especializacion;
		result = prime * result + medico;
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
		MedicoEspecialistaPK other = (MedicoEspecialistaPK) obj;
		if (especializacion != other.especializacion)
			return false;
		if (medico != other.medico)
			return false;
		return true;
	}
	
	
}
