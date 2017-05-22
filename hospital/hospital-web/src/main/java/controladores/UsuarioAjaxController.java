package controladores;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import org.omnifaces.cdi.ViewScoped;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.pa.negocio.beans.PacienteEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.PersonaEJB;
import co.edu.ingesoft.hospital.persistencia.entidades.Paciente;

@ViewScoped
@Named("usuAjaxController")
public class UsuarioAjaxController implements Serializable {

	private String user;
	
	private String contraseña;
	
	private String verContraseña;
	
	private int cedula;

	@EJB
	private PersonaEJB personaEJB;
	
	@EJB
	private PacienteEJB pacienteEJB;
	
	
	@PostConstruct
	public void inicializar(){
	
		
	}
	
	
	public void buscar(){
		try{
		System.out.println("ENtro");
		Paciente pa = pacienteEJB.buscarPaciente(cedula);
		System.out.println("++++++++++++++++ "+cedula);
		if(pa!=null){
			System.out.println("Existe");
			Messages.addFlashGlobalError("SI");
		}else{
			System.out.println("No Existe");
			Messages.addFlashGlobalError("NO");
		}
		}catch (NumberFormatException ex){
			Messages.addFlashGlobalError("Por favor, solo campos numericos en el documento");
		}
		
		
	}


	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}


	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}


	/**
	 * @return the contraseña
	 */
	public String getContraseña() {
		return contraseña;
	}


	/**
	 * @param contraseña the contraseña to set
	 */
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}


	/**
	 * @return the verContraseña
	 */
	public String getVerContraseña() {
		return verContraseña;
	}


	/**
	 * @param verContraseña the verContraseña to set
	 */
	public void setVerContraseña(String verContraseña) {
		this.verContraseña = verContraseña;
	}


	/**
	 * @return the cedula
	 */
	public int getCedula() {
		return cedula;
	}


	/**
	 * @param cedula the cedula to set
	 */
	public void setCedula(int cedula) {
		this.cedula = cedula;
	}
	
	

}
