package controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import co.edu.eam.ingesoft.pa.negocio.beans.CitaEJB;
import co.edu.ingesoft.hospital.persistencia.entidades.Cita;

@ViewScoped
@Named("citaMedicoAjaxController")
public class CitaMedicoAjaxController implements Serializable{
	
	@EJB
	private CitaEJB citaEJB;
	
	public static Cita cita;
	
	private List<Cita> citas;
	
	private String  citaSeleccionada;
	
	
	@PostConstruct
	public void inicializar() {

		citas = citaEJB.listarCitas();
		
	}
	
	
	public String atender(Cita c){
		cita = c;
		return"/paginas/seguro/atenderCita.xhtml";
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

	
	
}
