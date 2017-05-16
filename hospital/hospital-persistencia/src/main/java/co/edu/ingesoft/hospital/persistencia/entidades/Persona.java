/**
 * 
 */
package co.edu.ingesoft.hospital.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author TOSHIBAP55W
 *
 */
@Entity
@Table(name="PERSONAS")
@Inheritance(strategy=InheritanceType.JOINED)
@NamedQueries({
	@NamedQuery(name=Persona.ROLES_PERSONA,query="SELECT p.rol FROM Persona p WHERE p.identificacion=?1")
})
public class Persona implements Serializable{
	
	public static final String ROLES_PERSONA= "persona.rolXpersona";
	
	@Id
	@Column(name="identificacion")
	private int identificacion;
	
	@Column(name="nombre",length=40)
	private String nombre;
	
	@Column(name="apellido",length=40)
	private String apellido;
	
	@Column(name="telefono",length=40)
	private String telefono;
	
	@ManyToOne
	@JoinColumn(name="Rol")
	private Rol rol;
	
	public Persona (){
		
	}

	/**
	 * @param identificacion
	 * @param nombre
	 * @param apellido
	 * @param telefono
	 * @param rol
	 */
	public Persona(int identificacion, String nombre, String apellido, String telefono, Rol rol) {
		super();
		this.identificacion = identificacion;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.rol = rol;
	}

	/**
	 * @return the identificacion
	 */
	public int getIdentificacion() {
		return identificacion;
	}

	/**
	 * @param identificacion the identificacion to set
	 */
	public void setIdentificacion(int identificacion) {
		this.identificacion = identificacion;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the rol
	 */
	public Rol getRol() {
		return rol;
	}

	/**
	 * @param rol the rol to set
	 */
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	

}
