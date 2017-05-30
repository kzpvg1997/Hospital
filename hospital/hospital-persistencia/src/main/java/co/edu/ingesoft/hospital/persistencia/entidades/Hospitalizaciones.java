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
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * @author TOSHIBAP55W
 *
 */
@Entity
@Table(name="Hospitalizaciones")
@NamedQueries({
	@NamedQuery(name=Hospitalizaciones.BUSCAR_POR_CITA,query="SELECT h FROM Hospitalizaciones h where h.cita=?1")
})
public class Hospitalizaciones implements Serializable{
	
	public static final String BUSCAR_POR_CITA ="Hospitalizaciones.busacarXCita";

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HOSPITALIZACIONES_SEQ")
    @SequenceGenerator(sequenceName = "hospitalizaciones_seq", allocationSize = 1, name = "HOSPITALIZACIONES_SEQ")
	private int id;
	
	@OneToOne
	@JoinColumn(name="Cama",unique=true)
	private Cama cama;
	
	
	@OneToOne
	@JoinColumn(name="Cita",unique=true)
	private Cita cita;
		
	@Column(name="descripcion",length=400)
	private String descripcion;
	
	@Column(name="descripcion",length=40)
	private String descripcion;
	
	public Hospitalizaciones(){
		
	}

	public Hospitalizaciones(int id, Cama cama, Cita cita, String descripcion) {
		super();
		this.id = id;
		this.cama = cama;
		this.cita = cita;
		this.descripcion = descripcion;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the cama
	 */
	public Cama getCama() {
		return cama;
	}

	/**
	 * @param cama the cama to set
	 */
	public void setCama(Cama cama) {
		this.cama = cama;
	}

	/**
	 * @return the cita
	 */
	public Cita getCita() {
		return cita;
	}

	/**
	 * @param cita the cita to set
	 */
	public void setCita(Cita cita) {
		this.cita = cita;
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
