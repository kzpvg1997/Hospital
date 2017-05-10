package controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.pa.negocio.beans.PacienteEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.RolEJB;
import co.edu.ingesoft.hospital.persistencia.entidades.Eps;
import co.edu.ingesoft.hospital.persistencia.entidades.Paciente;
import co.edu.ingesoft.hospital.persistencia.entidades.Persona;


@ViewScoped
@Named("pacienteAjaxController")
public class PacienteAjaxController implements Serializable {
	
	@EJB
	private	PacienteEJB pacienteEJB;
	
	@EJB
	private RolEJB rolEJB;
	
	
	private String nombre;
	
	
	private String apellido;
	
	
	private int numeroDocumento;
	
	private String generoSeleccionado;
	
	private String epsSeleccionada;
	
	private List<Eps> listaEps;
	
	private String fecha;
	
	private String telefono;

	private String email;
	
	@PostConstruct
	public void inicializar(){
	
		listarCombos();
		
	}
	
	public void registrar(){
		
	}
	
	public void buscar(){
		System.out.println("holaaaaaaa");
		

//		Paciente pa = pacienteEJB.buscarPaciente(numeroDocumento);
//		if(pa != null){
//			nombre = pa.getNombre();
//			apellido = pa.getApellido();
//			numeroDocumento = pa.getIdentificacion();
//		//	generoSeleccionado = pa.getGenero();
//			
//		}else{
//			
//		}
//		
		//Paciente pa = pacienteEJB.buscarPaciente(numeroDocumento);
		Persona pa = pacienteEJB.buscarPersona(numeroDocumento);
		if(pa != null){
			System.out.println("si");
			nombre = pa.getNombre();
			apellido = pa.getApellido();
			numeroDocumento = pa.getIdentificacion();
			///generoSeleccionado = pa.;
		//	epsSeleccionada = pa.getEps().getNombre();
			//fecha = pa.getFechaNacimiento().toString();
			telefono = pa.getTelefono();
		//	email = pa.getEmail();
			
			System.out.println(pa.getNombre());
			System.out.println(pa.getApellido());
			System.out.println(pa.getIdentificacion());
		//	System.out.println(pa.getGenero());
			//System.out.println(pa.getEps().getNombre());
			//System.out.println(pa.getFechaNacimiento().toString());
			System.out.println(pa.getTelefono());
			//System.out.println(pa.getEmail());
													
		}else{
			Messages.addFlashGlobalWarn("El paciente no existe");
			System.out.println("no");
		}
		
		
		
	}
	
	public void listarCombos(){
		listaEps = pacienteEJB.listarEps();
		
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
