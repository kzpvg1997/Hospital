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

import co.edu.eam.ingesoft.pa.negocio.beans.CitaExamenEJB;

import co.edu.eam.ingesoft.pa.negocio.beans.MedicoEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.OrdenCirugiaEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.QuirofanoEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.SintomasPatologiasEJB;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;
import co.edu.ingesoft.hospital.persistencia.entidades.Cita;
import co.edu.ingesoft.hospital.persistencia.entidades.CitaExamen;
import co.edu.ingesoft.hospital.persistencia.entidades.CitasPatologias;
import co.edu.ingesoft.hospital.persistencia.entidades.Examen;
import co.edu.ingesoft.hospital.persistencia.entidades.Resultados;
import co.edu.ingesoft.hospital.persistencia.entidades.Sintoma;
import co.edu.ingesoft.hospital.persistencia.entidades.Tratamiento;
import co.edu.ingesoft.hospital.persistencia.entidades.Medico;
import co.edu.ingesoft.hospital.persistencia.entidades.OrdenCirugia;
import co.edu.ingesoft.hospital.persistencia.entidades.PatologiasDescritas;
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
	private CitaExamenEJB examenEJB;

	@EJB
	private QuirofanoEJB quirofanoEJB;
	
	@Inject
	private SessionController sesionController;
	
	@EJB
	private MedicoEJB medicoEJB;
	
	@EJB
	private OrdenCirugiaEJB ordenCirugiaEJB;
	
	@EJB
	private SintomasPatologiasEJB patoEJB;
	
//-------------------REGISTRAR SPATOLOGIAS---------------
	private int patologiaSeleccionada;
	private String descripcionPatologia;
	private List<PatologiasDescritas> listaPatologias;
	private int sintomaSeleccionado;
	private String descripcionSintoma;
	private List<Sintoma> listaSintomas;
	private int tratamientoSeleccionado;
	private String descripcionTratamiento;
	private List<Tratamiento> listaTratamientos;
	
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

	/*DATOS RESULTADO*/
	private String nombreResultado;
	private String descripcionResultado;
	private String valorFechaResulrado;
	private Date fechaResultado;
	
	/*DATOS EXAMEN CITA*/
	private String nombreExamen;
	private String descripcionExamen;
	
	
	private String descripcionQuirofano;
	
	private String procedimiento;
	
	private int quirofanoSeleccionado;
	
	private List<Quirofano> quirofanos;
	
	private String fechaQuirofano;
	
	private Date fechaQ;
	
	private String numOrden;
	
	private String busCirugia;
	
	
	@PostConstruct
	public void inicializar() {

		verCita();
		quirofanos = quirofanoEJB.listarQuirofanos();
		listaPatologias = patoEJB.listaPatologias();

	}
	
	public void sintomasByPatologia(){
		
		try{
			System.out.println("ENTRO A LA PATOLOGIA "+patologiaSeleccionada);
			PatologiasDescritas patologia = patoEJB.buscarPatologia(patologiaSeleccionada);
			if(patologia != null){
				descripcionPatologia = patologia.getDescripcion();
				listaSintomas = patoEJB.listaSintomasXPatologia(patologia);
				if(!listaSintomas.isEmpty()){
					Sintoma sintoma = listaSintomas.get(0);
					descripcionSintoma = sintoma.getDescripcion();
					tratamientosBySintoma();
				}else{
					listaTratamientos.clear();
				}
			}else{
				Messages.addFlashGlobalInfo("Seleccione una patologia");
			}
		}catch (ExcepcionNegocio e) {
			e.printStackTrace();
			Messages.addFlashGlobalError(e.getMessage());
		}
	}
	
	public void tratamientosBySintoma(){
		try{
			
			if(sintomaSeleccionado != 0){
				Sintoma sintoma = patoEJB.buscarSintoma(sintomaSeleccionado);
				listaTratamientos = patoEJB.listaTratamientoXSintoma(sintoma);
				Tratamiento tratamiento = listaTratamientos.get(0);
				descripcionTratamiento = tratamiento.getDescripcion();
			}
			
		}catch (ExcepcionNegocio e) {
			Messages.addFlashGlobalError(e.getMessage());
		}
	}
	
	public void asignarPatologia(){
		try{
			if(patologiaSeleccionada!=0){
				
				Cita cit = controladorCita.getCita();
				if(cit!=null){
				PatologiasDescritas pat = patoEJB.buscarPatologia(patologiaSeleccionada);
				if(pat!=null){
				CitasPatologias cipa = new CitasPatologias();
				cipa.setPatologiaDescrita(pat);
				cipa.setCita(cit);
				examenEJB.asignarPatologia(cipa);
				Messages.addFlashGlobalInfo("se ha asignado la patologia: "+pat.getNombrePatologia()+" Exitosamente!.");
				}
				}
			}else{
				Messages.addFlashGlobalError("Para asignar por favor seleccione patologia");
			}
			
		}catch (ExcepcionNegocio e) {
			Messages.addFlashGlobalError(e.getMessage());
		}
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
		
		if(!nombreCirugia.isEmpty() && !descripcionQuirofano.isEmpty() && !procedimiento.isEmpty() && (quirofanoSeleccionado > 0) && !fechaQuirofano.isEmpty()){
			
			OrdenCirugia o = new OrdenCirugia();
			o.setNumeroOrden(Integer.parseInt(numOrden));
			o.setNombreCirugia(nombreCirugia);
			o.setDescripcion(descripcionQuirofano);
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
			Messages.addFlashGlobalInfo("La orden fue registrada exitosamente");
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
	
	public void buscarOrdenC(){
		
		if(!busCirugia.isEmpty()){
			
			OrdenCirugia o = ordenCirugiaEJB.buscarOrden(Integer.parseInt(busCirugia));
			if(o!=null){
				
				nombreCirugia = o.getNombreCirugia();
				descripcionQuirofano = o.getDescripcion();
				procedimiento = o.getTipoProcedimiento();
				quirofanoSeleccionado = o.getQuirofano().getIdQuirofano();
				fechaQuirofano =o.getFechaCirugia()+"";
				numOrden = o.getNumeroOrden()+"";
				
			}else{
				Messages.addFlashGlobalError("Esta orden con numero "+busCirugia+" no existe");
			}
			
		}else{
			Messages.addFlashGlobalError("Para buscar ingrese el numero de orden");
		}
		
	}
	
	
	
	
	
	
	public void limpiarOrden(){
		
		nombreCirugia = "";
		descripcionQuirofano = "";
		procedimiento = "Seleccione";
		quirofanoSeleccionado = 0;
		fechaQuirofano = "";
		numOrden = "";
	}


	/**
	 * @return the controladorCita
	 */
	public CitaMedicoAjaxController getControladorCita() {
		return controladorCita;
	}


	/**
	 * @param controladorCita the controladorCita to set
	 */
	public void setControladorCita(CitaMedicoAjaxController controladorCita) {
		this.controladorCita = controladorCita;
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
	 * @return the busCirugia
	 */
	public String getBusCirugia() {
		return busCirugia;
	}


	/**
	 * @param busCirugia the busCirugia to set
	 */
	public void setBusCirugia(String busCirugia) {
		this.busCirugia = busCirugia;
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
	 * @return the descripcionQuirofano
	 */
	public String getDescripcionQuirofano() {
		return descripcionQuirofano;
	}


	/**
	 * @param descripcionQuirofano the descripcionQuirofano to set
	 */
	public void setDescripcionQuirofano(String descripcionQuirofano) {
		this.descripcionQuirofano = descripcionQuirofano;
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
	 * @return the numOrden
	 */
	public String getNumOrden() {
		return numOrden;
	}


	/**
	 * @param numOrden the numOrden to set
	 */
	public void setNumOrden(String numOrden) {
		this.numOrden = numOrden;
	}

	/**
	 * @return the descripcionPatologia
	 */
	public String getDescripcionPatologia() {
		return descripcionPatologia;
	}

	/**
	 * @param descripcionPatologia the descripcionPatologia to set
	 */
	public void setDescripcionPatologia(String descripcionPatologia) {
		this.descripcionPatologia = descripcionPatologia;
	}

	/**
	 * @return the listaPatologias
	 */
	public List<PatologiasDescritas> getListaPatologias() {
		return listaPatologias;
	}

	/**
	 * @param listaPatologias the listaPatologias to set
	 */
	public void setListaPatologias(List<PatologiasDescritas> listaPatologias) {
		this.listaPatologias = listaPatologias;
	}


	/**
	 * @return the descripcionSintoma
	 */
	public String getDescripcionSintoma() {
		return descripcionSintoma;
	}

	/**
	 * @param descripcionSintoma the descripcionSintoma to set
	 */
	public void setDescripcionSintoma(String descripcionSintoma) {
		this.descripcionSintoma = descripcionSintoma;
	}

	/**
	 * @return the listaSintomas
	 */
	public List<Sintoma> getListaSintomas() {
		return listaSintomas;
	}

	/**
	 * @param listaSintomas the listaSintomas to set
	 */
	public void setListaSintomas(List<Sintoma> listaSintomas) {
		this.listaSintomas = listaSintomas;
	}

	/**
	 * @return the descripcionTratamiento
	 */
	public String getDescripcionTratamiento() {
		return descripcionTratamiento;
	}

	/**
	 * @param descripcionTratamiento the descripcionTratamiento to set
	 */
	public void setDescripcionTratamiento(String descripcionTratamiento) {
		this.descripcionTratamiento = descripcionTratamiento;
	}

	/**
	 * @return the listaTratamientos
	 */
	public List<Tratamiento> getListaTratamientos() {
		return listaTratamientos;
	}

	/**
	 * @param listaTratamientos the listaTratamientos to set
	 */
	public void setListaTratamientos(List<Tratamiento> listaTratamientos) {
		this.listaTratamientos = listaTratamientos;
	}

	/**
	 * @return the patologiaSeleccionada
	 */
	public int getPatologiaSeleccionada() {
		return patologiaSeleccionada;
	}

	/**
	 * @param patologiaSeleccionada the patologiaSeleccionada to set
	 */
	public void setPatologiaSeleccionada(int patologiaSeleccionada) {
		this.patologiaSeleccionada = patologiaSeleccionada;
	}

	/**
	 * @return the sintomaSeleccionado
	 */
	public int getSintomaSeleccionado() {
		return sintomaSeleccionado;
	}

	/**
	 * @param sintomaSeleccionado the sintomaSeleccionado to set
	 */
	public void setSintomaSeleccionado(int sintomaSeleccionado) {
		this.sintomaSeleccionado = sintomaSeleccionado;
	}

	/**
	 * @return the tratamientoSeleccionado
	 */
	public int getTratamientoSeleccionado() {
		return tratamientoSeleccionado;
	}

	/**
	 * @param tratamientoSeleccionado the tratamientoSeleccionado to set
	 */
	public void setTratamientoSeleccionado(int tratamientoSeleccionado) {
		this.tratamientoSeleccionado = tratamientoSeleccionado;
	}
	

}
