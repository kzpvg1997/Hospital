/**
 * 
 */
package co.edu.ingesoft.hospital.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author TOSHIBAP55W
 *
 */
@Entity
@Table(name="Tratamientos")
@NamedQueries({
	@NamedQuery(name=Tratamiento.BUSCAR_TRATAMIENTO_NOMBRE,query="SELECT t FROM Tratamiento t WHERE t.nombreTratamiento=?1"),
	@NamedQuery(name=Tratamiento.TRATAMIENTO_SINTOMA,query="SELECT ts.tratamiento FROM TratamientoSintoma ts WHERE ts.sintoma=?1")
})
public class Tratamiento implements Serializable{

	public static final String BUSCAR_TRATAMIENTO_NOMBRE = "Tratamiento.BuscarXNombre";
	
	public static final String TRATAMIENTO_SINTOMA = "TratamientoXsintoma";
	
	@Id
	@Column(name="id_tratamiento")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRATAMENTO_SEQ")
    @SequenceGenerator(sequenceName = "tratamiento_seq", allocationSize = 1, name = "TRATAMENTO_SEQ")
	private int idTratamiento;
	
	@Column(name="nombre",length=40)
	private String nombreTratamiento;
	
	@Column(name="descripcion",length=200)
	private String descripcion;
	
	public Tratamiento(){
		
	}

	/**
	 * @param nombreTratamiento
	 * @param descripcion
	 */
	public Tratamiento(String nombreTratamiento, String descripcion) {
		super();
		this.nombreTratamiento = nombreTratamiento;
		this.descripcion = descripcion;
	}

	/**
	 * @return the idTratamiento
	 */
	public int getIdTratamiento() {
		return idTratamiento;
	}

	/**
	 * @param idTratamiento the idTratamiento to set
	 */
	public void setIdTratamiento(int idTratamiento) {
		this.idTratamiento = idTratamiento;
	}

	/**
	 * @return the nombreTratamiento
	 */
	public String getNombreTratamiento() {
		return nombreTratamiento;
	}

	/**
	 * @param nombreTratamiento the nombreTratamiento to set
	 */
	public void setNombreTratamiento(String nombreTratamiento) {
		this.nombreTratamiento = nombreTratamiento;
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
	
	
}
