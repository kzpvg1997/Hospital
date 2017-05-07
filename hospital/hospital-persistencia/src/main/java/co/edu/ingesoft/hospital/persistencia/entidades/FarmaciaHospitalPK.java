/**
 * 
 */
package co.edu.ingesoft.hospital.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * @author TOSHIBAP55W
 *
 */
public class FarmaciaHospitalPK implements Serializable{

	
	private int farmacia;
	
	private int hospital;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + farmacia;
		result = prime * result + hospital;
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
		FarmaciaHospitalPK other = (FarmaciaHospitalPK) obj;
		if (farmacia != other.farmacia)
			return false;
		if (hospital != other.hospital)
			return false;
		return true;
	}

	
	
}
