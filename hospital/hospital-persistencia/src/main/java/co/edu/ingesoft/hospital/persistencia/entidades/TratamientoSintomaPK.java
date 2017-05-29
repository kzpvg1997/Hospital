/**
 * 
 */
package co.edu.ingesoft.hospital.persistencia.entidades;

import java.io.Serializable;

/**
 * @author TOSHIBAP55W
 *
 */
public class TratamientoSintomaPK implements Serializable{

	private int tratamiento;
	
	private int sintoma;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + sintoma;
		result = prime * result + tratamiento;
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
		TratamientoSintomaPK other = (TratamientoSintomaPK) obj;
		if (sintoma != other.sintoma)
			return false;
		if (tratamiento != other.tratamiento)
			return false;
		return true;
	}

	/**
	 * @return the tratamiento
	 */
	public int getTratamiento() {
		return tratamiento;
	}

	/**
	 * @param tratamiento the tratamiento to set
	 */
	public void setTratamiento(int tratamiento) {
		this.tratamiento = tratamiento;
	}

	/**
	 * @return the sintoma
	 */
	public int getSintoma() {
		return sintoma;
	}

	/**
	 * @param sintoma the sintoma to set
	 */
	public void setSintoma(int sintoma) {
		this.sintoma = sintoma;
	}
	
	
	
}
