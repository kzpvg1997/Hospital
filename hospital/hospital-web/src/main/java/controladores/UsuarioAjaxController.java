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
	
	private String contraseņa;
	
	private String verContraseņa;
	
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
	 * @return the contraseņa
	 */
	public String getContraseņa() {
		return contraseņa;
	}


	/**
	 * @param contraseņa the contraseņa to set
	 */
	public void setContraseņa(String contraseņa) {
		this.contraseņa = contraseņa;
	}


	/**
	 * @return the verContraseņa
	 */
	public String getVerContraseņa() {
		return verContraseņa;
	}


	/**
	 * @param verContraseņa the verContraseņa to set
	 */
	public void setVerContraseņa(String verContraseņa) {
		this.verContraseņa = verContraseņa;
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
