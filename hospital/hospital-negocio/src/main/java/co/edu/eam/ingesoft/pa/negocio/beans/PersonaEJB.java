/**
 * 
 */
package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;
import co.edu.ingesoft.hospital.persistencia.entidades.Paciente;
import co.edu.ingesoft.hospital.persistencia.entidades.Persona;
import co.edu.ingesoft.hospital.persistencia.entidades.Rol;
import co.edu.ingesoft.hospital.persistencia.entidades.Usuario;

/**
 * @author TOSHIBAP55W
 *
 */
@LocalBean
@Stateless
public class PersonaEJB {

	@PersistenceContext
	private EntityManager em;
	
	@EJB
	private SeguridadEJB seguridadEJB;
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Persona buscarPersonaPorUsuario(String username){
		Query q = em.createNamedQuery(Usuario.PERSONA_POR_USUARIO);
		q.setParameter(1, username);
		List<Persona> personas = q.getResultList();
		for (Persona persona : personas) {
			if(persona!=null){
				return personas.get(0);
			}
		}
		return null;
	}
	 
	

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Persona buscarPersona(int identificacion){
		return em.find(Persona.class, identificacion);
	}

	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarPersona(Persona persona){
		Persona p= buscarPersona(persona.getIdentificacion());
		if(p!=null){ 
			em.remove(persona);
		}
	
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void registrarUsuarioPersona(Usuario usuario,Persona persona){
		
		Usuario usu = seguridadEJB.buscarUsuarioPersona(persona);
		if(usu==null){
			boolean username = seguridadEJB.verificarUsername(usuario.getUsuario());
		if(username==true){	
		em.persist(usuario);
		}else{
			throw new ExcepcionNegocio("Este nombre de usuario (username) no esta disponible");
		}
		}else{
			throw new ExcepcionNegocio("Esta persona ya registro su usuario");
		}
	}
	
	
}
