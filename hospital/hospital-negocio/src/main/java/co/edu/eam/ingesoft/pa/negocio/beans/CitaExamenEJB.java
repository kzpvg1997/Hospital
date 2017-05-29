/**
 * 
 */
package co.edu.eam.ingesoft.pa.negocio.beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;
import co.edu.ingesoft.hospital.persistencia.entidades.CitaExamen;
import co.edu.ingesoft.hospital.persistencia.entidades.Examen;
import co.edu.ingesoft.hospital.persistencia.entidades.Resultados;

/**
 * @author TOSHIBAP55W
 *
 */

@LocalBean
@Stateless
public class CitaExamenEJB {

	@PersistenceContext
	private EntityManager em;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void registrarExamen(Examen examen){
		Examen ex = buscarExamen(examen.getIdExamen());
		if(ex==null){
			em.persist(examen);
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void registrarCitaExamen(CitaExamen citaExamen){
		CitaExamen ciex = buscarCitaExamen(citaExamen.getId());
		if(ciex==null){
			em.persist(citaExamen);
		}
		
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public CitaExamen buscarCitaExamen(int id){
		return em.find(CitaExamen.class,id );
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void registrarResultado(Resultados resultado){
		Resultados re = buscarResultado(resultado.getId());
		if(re==null){
			em.persist(resultado);
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Examen buscarExamen(int id){
		return em.find(Examen.class, id);
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Resultados buscarResultado(int id){
		return em.find(Resultados.class, id);
	}
}
