package controladores;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.pa.negocio.beans.PacienteEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.PersonaEJB;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;
import co.edu.ingesoft.hospital.persistencia.entidades.Paciente;
import co.edu.ingesoft.hospital.persistencia.entidades.Usuario;

@ViewScoped
@Named("usuAjaxController")
public class UsuarioAjaxController implements Serializable {

	private String user;

	private String contraseña;

	private String verContraseña;

	private String cedula;

	@EJB
	private PersonaEJB personaEJB;

	@EJB
	private PacienteEJB pacienteEJB;

	@PostConstruct
	public void inicializar() {

	}

	public void registrarUsuario (){
		
		try{
		
			if(!user.isEmpty()&&!contraseña.isEmpty()&&!verContraseña.isEmpty()&&!cedula.isEmpty()){
			
				if(!verContraseña.equals(contraseña)){
					Messages.addFlashGlobalError("las contraseñas no coinciden");
				}else{
					
				Paciente pa = pacienteEJB.buscarPaciente(Integer.parseInt(cedula));
				if(pa!=null){
					
				Usuario usuario =  new Usuario();
				usuario.setUsuario(user);
				usuario.setPassword(contraseña);
				usuario.setPersona(pa);
				
				pacienteEJB.registrarUsuarioPaciente(usuario, pa);
				Messages.addFlashGlobalInfo("Se ha registrado exitosamente el usuario");
				limpiarCampos();
				
				}else{
					Messages.addFlashGlobalError("Este paciente no se encuentra registrado");
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
		contraseña="";
		verContraseña="";
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
	 * @return the contraseña
	 */
	public String getContraseña() {
		return contraseña;
	}

	/**
	 * @param contraseña
	 *            the contraseña to set
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
	 * @param verContraseña
	 *            the verContraseña to set
	 */
	public void setVerContraseña(String verContraseña) {
		this.verContraseña = verContraseña;
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
