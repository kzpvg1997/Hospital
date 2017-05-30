/**
 * 
 */
package co.edu.ingesoft.hospital.persistencia.entidades;

import java.io.Serializable;

/**
 * @author TOSHIBAP55W
 *
 */
public class CitasPatologiasPK implements Serializable {

	private int patologiaDescrita;
	
	private int cita;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cita;
		result = prime * result + patologiaDescrita;
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
		CitasPatologiasPK other = (CitasPatologiasPK) obj;
		if (cita != other.cita)
			return false;
		if (patologiaDescrita != other.patologiaDescrita)
			return false;
		return true;
	}

	/**
	 * @return the patologiaDescrita
	 */
	public int getPatologiaDescrita() {
		return patologiaDescrita;
	}

	/**
	 * @param patologiaDescrita the patologiaDescrita to set
	 */
	public void setPatologiaDescrita(int patologiaDescrita) {
		this.patologiaDescrita = patologiaDescrita;
	}

	/**
	 * @return the cita
	 */
	public int getCita() {
		return cita;
	}

	/**
	 * @param cita the cita to set
	 */
	public void setCita(int cita) {
		this.cita = cita;
	}
	
	
}
