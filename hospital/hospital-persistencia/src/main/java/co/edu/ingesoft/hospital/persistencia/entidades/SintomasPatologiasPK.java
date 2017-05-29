/**
 * 
 */
package co.edu.ingesoft.hospital.persistencia.entidades;

import java.io.Serializable;

/**
 * @author TOSHIBAP55W
 *
 */
public class SintomasPatologiasPK implements Serializable{

	
	private int patologiaDescrita;
	
	private int sintoma;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + patologiaDescrita;
		result = prime * result + sintoma;
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
		SintomasPatologiasPK other = (SintomasPatologiasPK) obj;
		if (patologiaDescrita != other.patologiaDescrita)
			return false;
		if (sintoma != other.sintoma)
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
