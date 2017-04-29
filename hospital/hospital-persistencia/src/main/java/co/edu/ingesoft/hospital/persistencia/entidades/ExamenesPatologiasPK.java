/**
 * 
 */
package co.edu.ingesoft.hospital.persistencia.entidades;

import java.io.Serializable;

/**
 * @author TOSHIBAP55W
 *
 */
public class ExamenesPatologiasPK implements Serializable{

	private int examen;
	
	private int patologiasDescritas;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + examen;
		result = prime * result + patologiasDescritas;
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
		ExamenesPatologiasPK other = (ExamenesPatologiasPK) obj;
		if (examen != other.examen)
			return false;
		if (patologiasDescritas != other.patologiasDescritas)
			return false;
		return true;
	}
	
	
	
}
