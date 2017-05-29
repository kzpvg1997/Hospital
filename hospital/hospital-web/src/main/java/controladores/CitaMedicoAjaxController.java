package controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import co.edu.eam.ingesoft.pa.negocio.beans.CitaEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.MedicoEJB;
import co.edu.ingesoft.hospital.persistencia.entidades.Cita;
import co.edu.ingesoft.hospital.persistencia.entidades.Medico;
import co.edu.ingesoft.hospital.persistencia.entidades.Persona;
import co.edu.ingesoft.hospital.persistencia.entidades.Usuario;
import session.SessionController;

@ViewScoped
@Named("citaMedicoAjaxController")
public class CitaMedicoAjaxController implements Serializable{
	
	@Inject
	private SessionController sesionController;
	
	@EJB
	private MedicoEJB medicoEJB;
	
	@EJB
	private CitaEJB citaEJB;
	
	public static Cita cita;
	
	private List<Cita> citas;
	
	private String  citaSeleccionada;
	
	
	@PostConstruct
	public void inicializar() {

		Usuario u = sesionController.getUsuario();
		Persona m = medicoEJB.buscarMedico(u.getPersona().getIdentificacion());
	
		citas = citaEJB.ListaCitasPorMedico(u.getPersona().getIdentificacion());
		
	}
	
	
	public String atender(Cita c){
		cita = c;
		return"/paginas/seguro/medico/atenderCita.xhtml";
	}
	

	
	
	
	


	/**
	 * @return the citas
	 */
	public List<Cita> getCitas() {
		return citas;
	}


	/**
	 * @param citas the citas to set
	 */
	public void setCitas(List<Cita> citas) {
		this.citas = citas;
	}


	/**
	 * @return the citaSeleccionada
	 */
	public String getCitaSeleccionada() {
		return citaSeleccionada;
	}


	/**
	 * @param citaSeleccionada the citaSeleccionada to set
	 */
	public void setCitaSeleccionada(String citaSeleccionada) {
		this.citaSeleccionada = citaSeleccionada;
	}


	/**
	 * @return the cita
	 */
	public static Cita getCita() {
		return cita;
	}


	/**
	 * @param cita the cita to set
	 */
	public static void setCita(Cita cita) {
		CitaMedicoAjaxController.cita = cita;
	}

	
	
}
