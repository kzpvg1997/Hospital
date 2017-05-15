package session;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.pa.negocio.beans.MedicoEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.PacienteEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.SeguridadEJB;
import co.edu.ingesoft.hospital.persistencia.entidades.Paciente;
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

	private Paciente paciente;

	public String loginU() {

		Usuario usu = seguridadEJB.buscarUsuario(password);
		if (usu != null) {
			if (usu.getUsuario().equals(user)) {
				if (usu.getPassword().equals(password)) {

					Paciente p = pacienteEJB.buscarPaciente(usu.getPersona().getIdentificacion());

					if (p != null) {
						p.getRol().getIdRol();

						paciente = p;
						Faces.setSessionAttribute("user", paciente);
						return "/paginas/seguro/paciente.xhtml?faces-redirect=true";
					}
				} else {
					Messages.addFlashGlobalError("Datos incorrectos");
					System.out.println("Datos incorrectos");
				}
			} else {
				Messages.addFlashGlobalError("Datos incorrectos");
				System.out.println("Datos incorrectos");
			}
		} else {
			Messages.addFlashGlobalError("Datos incorrectos");
			System.out.println("Datos incorrectos");
		}
		return null;
	}

	public String cerrarSesion() {
		paciente = null;
		HttpSession sesion;
		sesion = (HttpSession) Faces.getSession();
		sesion.invalidate();
		return "/paginas/publico/login.xhtml?faces-redirect=true";
	}

	public boolean isSesion() {
		return cliente != null;
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
	
	
	
	
}
