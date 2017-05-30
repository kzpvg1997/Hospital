/**
 * 
 */
package co.edu.ingesoft.hospital.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;


/**
 * @author TOSHIBAP55W
 *
 */
@Entity
@Table(name="Quirofanos")

@NamedQueries({
	@NamedQuery(name=Quirofano.LISTA_QUIROFANO,query="SELECT q FROM Quirofano q ")
})
public class Quirofano implements Serializable{
	
	public static final String LISTA_QUIROFANO ="quirofano.Lista";
	

	@Id
	@Column(name="id_quirofano")
	private int idQuirofano;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="localizacion",length=100)
	private String localizacion;
	
	@Column(name="descripcion",length=200)
	private String descripcion;
	
	@Column(name="tipo_quirofano",length=40)
	private String tipoQuirofano;
	
	@Column(name="ocupado")
	private boolean ocupado;
	
	
	@ManyToOne
	@JoinColumn(name="Hospital_id")
	private Hospital hospital;
	
	public Quirofano(){
		

	}
	/**
	 * @param idQuirofano
	 * @param nombre
	 * @param localizacion
	 * @param descripcion
	 * @param tipoQuirofano
	 * @param ocupado
	 * @param hospital
	 */
	public Quirofano(int idQuirofano, String nombre, String localizacion, String descripcion, String tipoQuirofano,
			boolean ocupado, Hospital hospital) {
		super();
		this.idQuirofano = idQuirofano;
		this.nombre = nombre;
		this.localizacion = localizacion;
		this.descripcion = descripcion;
		this.tipoQuirofano = tipoQuirofano;
		this.ocupado = ocupado;
		this.hospital = hospital;
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
	 * @return the idQuirofano
	 */
	public int getIdQuirofano() {
		return idQuirofano;
	}

	/**
	 * @param idQuirofano the idQuirofano to set
	 */
	public void setIdQuirofano(int idQuirofano) {
		this.idQuirofano = idQuirofano;
	}

	/**
	 * @return the localizacion
	 */
	public String getLocalizacion() {
		return localizacion;
	}

	/**
	 * @param localizacion the localizacion to set
	 */
	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the tipoQuirofano
	 */
	public String getTipoQuirofano() {
		return tipoQuirofano;
	}

	/**
	 * @param tipoQuirofano the tipoQuirofano to set
	 */
	public void setTipoQuirofano(String tipoQuirofano) {
		this.tipoQuirofano = tipoQuirofano;
	}

	/**
	 * @return the ocupado
	 */
	public boolean isOcupado() {
		return ocupado;
	}

	/**
	 * @param ocupado the ocupado to set
	 */
	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}

	/**
	 * @return the hospital
	 */
	public Hospital getHospital() {
		return hospital;
	}

	/**
	 * @param hospital the hospital to set
	 */
	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getNombre()+"";
	}
	
	
	
}
