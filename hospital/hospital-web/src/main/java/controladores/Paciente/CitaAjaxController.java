package controladores.Paciente;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.pa.negocio.beans.CitaEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.MedicoEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.PacienteEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.PersonaEJB;
import co.edu.ingesoft.hospital.persistencia.entidades.Cita;
import co.edu.ingesoft.hospital.persistencia.entidades.HorariosDisponibles;
import co.edu.ingesoft.hospital.persistencia.entidades.Medico;
import co.edu.ingesoft.hospital.persistencia.entidades.Paciente;
import co.edu.ingesoft.hospital.persistencia.entidades.Persona;
import co.edu.ingesoft.hospital.persistencia.entidades.Usuario;
import session.SessionController;

@ViewScoped
@Named("citaAjaxController")
public class CitaAjaxController implements Serializable {

	@Inject
	private SessionController sesionCotroller;

	@EJB
	private CitaEJB citaEJB;

	@EJB
	private MedicoEJB medicoEJB;

	@EJB
	private PersonaEJB personaEJB;

	private PacienteEJB pacienteEJB;

	private String tipoCita;

	private int horaInicioSeleccionado;

	private int horaFinSeleccionada;

	private List<HorariosDisponibles> horarios;

	private List<Medico> medicos;

	private int medicoSeleccionado;

	private String detalle;

	private Date fechaCita;

	private String fecha;

	@PostConstruct
	public void inicializar() {

		horarios = citaEJB.listarHorarios();
		medicos = medicoEJB.listarMedicos();

	}

	public void pedirCita() throws ParseException {
		System.out.println("ENTROOOOOOOOOOOO");
		 if(tipoCita=="Seleccione" || !fecha.isEmpty() || detalle.isEmpty() || (horaInicioSeleccionado==0) || (medicoSeleccionado == 0) || (horaFinSeleccionada==0)){
			 
			 Usuario p = sesionCotroller.getUsuario();
				Persona pe = personaEJB.buscarPersona(p.getPersona().getIdentificacion());
				Paciente pa = (Paciente)pe;			
					
					Medico m = medicoEJB.buscarMedico(medicoSeleccionado);
					System.out.println(m.getNombre());
					Cita c = new Cita();
				
					c.setTipoCita(tipoCita);
					c.setDescripcion(detalle);
					c.setHoraCita(horaInicioSeleccionado+" - "+horaFinSeleccionada);
					c.setAnotacion("Pendiente");
					c.setAtendida(false);
					fechaCita = new SimpleDateFormat("dd-MM-yyyy").parse(fecha);
					c.setFechaCita(fechaCita);
					c.setMedico(m);
					c.setPaciente(pa);
					c.setIdCita(null);
					
					if(c.getFechaCita() != null){
						citaEJB.crearCita(c);
						limpiarCita();
						Messages.addFlashGlobalInfo("La cita se ha asignado correctamente al medico: "+m.getNombre()+".");
					}else{
						Messages.addFlashGlobalError("Verifique que la fecha este correcta");
					}			 
		 }else{
			 Messages.addFlashGlobalError("Ingrese todos los datos");
		 }

	}

	public void limpiarCita() {

		tipoCita = "Seleccione";
		detalle = "";
		horaInicioSeleccionado = 0;
		horaFinSeleccionada = 0;
		fecha = "";
		medicoSeleccionado = 0;

	}

	/**
	 * @return the sesionCotroller
	 */
	public SessionController getSesionCotroller() {
		return sesionCotroller;
	}

	/**
	 * @param sesionCotroller
	 *            the sesionCotroller to set
	 */
	public void setSesionCotroller(SessionController sesionCotroller) {
		this.sesionCotroller = sesionCotroller;
	}

	/**
	 * @return the tipoCita
	 */
	public String getTipoCita() {
		return tipoCita;
	}

	/**
	 * @param tipoCita
	 *            the tipoCita to set
	 */
	public void setTipoCita(String tipoCita) {
		this.tipoCita = tipoCita;
	}

	/**
	 * @return the horaInicioSeleccionado
	 */
	public int getHoraInicioSeleccionado() {
		return horaInicioSeleccionado;
	}

	/**
	 * @param horaInicioSeleccionado
	 *            the horaInicioSeleccionado to set
	 */
	public void setHoraInicioSeleccionado(int horaInicioSeleccionado) {
		this.horaInicioSeleccionado = horaInicioSeleccionado;
	}

	/**
	 * @return the horaFinSeleccionada
	 */
	public int getHoraFinSeleccionada() {
		return horaFinSeleccionada;
	}

	/**
	 * @param horaFinSeleccionada
	 *            the horaFinSeleccionada to set
	 */
	public void setHoraFinSeleccionada(int horaFinSeleccionada) {
		this.horaFinSeleccionada = horaFinSeleccionada;
	}

	/**
	 * @return the horarios
	 */
	public List<HorariosDisponibles> getHorarios() {
		return horarios;
	}

	/**
	 * @param horarios
	 *            the horarios to set
	 */
	public void setHorarios(List<HorariosDisponibles> horarios) {
		this.horarios = horarios;
	}

	/**
	 * @return the medicos
	 */
	public List<Medico> getMedicos() {
		return medicos;
	}

	/**
	 * @param medicos
	 *            the medicos to set
	 */
	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}

	/**
	 * @return the medicoSeleccionado
	 */
	public int getMedicoSeleccionado() {
		return medicoSeleccionado;
	}

	/**
	 * @param medicoSeleccionado
	 *            the medicoSeleccionado to set
	 */
	public void setMedicoSeleccionado(int medicoSeleccionado) {
		this.medicoSeleccionado = medicoSeleccionado;
	}

	/**
	 * @return the detalle
	 */
	public String getDetalle() {
		return detalle;
	}

	/**
	 * @param detalle
	 *            the detalle to set
	 */
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	/**
	 * @return the fechaCita
	 */
	public Date getFechaCita() {
		return fechaCita;
	}

	/**
	 * @param fechaCita
	 *            the fechaCita to set
	 */
	public void setFechaCita(Date fechaCita) {
		this.fechaCita = fechaCita;
	}

	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

}
