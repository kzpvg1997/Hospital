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
	
	private String contrase�a;
	
	private String verContrase�a;
	
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
	 * @return the contrase�a
	 */
	public String getContrase�a() {
		return contrase�a;
	}


	/**
	 * @param contrase�a the contrase�a to set
	 */
	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}


	/**
	 * @return the verContrase�a
	 */
	public String getVerContrase�a() {
		return verContrase�a;
	}


	/**
	 * @param verContrase�a the verContrase�a to set
	 */
	public void setVerContrase�a(String verContrase�a) {
		this.verContrase�a = verContrase�a;
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
