/**
 * 
 */
package co.edu.ingesoft.hospital.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author TOSHIBAP55W
 *
 */
@Entity
@Table(name="Administradores")
public class Administrador extends Persona implements Serializable {


	@Column(name="codigo_administrativo",length=40)
	private String codigoAdministrativo;
	
	public Administrador(){
		super();
	}

	/**
	 * @param identificacion
	 * @param nombre
	 * @param apellido
	 * @param telefono
	 */
	public Administrador(int identificacion, String nombre, String apellido, String telefono, String codigoAdministrativo) {
		super();
		
		this.codigoAdministrativo=codigoAdministrativo;
		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.valueOf(getIdentificacion());
	}
}
