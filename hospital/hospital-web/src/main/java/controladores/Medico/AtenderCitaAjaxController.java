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

import co.edu.eam.ingesoft.pa.negocio.beans.CitaEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.CitaExamenEJB;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;
import co.edu.ingesoft.hospital.persistencia.entidades.Cita;
import co.edu.ingesoft.hospital.persistencia.entidades.CitaExamen;
import co.edu.ingesoft.hospital.persistencia.entidades.Examen;
import co.edu.ingesoft.hospital.persistencia.entidades.Resultados;

@ViewScoped
@Named("atenderCitaAjaxController")
public class AtenderCitaAjaxController implements Serializable {

	@Inject
	private CitaMedicoAjaxController controladorCita;

	@EJB
	private CitaEJB citaEJB;
	
	@EJB
	private CitaExamenEJB examenEJB;

	private String tipoCita;

	private String detalle;

	private String horaCita;

	private String fecha;

	private String medico;

	private String anotacion;

	private String paciente;

	private String documento;
	
	/*DATOS RESULTADO*/
	private String nombreResultado;
	private String descripcionResultado;
	private String valorFechaResulrado;
	private Date fechaResultado;
	
	/*DATOS EXAMEN CITA*/
	private String nombreExamen;
	private String descripcionExamen;
	
	
	public void asignarRessultado(){
		try{
			if(!nombreExamen.isEmpty()){
				if(!nombreResultado.isEmpty()&&!descripcionResultado.isEmpty()&&!valorFechaResulrado.isEmpty()){
					
					Examen ex = examenEJB.buscarExamenNombre(nombreExamen);
					if(ex!=null){
					Cita cit = controladorCita.getCita();
					CitaExamen ce = examenEJB.buscarCitaExamenXCitaExamen(cit, ex);
					Resultados re = new Resultados();
					re.setNombreResultado(nombreResultado);
					re.setCitaExamen(ce);
					re.setDescripcion(descripcionResultado);
					fechaResultado = new SimpleDateFormat("dd-MM-yyyy").parse(valorFechaResulrado);
					re.setFechaResultado(fechaResultado);
					examenEJB.registrarResultado(re);
					Messages.addFlashGlobalInfo("Se ha asignado un resultado al examen: ''"+ex.getNombreExamen()+" Exitosamente");
					limpiarResultado();
					
					}else{
						Messages.addFlashGlobalError("el examen con nombre: "+nombreExamen+" no se encuentra registrado");
					}
					
				}else{
					Messages.addFlashGlobalWarn("Por favor ingrese datos del resultado");
				}
				
			}else{
				Messages.addFlashGlobalError("Para asignar el resultado busque el nombre del examen");
			}
			
		}catch (ExcepcionNegocio e) {
			Messages.addFlashGlobalError(e.getMessage());
		}catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void limpiarResultado(){
		nombreResultado= "";
		descripcionResultado="";
		valorFechaResulrado="";
	}
	
	
	public void buscarExamen(){
		try{
			if(!nombreExamen.isEmpty()){
				Examen exa = examenEJB.buscarExamenNombre(nombreExamen);
				if(exa!=null){
					descripcionExamen = exa.getDescripcion();					
				}else{
					Messages.addFlashGlobalError("El examen con nombre: "+nombreExamen+" NO se encuentra asignado");
				}	
			}else{
				Messages.addFlashGlobalWarn("Para buscar ingrese el nombre del examen");
			}
					
		}catch (ExcepcionNegocio e) {
			Messages.addFlashGlobalError(e.getMessage());
		}
		
	}
	
	
	public void registrarExamen (){
		try{
			if(!nombreExamen.isEmpty()&&!descripcionExamen.isEmpty()){
				
				Examen ex =new Examen();
				ex.setNombreExamen(nombreExamen);
				ex.setDescripcion(descripcionExamen);
				
				examenEJB.registrarExamen(ex);
				
				Examen exc = examenEJB.buscarExamenNombre(nombreExamen);
				CitaExamen ciex = new CitaExamen(controladorCita.getCita(), exc);
				examenEJB.registrarCitaExamen(ciex);
				
				Messages.addFlashGlobalInfo("Se ha asignado un examen exitosamente");
			}else{
				Messages.addFlashGlobalWarn("Por favor ingrese todos los datos de examen");
			}
			
		}catch (ExcepcionNegocio e) {
			Messages.addFlashGlobalError(e.getMessage());
		}
	}
	
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
	 * @return the descripcionExamen
	 */
	public String getDescripcionExamen() {
		return descripcionExamen;
	}


	/**
	 * @param descripcionExamen the descripcionExamen to set
	 */
	public void setDescripcionExamen(String descripcionExamen) {
		this.descripcionExamen = descripcionExamen;
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
	 * @return the valorFechaResulrado
	 */
	public String getValorFechaResulrado() {
		return valorFechaResulrado;
	}


	/**
	 * @param valorFechaResulrado the valorFechaResulrado to set
	 */
	public void setValorFechaResulrado(String valorFechaResulrado) {
		this.valorFechaResulrado = valorFechaResulrado;
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
