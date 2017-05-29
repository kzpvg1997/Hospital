package controladores.Medico;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.pa.negocio.beans.HorariosDisponiblesEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.MedicoEJB;
import co.edu.ingesoft.hospital.persistencia.entidades.HorariosDisponibles;
import co.edu.ingesoft.hospital.persistencia.entidades.Medico;
import co.edu.ingesoft.hospital.persistencia.entidades.Usuario;
import session.SessionController;

@ViewScoped
@Named("horarioMedicoAjaxController")
public class HorarioMedicoAjaxController implements Serializable{
	
	@Inject
	private SessionController sesionCotroller;
	
	@EJB
	private HorariosDisponiblesEJB horarioEJB;
	
	@EJB
	private MedicoEJB medicoEJB;
	
	private int horaInicioSeleccionada;
	
	private int horaFinSeleccionada;
	
	private Date fechaHorario;
	
	private String fecha;
	
	
	@PostConstruct
	public void inicializar() {

		
	}
	
	
	public void registarHorario() throws ParseException{
		
		if(!(horaInicioSeleccionada==0) && !(horaFinSeleccionada==0) && !fecha.isEmpty() ){
			
			if(horaInicioSeleccionada!=horaFinSeleccionada){
				
				if(horaFinSeleccionada>horaInicioSeleccionada){
			
			Usuario u = sesionCotroller.getUsuario();
			Medico m = medicoEJB.buscarMedico(u.getPersona().getIdentificacion());
			
			HorariosDisponibles hd = new HorariosDisponibles();
			
			fechaHorario = new SimpleDateFormat("dd-MM-yyyy").parse(fecha);
			hd.setFecha(fechaHorario);
			hd.setHoraInicio(horaInicioSeleccionada);
			hd.setHoraFin(horaFinSeleccionada);
			hd.setMedico(m);
			
			horarioEJB.crearHorario(hd);
			
			Messages.addFlashGlobalInfo("Horario registrado");
			limpiar();
				}else{
					Messages.addFlashGlobalError("Hora invalida la hora fin es mayor a la hora inicio");
				}
			}else{
				Messages.addFlashGlobalWarn("Verifique que las horas no sean iguales");
			}
		}else{
			Messages.addFlashGlobalError("Verifique que los campos esten llenos");
		}
		
	}
	
	
	
	
	
	public void limpiar(){
		
		horaFinSeleccionada = 0;
		horaInicioSeleccionada = 0;
		fecha = "";
		
	}

	/**
	 * @return the horaInicioSeleccionada
	 */
	public int getHoraInicioSeleccionada() {
		return horaInicioSeleccionada;
	}


	/**
	 * @param horaInicioSeleccionada the horaInicioSeleccionada to set
	 */
	public void setHoraInicioSeleccionada(int horaInicioSeleccionada) {
		this.horaInicioSeleccionada = horaInicioSeleccionada;
	}


	/**
	 * @return the horaFinSeleccionada
	 */
	public int getHoraFinSeleccionada() {
		return horaFinSeleccionada;
	}


	/**
	 * @param horaFinSeleccionada the horaFinSeleccionada to set
	 */
	public void setHoraFinSeleccionada(int horaFinSeleccionada) {
		this.horaFinSeleccionada = horaFinSeleccionada;
	}


	/**
	 * @return the fechaHorario
	 */
	public Date getFechaHorario() {
		return fechaHorario;
	}


	/**
	 * @param fechaHorario the fechaHorario to set
	 */
	public void setFechaHorario(Date fechaHorario) {
		this.fechaHorario = fechaHorario;
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
	
	
	
	
	
	
	

}
