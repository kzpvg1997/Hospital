/**
 * 
 */
package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.ingesoft.hospital.persistencia.entidades.Eps;


/**
 * @author TOSHIBAP55W
 *
 */
@LocalBean
@Stateless
public class PacienteEJB {

	@PersistenceContext
	private EntityManager em;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Eps> listarEps(){
		Query q = em.createNamedQuery(Eps.LISTA_EPS);
		List<Eps> banks = q.getResultList();
		return banks;
	}
}
