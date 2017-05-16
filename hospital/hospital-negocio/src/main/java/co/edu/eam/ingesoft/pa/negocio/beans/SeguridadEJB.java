package co.edu.eam.ingesoft.pa.negocio.beans;

import java.io.Serializable;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.ingesoft.hospital.persistencia.entidades.Usuario;



@LocalBean
@Stateless
public class SeguridadEJB implements Serializable{
	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Metoso que sirve para buscar un usuario
	 * @param pass parametro quer recibe
	 * @return el usuario
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Usuario buscarUsuario(String pass){
		return em.find(Usuario.class, pass);
	}

}
