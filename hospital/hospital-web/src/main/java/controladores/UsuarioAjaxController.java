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

	private String contrase�a;

	private String verContrase�a;

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
		
			if(!user.isEmpty()&&!contrase�a.isEmpty()&&!verContrase�a.isEmpty()&&!cedula.isEmpty()){
			
				if(!verContrase�a.equals(contrase�a)){
					Messages.addFlashGlobalError("las contrase�as no coinciden");
				}else{
					
				Paciente pa = pacienteEJB.buscarPaciente(Integer.parseInt(cedula));
				if(pa!=null){
					
				Usuario usuario =  new Usuario();
				usuario.setUsuario(user);
				usuario.setPassword(contrase�a);
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
		contrase�a="";
		verContrase�a="";
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
	 * @return the contrase�a
	 */
	public String getContrase�a() {
		return contrase�a;
	}

	/**
	 * @param contrase�a
	 *            the contrase�a to set
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
	 * @param verContrase�a
	 *            the verContrase�a to set
	 */
	public void setVerContrase�a(String verContrase�a) {
		this.verContrase�a = verContrase�a;
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
