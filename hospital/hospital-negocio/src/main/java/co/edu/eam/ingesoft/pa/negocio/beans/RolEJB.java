/**
 * 
 */
package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.ingesoft.hospital.persistencia.entidades.Accesos;
import co.edu.ingesoft.hospital.persistencia.entidades.Persona;
import co.edu.ingesoft.hospital.persistencia.entidades.Rol;


/**
 * @author TOSHIBAP55W
 *
 */
@LocalBean
@Stateless
public class RolEJB {

	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Metodo que sirve para listar las roles
	 * @return las roles
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Rol> listarRoles(){
		Query q = em.createNamedQuery(Rol.LISTA_ROLES);
		List<Rol> roles = q.getResultList();
		return roles;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Rol> ListaRolesPersona(int identificacion){
		Query q = em.createNamedQuery(Persona.ROLES_PERSONA);
		q.setParameter(1, identificacion);
		List<Rol> rolesPersona = q.getResultList();
		return rolesPersona;
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Rol buscarRol (int id){
		return em.find(Rol.class, id);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Accesos> ListaAccesosRol(Rol rol){
		Query q = em.createNamedQuery(Accesos.LISTA_ACCESOS_ROL);
		q.setParameter(1, rol);
		List<Accesos> accesosRoles = q.getResultList();
		return accesosRoles;
	}
	
	public int prueba (){
		
		Query q = em.createNativeQuery("SELECT R.ID FROM ROLES R WHERE R.NOMBRE='ADMINISTRADOR'");
		List<Integer> valores = q.getResultList();
		
		System.out.println("((((((((((((((("+valores.get(0)+")))))))))))");
		return valores.get(0);
		

	}
}
