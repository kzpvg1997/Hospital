/**
 * 
 */
package controladores.Medico;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.swing.text.DefaultEditorKit.CutAction;

import org.omnifaces.cdi.ViewScoped;

import co.edu.eam.ingesoft.pa.negocio.beans.CitaEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.CitaExamenEJB;

/**
 * @author TOSHIBAP55W
 *
 */
@ViewScoped
@Named("ExamResultAjaxController")
public class ExamenesResultadoCitaAjaxController implements Serializable{

	@EJB
	private  CitaExamenEJB ciexEJB;
	
	/*DATOS CITA*/
	private String descripcionCita;
	private String nombreExamen;
	
	/*DATOS RESULTADO*/
	private String nombreResultado;
	private String descripcionResultado;
	private String citaExamen;
	private String fecha;
	private Date fechaResultado;
	
	
	

	
	
	/**
	 * @return the descripcionCita
	 */
	public String getDescripcionCita() {
		return descripcionCita;
	}
	/**
	 * @param descripcionCita the descripcionCita to set
	 */
	public void setDescripcionCita(String descripcionCita) {
		this.descripcionCita = descripcionCita;
	}
	/**
	 * @return the nombreExamen
	 */
	public String getNombreExamen() {
		return nombreExamen;
	}
	/**
	 * @param nombreExamen the nombreExamen to set
	 */
	public void setNombreExamen(String nombreExamen) {
		this.nombreExamen = nombreExamen;
	}
	/**
	 * @return the nombreResultado
	 */
	public String getNombreResultado() {
		return nombreResultado;
	}
	/**
	 * @param nombreResultado the nombreResultado to set
	 */
	public void setNombreResultado(String nombreResultado) {
		this.nombreResultado = nombreResultado;
	}
	/**
	 * @return the descripcionResultado
	 */
	public String getDescripcionResultado() {
		return descripcionResultado;
	}
	/**
	 * @param descripcionResultado the descripcionResultado to set
	 */
	public void setDescripcionResultado(String descripcionResultado) {
		this.descripcionResultado = descripcionResultado;
	}
	/**
	 * @return the citaExamen
	 */
	public String getCitaExamen() {
		return citaExamen;
	}
	/**
	 * @param citaExamen the citaExamen to set
	 */
	public void setCitaExamen(String citaExamen) {
		this.citaExamen = citaExamen;
	}
	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return the fechaResultado
	 */
	public Date getFechaResultado() {
		return fechaResultado;
	}
	/**
	 * @param fechaResultado the fechaResultado to set
	 */
	public void setFechaResultado(Date fechaResultado) {
		this.fechaResultado = fechaResultado;
	}
	
	
	
	
	
}
