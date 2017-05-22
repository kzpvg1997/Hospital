package co.edu.eam.ingesoft.pa.negocio.beans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;
import co.edu.ingesoft.hospital.persistencia.entidades.Persona;
import co.edu.ingesoft.hospital.persistencia.entidades.Usuario;

@LocalBean
@Stateless
public class SeguridadEJB implements Serializable {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Metoso que sirve para buscar un usuario
	 * 
	 * @param pass
	 *            parametro quer recibe
	 * @return el usuario
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Usuario buscarUsuario(String username) {
		Query q = em.createNamedQuery(Usuario.BUSCAR_USUARIO);
		q.setParameter(1, username);
		List<Usuario> usu = q.getResultList();
		if (usu.isEmpty()) {
			return null;
		} else {
			return usu.get(0);
		}
	}
	
	/**
	 * Metodo que sirve para buscar el usuario por persona
	 * @param persona
	 * @return el usuario
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Usuario buscarUsuarioPersona(Persona persona){
		Query q = em.createNamedQuery(Usuario.PERSONA_POR_USUARIO);
		q.setParameter(1, persona);
		List<Usuario> usu = q.getResultList();
		if (usu.isEmpty()) {
			return null;
		} else {
			//System.out.println("(((((((((((((==="+usu.get(0)+"===)))))))))))");
			return usu.get(0);
			
		}
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void borrarUsuarioPersona(Persona persona){
		Query q = em.createNamedQuery(Usuario.PERSONA_POR_USUARIO);
		q.setParameter(1, persona);
		List<Usuario> usu = q.getResultList();
		if (usu.isEmpty()) {
			
		} else {
			em.remove(usu.get(0));
			//System.out.println("(((((((((((((==="+usu.get(0).getUsuario()+"===)))))))))))");
		}
	}

}
