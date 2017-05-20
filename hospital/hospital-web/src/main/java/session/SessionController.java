package session;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.pa.negocio.beans.MedicoEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.PacienteEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.PersonaEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.SeguridadEJB;
import co.edu.ingesoft.hospital.persistencia.entidades.Paciente;
import co.edu.ingesoft.hospital.persistencia.entidades.Persona;
import co.edu.ingesoft.hospital.persistencia.entidades.Usuario;

@Named("sessionControl")
@SessionScoped
public class SessionController implements Serializable {

	private String password;

	private String user;

	@EJB
	private SeguridadEJB seguridadEJB;

	@EJB
	private MedicoEJB medicoEJB;

	@EJB
	private PacienteEJB pacienteEJB;

	@EJB
	private PersonaEJB personaEJB;
	
	private Paciente paciente;
	
	private Usuario usuario;
	
	

	public String loginU() {

		Usuario usu = seguridadEJB.buscarUsuario(user);
		if (usu != null) {
			
			usuario = usu;
			if(usu.getPassword().equals(password)){
				
				Persona persona = personaEJB.buscarPersona(usu.getPersona().getIdentificacion());
				
				if(persona.getRol().getIdRol() == 3){
					Faces.setSessionAttribute("user", usuario);
					System.out.println("Inicio sesion administrador");
					return "/paginas/seguro/administrador.xhtml?faces-redirect=true";
					
				}else if(persona.getRol().getIdRol() == 1){
					Faces.setSessionAttribute("user", usuario);
					System.out.println("Inicio sesion paciente");
					return "/paginas/seguro/paciente.xhtml?faces-redirect=true";
					
				}else if(persona.getRol().getIdRol() == 2){
					Faces.setSessionAttribute("user", usuario);
					System.out.println("Inicio sesion medico");
					return "/paginas/seguro/medico.xhtml?faces-redirect=true";
					
				}else if(persona.getRol().getIdRol() == 4){
					Faces.setSessionAttribute("user", usuario);
					System.out.println("Inicio sesion farmaceuta");
					return "/paginas/seguro/farmaceuta.xhtml?faces-redirect=true";
				}else{
					Messages.addFlashGlobalError("Datos incorrectos");
				}
			}else{
				Messages.addFlashGlobalError("Datos incorrectos");
			}
		}else{
			Messages.addFlashGlobalError("Datos incorrectos");
		}
		return null;
			
	}

	public String cerrarSesion() {
		usuario = null;
		HttpSession sesion;
		sesion = (HttpSession) Faces.getSession();
		sesion.invalidate();
		return "/paginas/publico/login.xhtml?faces-redirect=true";
	}

	public boolean isSesion() {
		return usuario != null;
	}
	

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * @return the paciente
	 */
	public Paciente getPaciente() {
		return paciente;
	}

	/**
	 * @param paciente the paciente to set
	 */
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
	
}
