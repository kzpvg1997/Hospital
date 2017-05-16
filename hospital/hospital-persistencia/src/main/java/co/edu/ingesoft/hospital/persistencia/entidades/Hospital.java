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
@Table(name="Hospitales")
@NamedQueries({
	@NamedQuery(name=Hospital.LISTA_HOSPITALES,query="SELECT h FROM Hospital h")
})
@NamedQueries({
	@NamedQuery(name=Hospital.ListaHospital,query="SELECT h FROM Hospital h")
})
public class Hospital implements Serializable{
	
	public static final String LISTA_HOSPITALES = "Hospital.listaHospitales";

	public static final String ListaHospital = "Hospital.listarHospitales";
	
	@Id
	@Column(name="id_hospital")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HOSPITAL_SEQ")
    @SequenceGenerator(sequenceName = "hospital_seq", allocationSize = 1, name = "HOSPITAL_SEQ")
	private int idHospital;
	
	@Column(name="nombre",length=40)
	private String nombre;
	
	@OneToOne
	@JoinColumn(name="Usuario",unique=true)
	private Usuario usuario;
	
	public Hospital(){
		
	}
	
	/**
	 * @param idHospital
	 * @param nombre
	 * @param usuario
	 */
	public Hospital(int idHospital, String nombre, Usuario usuario) {
		super();
		this.idHospital = idHospital;
		this.nombre = nombre;
		this.usuario = usuario;
	}



	/**
	 * @return the idHospital
	 */
	public int getIdHospital() {
		return idHospital;
	}

	/**
	 * @param idHospital the idHospital to set
	 */
	public void setIdHospital(int idHospital) {
		this.idHospital = idHospital;
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
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
