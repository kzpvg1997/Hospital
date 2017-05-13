/**
 * 
 */
package co.edu.eam.ingesoft.pa.negocio.beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author GAR-T
 *
 */

@LocalBean
@Stateless
public class MedicoEJB {

	@PersistenceContext
	private EntityManager em;
}
