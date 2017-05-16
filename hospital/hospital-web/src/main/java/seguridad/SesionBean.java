/**
 * 
 */
package seguridad;

import javax.inject.Named;
import javax.faces.context.FacesContext;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.pa.negocio.beans.PerosnaEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.RolEJB;
import co.edu.ingesoft.hospital.persistencia.entidades.Accesos;
import co.edu.ingesoft.hospital.persistencia.entidades.Persona;
import co.edu.ingesoft.hospital.persistencia.entidades.Rol;
import co.edu.ingesoft.hospital.persistencia.entidades.Usuario;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

/**
 * @author TOSHIBAP55W
 *
 */
@Named
@SessionScoped
public class SesionBean implements Serializable {

	private Usuario usuario;

	private String username;

	private String pass;

	@EJB
	private PerosnaEJB personaEJB;

	@EJB
	private RolEJB rolEJB;

	private List<Accesos> accesos;

	private List<Rol> roles;

	public String login() {

//		Usuario usu = rolEJB.;
//		if (usu != null) {
//			if (usu.getUsuario().equals(nombre)) {
//				if (usu.getContraseña().equals(num)) {
//					Customer clienteTemp = customerEJB.buscarCustomer(usu.getCustomer().getIdType(),
//							usu.getCustomer().getIdNum());
//					if (clienteTemp != null) {
//						cliente = clienteTemp;
//						Faces.setSessionAttribute("user", cliente);
//						System.out.println("Inicio sesion");
//						return "/paginas/seguro/resumenproducto.xhtml?faces-redirect=true";
//						
//					}
//				} else {
//					Messages.addFlashGlobalError("Datos incorrectos");
//					System.out.println("Datos incorrectos");
//				}
//			} else {
//				Messages.addFlashGlobalError("Datos incorrectos");
//				System.out.println("Datos incorrectos");
//			}
//		} else {
//			Messages.addFlashGlobalError("Datos incorrectos");
//			System.out.println("Datos incorrectos");
//		}
		return null;
	}
	

	public boolean isLogged() {

		return usuario != null;
	}

	public String cerrarSesion() {
//		persona = null;
//		HttpSession sesion;
//		sesion = (HttpSession) Faces.getSession();
//		sesion.invalidate();
//		return "/paginas/publico/login.xhtml?faces-redirect=true";
		return null;
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


	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}


	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}


	/**
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}


	/**
	 * @param pass the pass to set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}


	/**
	 * @return the accesos
	 */
	public List<Accesos> getAccesos() {
		return accesos;
	}


	/**
	 * @param accesos the accesos to set
	 */
	public void setAccesos(List<Accesos> accesos) {
		this.accesos = accesos;
	}


	/**
	 * @return the roles
	 */
	public List<Rol> getRoles() {
		return roles;
	}


	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	

}
