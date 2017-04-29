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

	
	private String farmacia;
	
	private String hospital;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((farmacia == null) ? 0 : farmacia.hashCode());
		result = prime * result + ((hospital == null) ? 0 : hospital.hashCode());
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
		if (farmacia == null) {
			if (other.farmacia != null)
				return false;
		} else if (!farmacia.equals(other.farmacia))
			return false;
		if (hospital == null) {
			if (other.hospital != null)
				return false;
		} else if (!hospital.equals(other.hospital))
			return false;
		return true;
	}
	
	
	
}
