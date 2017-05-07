package controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.omnifaces.cdi.ViewScoped;

import co.edu.eam.ingesoft.pa.negocio.beans.PacienteEJB;
import co.edu.ingesoft.hospital.persistencia.entidades.Eps;
import co.edu.ingesoft.hospital.persistencia.entidades.Paciente;
import co.edu.ingesoft.hospital.persistencia.enumeraciones.GeneroEnum;


@ViewScoped
@Named("pacienteAjaxController")
public class PacienteAjaxController implements Serializable {
	
	@EJB
	private	PacienteEJB pacienteEJB;
	
	@Pattern(regexp="[A-Za-z ]*",message="Ingrese solo letras")
	@Length(min=4,max=30,message="Lonitud entre 4 y 30")
	private String nombre;
	
	@Pattern(regexp="[A-Za-z ]*",message="Ingrese solo letras")
	@Length(min=4,max=30,message="Lonitud entre 4 y 30")
	private String apellido;
	
	@Pattern(regexp="[0-9]*",message="Ingrese solo numeros")
	@Length(min=3,max=10,message="Lonitud entre 3 y 10")
	private int numeroDocumento;
	
	private String generoSeleccionado;
	
	private String epsSeleccionada;
	
	private List<Eps> listaEps;
	
	private GeneroEnum[] listaGeneros;
	
	private String fecha;
	
	@Pattern(regexp="[0-9]*",message="Ingrese solo numeros")
	@Length(min=10,max=10,message="Lonitud entre 3 y 10")
	private String telefono;
	
	@Pattern(regexp="[A-Za-z ]*",message="Ingrese solo letras")
	@Length(min=4,max=30,message="Lonitud entre 4 y 30")
	private String email;
	
	@PostConstruct
	public void inicializar(){
	
		listarCombos();
	}
	
	public void registrar(){
		
	}
	
	public void buscar(){
		
		Paciente pa = pacienteEJB.buscarPaciente(numeroDocumento);
		if(pa != null){
			nombre = pa.getNombre();
			apellido = pa.getApellido();
			numeroDocumento = pa.getIdentificacion();
			//generoSeleccionado = pa.getGenero();
			epsSeleccionada = pa.getEps().getNombre();
			fecha = pa.getFechaNacimiento().toString();
			telefono = pa.getTelefono();
			email = pa.getEmail();
			
		}else{
			
		}
		
		
		
	}
	
	public void listarCombos(){
		listaEps = pacienteEJB.listarEps();
		
	}

	public GeneroEnum[] getGeneros(){
		return GeneroEnum.values();
	}


	/**
	 * @return the listaGeneros
	 */
	public GeneroEnum[] getListaGeneros() {
		return listaGeneros;
	}

	/**
	 * @param listaGeneros the listaGeneros to set
	 */
	public void setListaGeneros(GeneroEnum[] listaGeneros) {
		this.listaGeneros = listaGeneros;
	}

	/**
	 * @return the pacienteEJB
	 */
	public PacienteEJB getPacienteEJB() {
		return pacienteEJB;
	}

	/**
	 * @param pacienteEJB the pacienteEJB to set
	 */
	public void setPacienteEJB(PacienteEJB pacienteEJB) {
		this.pacienteEJB = pacienteEJB;
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
	 * @return the generoSeleccionado
	 */
	public String getGeneroSeleccionado() {
		return generoSeleccionado;
	}

	/**
	 * @param generoSeleccionado the generoSeleccionado to set
	 */
	public void setGeneroSeleccionado(String generoSeleccionado) {
		this.generoSeleccionado = generoSeleccionado;
	}

	/**
	 * @return the epsSeleccionada
	 */
	public String getEpsSeleccionada() {
		return epsSeleccionada;
	}

	/**
	 * @param epsSeleccionada the epsSeleccionada to set
	 */
	public void setEpsSeleccionada(String epsSeleccionada) {
		this.epsSeleccionada = epsSeleccionada;
	}

	/**
	 * @return the listaEps
	 */
	public List<Eps> getListaEps() {
		return listaEps;
	}

	/**
	 * @param listaEps the listaEps to set
	 */
	public void setListaEps(List<Eps> listaEps) {
		this.listaEps = listaEps;
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
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	

}
