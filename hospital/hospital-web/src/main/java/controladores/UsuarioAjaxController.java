package controladores;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.pa.negocio.beans.MedicoEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.PacienteEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.PersonaEJB;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;
import co.edu.ingesoft.hospital.persistencia.entidades.Paciente;
import co.edu.ingesoft.hospital.persistencia.entidades.Persona;
import co.edu.ingesoft.hospital.persistencia.entidades.Usuario;

@ViewScoped
@Named("usuAjaxController")
public class UsuarioAjaxController implements Serializable {

	private String user;

	private String contraseņa;

	private String verContraseņa;

	private String cedula;

	@EJB
	private PersonaEJB personaEJB;

	@EJB
	private PacienteEJB pacienteEJB;

	@EJB
	private MedicoEJB medicoEJB;
	
	@PostConstruct
	public void inicializar() {

	}

	public void registrarUsuario (){
		
		try{
		
			if(!user.isEmpty()&&!contraseņa.isEmpty()&&!verContraseņa.isEmpty()&&!cedula.isEmpty()){
			
				if(!verContraseņa.equals(contraseņa)){
					Messages.addFlashGlobalError("las contraseņas no coinciden");
				}else{
					
				Persona pe = personaEJB.buscarPersona(Integer.parseInt(cedula));
				if(pe!=null){
					
				Usuario usuario =  new Usuario();
				usuario.setUsuario(user);
				usuario.setPassword(contraseņa);
				usuario.setPersona(pe);
				
				personaEJB.registrarUsuarioPersona(usuario, pe);
				Messages.addFlashGlobalInfo("Se ha registrado exitosamente el usuario");
				limpiarCampos();
				
				}else{
					Messages.addFlashGlobalError("Esta persona no se encuentra registrada");
				}
				}
				
		}else{
			Messages.addFlashGlobalError("Por favor, ingrese todos los campos");
		
		}
		}catch (NumberFormatException ex){
			Messages.addFlashGlobalError("Por favor, solo campos numericos en el documento");
		}catch (ExcepcionNegocio e) {
			Messages.addFlashGlobalError(e.getMessage());
		}
		
	}
	
	public void limpiarCampos(){
		cedula="";
		user ="";
		contraseņa="";
		verContraseņa="";
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
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
	 * @param contraseņa
	 *            the contraseņa to set
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
	 * @param verContraseņa
	 *            the verContraseņa to set
	 */
	public void setVerContraseņa(String verContraseņa) {
		this.verContraseņa = verContraseņa;
	}

	/**
	 * @return the cedula
	 */
	public String getCedula() {
		return cedula;
	}

	/**
	 * @param cedula the cedula to set
	 */
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	
}
