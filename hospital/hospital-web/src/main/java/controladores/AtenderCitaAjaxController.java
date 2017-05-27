package controladores;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import co.edu.eam.ingesoft.pa.negocio.beans.CitaEJB;

@ViewScoped
@Named("atenderCitaAjaxController")
public class AtenderCitaAjaxController implements Serializable {
	
	@Inject
	private CitaMedicoAjaxController controladorCita;
	
	@EJB
	private CitaEJB citaEJB;
	
	private String tipoCita;
	
	private String detalle;
	
	private String horaCita;
	
	private String fecha;
	
	private String medico;
	
	private String anotacion;
	
	private String paciente;
	
	private String documento;
	
	
	@PostConstruct
	public void inicializar() {

		verCita();
		
	}
	
	public void verCita(){
		
		tipoCita = controladorCita.getCita().getTipoCita();
		detalle = controladorCita.getCita().getDescripcion();
		horaCita = controladorCita.getCita().getHoraCita();
		fecha = controladorCita.getCita().getFechaCita().toString();
		
		String nombreP = controladorCita.getCita().getPaciente().getNombre();
		String apellidoP = controladorCita.getCita().getPaciente().getApellido();
		paciente = nombreP+" "+apellidoP;
		
		String nombre = controladorCita.getCita().getMedico().getNombre();
		String apellido = controladorCita.getCita().getMedico().getApellido();
		medico = nombre+" "+apellido;
		
		anotacion = "";
		documento =  String.valueOf(controladorCita.getCita().getPaciente().getIdentificacion());

	}
	
	public void atendida(){
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	/**
	 * @return the documento
	 */
	public String getDocumento() {
		return documento;
	}

	/**
	 * @param documento the documento to set
	 */
	public void setDocumento(String documento) {
		this.documento = documento;
	}

	/**
	 * @return the tipoCita
	 */
	public String getTipoCita() {
		return tipoCita;
	}

	/**
	 * @param tipoCita the tipoCita to set
	 */
	public void setTipoCita(String tipoCita) {
		this.tipoCita = tipoCita;
	}

	/**
	 * @return the detalle
	 */
	public String getDetalle() {
		return detalle;
	}

	/**
	 * @param detalle the detalle to set
	 */
	public void setDetalle(String detalle) {
		this.detalle = detalle;
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
	 * @return the medico
	 */
	public String getMedico() {
		return medico;
	}

	/**
	 * @param medico the medico to set
	 */
	public void setMedico(String medico) {
		this.medico = medico;
	}

	/**
	 * @return the paciente
	 */
	public String getPaciente() {
		return paciente;
	}

	/**
	 * @param paciente the paciente to set
	 */
	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}

	/**
	 * @return the horaCita
	 */
	public String getHoraCita() {
		return horaCita;
	}

	/**
	 * @param horaCita the horaCita to set
	 */
	public void setHoraCita(String horaCita) {
		this.horaCita = horaCita;
	}

	/**
	 * @return the anotacion
	 */
	public String getAnotacion() {
		return anotacion;
	}

	/**
	 * @param anotacion the anotacion to set
	 */
	public void setAnotacion(String anotacion) {
		this.anotacion = anotacion;
	}
	
	
	
	
	
	

	
	
	
	
	
	
	
	

}