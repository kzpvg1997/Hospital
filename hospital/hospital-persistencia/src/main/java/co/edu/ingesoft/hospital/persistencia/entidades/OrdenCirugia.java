/**
 * 
 */
package co.edu.ingesoft.hospital.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * @author TOSHIBAP55W
 *
 */
@Entity
@Table(name="Orden_cirugia")
public class OrdenCirugia implements Serializable {

	@Id
	@Column(name="numero_orden")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDENCIRUGIA_SEQ")
    @SequenceGenerator(sequenceName = "ordencirugia_seq", allocationSize = 1, name = "ORDENCIRUGIA_SEQ")
	private int numeroOrden;
	
	@Column(name="nombre_cirugia",length=40)
	private String nombreCirugia;
	
	@Column(name="descripcion",length=400)
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name="Cita")
	private Cita cita;
	
	@ManyToOne
	@JoinColumn(name="quirofano")
	private Quirofano quirofano;
	
	@Column(name="fecha_cirugia")
	@Temporal(TemporalType.DATE)
	private Date fechaCirugia;
	
	@ManyToOne
	@JoinColumn(name="Medico")
	private Medico medico;
	
	@Column(name="Tipo_procedimiento",length=40)
	private String tipoProcedimiento;
	
	public OrdenCirugia(){
		
	}

	/**
	 * @return the numeroOrden
	 */
	public int getNumeroOrden() {
		return numeroOrden;
	}

	/**
	 * @param numeroOrden the numeroOrden to set
	 */
	public void setNumeroOrden(int numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	/**
	 * @return the nombreCirugia
	 */
	public String getNombreCirugia() {
		return nombreCirugia;
	}

	/**
	 * @param nombreCirugia the nombreCirugia to set
	 */
	public void setNombreCirugia(String nombreCirugia) {
		this.nombreCirugia = nombreCirugia;
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
	 * @return the medico
	 */
	public Medico getMedico() {
		return medico;
	}

	/**
	 * @param medico the medico to set
	 */
	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	/**
	 * @return the tipoProcedimiento
	 */
	public String getTipoProcedimiento() {
		return tipoProcedimiento;
	}

	/**
	 * @param tipoProcedimiento the tipoProcedimiento to set
	 */
	public void setTipoProcedimiento(String tipoProcedimiento) {
		this.tipoProcedimiento = tipoProcedimiento;
	}

	/**
	 * @return the quirofano
	 */
	public Quirofano getQuirofano() {
		return quirofano;
	}

	/**
	 * @param quirofano the quirofano to set
	 */
	public void setQuirofano(Quirofano quirofano) {
		this.quirofano = quirofano;
	}

	/**
	 * @return the fechaCirugia
	 */
	public Date getFechaCirugia() {
		return fechaCirugia;
	}

	/**
	 * @param fechaCirugia the fechaCirugia to set
	 */
	public void setFechaCirugia(Date fechaCirugia) {
		this.fechaCirugia = fechaCirugia;
	}
	
	
	
	
	
	
}
