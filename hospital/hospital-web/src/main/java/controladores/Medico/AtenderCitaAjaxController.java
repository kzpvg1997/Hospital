package controladores.Medico;

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
import co.edu.eam.ingesoft.pa.negocio.beans.OrdenCirugiaEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.QuirofanoEJB;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;
import co.edu.ingesoft.hospital.persistencia.entidades.Cita;
import co.edu.ingesoft.hospital.persistencia.entidades.Medico;
import co.edu.ingesoft.hospital.persistencia.entidades.OrdenCirugia;
import co.edu.ingesoft.hospital.persistencia.entidades.Quirofano;
import session.SessionController;

@ViewScoped
@Named("atenderCitaAjaxController")
public class AtenderCitaAjaxController implements Serializable {

	@Inject
	private CitaMedicoAjaxController controladorCita;

	@EJB
	private CitaEJB citaEJB;
	
	@EJB
	private QuirofanoEJB quirofanoEJB;
	
	@Inject
	private SessionController sesionController;
	
	@EJB
	private MedicoEJB medicoEJB;
	
	@EJB
	private OrdenCirugiaEJB ordenCirugiaEJB;
	
//----------------------ATENDER CITA-----------------------
	private String tipoCita;

	private String detalle;

	private String horaCita;

	private String fecha;

	private String medico;

	private String anotacion;

	private String paciente;

	private String documento;
//-----------------QUIROFANO------------------------
	private String nombreCirugia;
	
	private String descripcion;
	
	private String procedimiento;
	
	private int quirofanoSeleccionado;
	
	private List<Quirofano> quirofanos;
	
	private String fechaQuirofano;
	
	private Date fechaQ;
	
//----------------------------------------------	
	
	

	public void aceptar(){
		
		if(!tipoCita.isEmpty()){
		
		if(!anotacion.isEmpty()){
		Cita c = new Cita();
		c = citaEJB.buscarCita(controladorCita.getCita().getIdCita());
		if(c !=  null){
			
			c.setAnotacion(anotacion);
			c.setAtendida(true);
			
			citaEJB.editarCita(c);
			Messages.addFlashGlobalInfo("La cita fue atendida correctamente");
			limpiar();
		}
		}else{
			Messages.addFlashGlobalWarn("Por favor ingrese anotacion de la cita");
		}
		}else{
			Messages.addFlashGlobalError("Para atender la cita debe elegir una cita en estado pendiente");
		}
	}
	

	@PostConstruct
	public void inicializar() {

		verCita();
		quirofanos = quirofanoEJB.listarQuirofanos();

	}

	public void verCita() {

		tipoCita = controladorCita.getCita().getTipoCita();
		detalle = controladorCita.getCita().getDescripcion();
		horaCita = controladorCita.getCita().getHoraCita();
		fecha = controladorCita.getCita().getFechaCita().toString();

		String nombreP = controladorCita.getCita().getPaciente().getNombre();
		String apellidoP = controladorCita.getCita().getPaciente().getApellido();
		paciente = nombreP + " " + apellidoP;

		String nombre = controladorCita.getCita().getMedico().getNombre();
		String apellido = controladorCita.getCita().getMedico().getApellido();

		medico = nombre+" "+apellido;

		documento =  String.valueOf(controladorCita.getCita().getPaciente().getIdentificacion());
		medico = nombre + " " + apellido;
		documento = String.valueOf(controladorCita.getCita().getPaciente().getIdentificacion());
		System.out.println(controladorCita.getCita().getIdCita());

	}

	
	
	
	public void limpiar(){
		
		tipoCita = "";
		detalle = "";
		horaCita = "";
		fecha = "";
		paciente = "";
		medico = "";
		anotacion = "";
		documento = "";
	}
	
	
	public void ordenCirugia(){
		
		try{
		
		if(!nombreCirugia.isEmpty() && !descripcion.isEmpty() && !procedimiento.isEmpty() && (quirofanoSeleccionado > 0) && !fechaQuirofano.isEmpty()){
			
			OrdenCirugia o = new OrdenCirugia();
			o.setNombreCirugia(nombreCirugia);
			o.setDescripcion(descripcion);
			o.setTipoProcedimiento(procedimiento);
			Quirofano q = quirofanoEJB.buscarQuirofano(quirofanoSeleccionado);
			System.out.println(q);
			o.setQuirofano(q);
			fechaQ = new SimpleDateFormat("dd-MM-yyyy").parse(fechaQuirofano);
			o.setFechaCirugia(fechaQ);
			Cita c = citaEJB.buscarCita(controladorCita.getCita().getIdCita());
			o.setCita(c);
			Medico m = medicoEJB.buscarMedico(sesionController.getUsuario().getPersona().getIdentificacion());
			o.setMedico(m);
			
			ordenCirugiaEJB.registrarOrdenCirugia(o);
			Messages.addFlashGlobalInfo("La orden fue registrada exitosamenteS");
			limpiarOrden();
		}else{
			Messages.addFlashGlobalError("Por favor ingrese todos los datos");
		}
		
		}catch(ExcepcionNegocio e){
			Messages.addFlashGlobalError(e.getMessage());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	public void limpiarOrden(){
		
		nombreCirugia = "";
		descripcion = "";
		procedimiento = "Seleccione";
		quirofanoSeleccionado = 0;
		fechaQuirofano = "";
	}
	
	
	
	


	/**
	 * @return the nombreCiruia
	 */
	public String getNombreCiruia() {
		return nombreCirugia;
	}


	/**
	 * @param nombreCiruia the nombreCiruia to set
	 */
	public void setNombreCiruia(String nombreCiruia) {
		this.nombreCirugia = nombreCiruia;
	}


	/**
	 * @return the procedimiento
	 */
	public String getProcedimiento() {
		return procedimiento;
	}


	/**
	 * @param procedimiento the procedimiento to set
	 */
	public void setProcedimiento(String procedimiento) {
		this.procedimiento = procedimiento;
	}


	/**
	 * @return the quirofanoSeleccionado
	 */
	public int getQuirofanoSeleccionado() {
		return quirofanoSeleccionado;
	}


	/**
	 * @param quirofanoSeleccionado the quirofanoSeleccionado to set
	 */
	public void setQuirofanoSeleccionado(int quirofanoSeleccionado) {
		this.quirofanoSeleccionado = quirofanoSeleccionado;
	}


	/**
	 * @return the quirofanos
	 */
	public List<Quirofano> getQuirofanos() {
		return quirofanos;
	}


	/**
	 * @param quirofanos the quirofanos to set
	 */
	public void setQuirofanos(List<Quirofano> quirofanos) {
		this.quirofanos = quirofanos;
	}


	/**
	 * @return the fechaQuirofano
	 */
	public String getFechaQuirofano() {
		return fechaQuirofano;
	}


	/**
	 * @param fechaQuirofano the fechaQuirofano to set
	 */
	public void setFechaQuirofano(String fechaQuirofano) {
		this.fechaQuirofano = fechaQuirofano;
	}


	/**
	 * @return the fechaQ
	 */
	public Date getFechaQ() {
		return fechaQ;
	}


	/**
	 * @param fechaQ the fechaQ to set
	 */
	public void setFechaQ(Date fechaQ) {
		this.fechaQ = fechaQ;
	}


	/**
	 * @return the documento
	 */
	public String getDocumento() {
		return documento;
	}

	/**
	 * @param documento
	 *            the documento to set
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
	 * @param tipoCita
	 *            the tipoCita to set
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
	 * @param detalle
	 *            the detalle to set
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
	 * @param fecha
	 *            the fecha to set
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
	 * @param medico
	 *            the medico to set
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
	 * @param paciente
	 *            the paciente to set
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
	 * @param horaCita
	 *            the horaCita to set
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
	 * @param anotacion
	 *            the anotacion to set
	 */
	public void setAnotacion(String anotacion) {
		this.anotacion = anotacion;
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
