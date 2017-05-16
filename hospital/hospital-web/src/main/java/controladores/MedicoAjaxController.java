package controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.pa.negocio.beans.MedicoEJB;
import co.edu.ingesoft.hospital.persistencia.entidades.Hospital;
import co.edu.ingesoft.hospital.persistencia.entidades.Medico;

import co.edu.eam.ingesoft.pa.negocio.beans.MedicoEJB;
import co.edu.ingesoft.hospital.persistencia.entidades.Hospital;

@ViewScoped
@Named("medicoAjaxController")
public class MedicoAjaxController implements Serializable {
	
	@EJB
	private MedicoEJB medicoEJB;
	
	@Pattern(regexp="[A-Za-z ]*",message="Ingrese solo letras")
	@Length(min=4,max=30,message="Lonitud entre 4 y 30")
	private String nombre;
	
	@Pattern(regexp="[A-Za-z ]*",message="Ingrese solo letras")
	@Length(min=4,max=30,message="Lonitud entre 4 y 30")
	private String apellido;
	
	@Pattern(regexp="[0-9]*",message="Ingrese solo numeros")
	@Length(min=3,max=10,message="Lonitud entre 3 y 10")
	private int numeroDocumento;
	
	private List<Hospital> listaHospitales;
		
	private String especialidadSeleccionda;
	
	private String hospitalSeleccionado;
	
	private List<Hospital> hospitales;
	
	private List<Medico> medicos;
	
	private String horario;
	
	private int busMedico;
	
	private boolean general;
	
	private boolean activo;
	
	private boolean noActivo;
	
	@PostConstruct
	public void inicializar(){
	listaHospitales = medicoEJB.listaHospitales();
		hospitales = medicoEJB.listarHospitales();
		medicos = medicoEJB.listarMedicos();
	}
	
	public void registrar(){
		
	}
	
	public void buscar(){
		
		Medico me = medicoEJB.buscarMedico(busMedico);
		if(me != null){
			
			nombre = me.getNombre();
			apellido = me.getApellido();
			numeroDocumento = me.getIdentificacion();
			especialidadSeleccionda = me.getTipoMedico();
			hospitalSeleccionado = me.getHospital().getNombre();			
	
		}else{
			Messages.addFlashGlobalInfo("El Medico no existe");
		}
		
		
	}
	

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * @return the numeroDocumento
	 */
	public int getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * @param numeroDocumento the numeroDocumento to set
	 */
	public void setNumeroDocumento(int numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * @return the especialidadSeleccionda
	 */
	public String getEspecialidadSeleccionda() {
		return especialidadSeleccionda;
	}

	/**
	 * @param especialidadSeleccionda the especialidadSeleccionda to set
	 */
	public void setEspecialidadSeleccionda(String especialidadSeleccionda) {
		this.especialidadSeleccionda = especialidadSeleccionda;
	}

	/**
	 * @return the hospitalSeleccionado
	 */
	public String getHospitalSeleccionado() {
		return hospitalSeleccionado;
	}

	/**
	 * @param hospitalSeleccionado the hospitalSeleccionado to set
	 */
	public void setHospitalSeleccionado(String hospitalSeleccionado) {
		this.hospitalSeleccionado = hospitalSeleccionado;
	}

	/**
	 * @return the horario
	 */
	public String getHorario() {
		return horario;
	}

	/**
	 * @param horario the horario to set
	 */
	public void setHorario(String horario) {
		this.horario = horario;
	}

	/**
	 * @return the hospitales
	 */
	public List<Hospital> getHospitales() {
		return hospitales;
	}

	/**
	 * @param hospitales the hospitales to set
	 */
	public void setHospitales(List<Hospital> hospitales) {
		this.hospitales = hospitales;
	}

	/**
	 * @return the busMedico
	 */
	public int getBusMedico() {
		return busMedico;
	}

	/**
	 * @param busMedico the busMedico to set
	 */
	public void setBusMedico(int busMedico) {
		this.busMedico = busMedico;
	}

	/**
	 * @return the general
//	 */
	public boolean isGeneral() {
		return general;
	}

	/**
	 * @param general the general to set
	 */
	public void setGeneral(boolean general) {
		this.general = general;
	}

	/**
	 * @return the medicoEJB
	 */
	public MedicoEJB getMedicoEJB() {
		return medicoEJB;
	}

	/**
	 * @param medicoEJB the medicoEJB to set
	 */
	public void setMedicoEJB(MedicoEJB medicoEJB) {
		this.medicoEJB = medicoEJB;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * @return the numeroDocumento
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * @param numeroDocumento the numeroDocumento to set
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * @return the listaHospitales
	 */
	public List<Hospital> getListaHospitales() {
		return listaHospitales;
	}

	/**
	 * @param listaHospitales the listaHospitales to set
	 */
	public void setListaHospitales(List<Hospital> listaHospitales) {
		this.listaHospitales = listaHospitales;
	}

	/**
	 * @return the especialidadSeleccionda
	 */
	public String getEspecialidadSeleccionda() {
		return especialidadSeleccionda;
	}

	/**
	 * @param especialidadSeleccionda the especialidadSeleccionda to set
	 */
	public void setEspecialidadSeleccionda(String especialidadSeleccionda) {
		this.especialidadSeleccionda = especialidadSeleccionda;
	}

	/**
	 * @return the hospitalSeleccionado
	 */
	public String getHospitalSeleccionado() {
		return hospitalSeleccionado;
	}

	/**
	 * @param hospitalSeleccionado the hospitalSeleccionado to set
	 */
	public void setHospitalSeleccionado(String hospitalSeleccionado) {
		this.hospitalSeleccionado = hospitalSeleccionado;
	}

	/**
	 * @return the horario
	 */
	public String getHorario() {
		return horario;
	}

	/**
	 * @param horario the horario to set
	 */
	public void setHorario(String horario) {
		this.horario = horario;
	}

	
	/**
	 * @return the activo
	 */
	public boolean isActivo() {
		return activo;
	}

	/**
	 * @param activo the activo to set
	 */
	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	/**
	 * @return the noActivo
	 */
	public boolean isNoActivo() {
		return noActivo;
	}

	/**
	 * @param noActivo the noActivo to set
	 */
	public void setNoActivo(boolean noActivo) {
		this.noActivo = noActivo;
	}

	/**
	 * @return the medicos
	 */
	public List<Medico> getMedicos() {
		return medicos;
	}

	/**
	 * @param medicos the medicos to set
	 */
	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}


	

}
