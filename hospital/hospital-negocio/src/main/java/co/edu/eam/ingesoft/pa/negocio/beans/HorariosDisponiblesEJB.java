package co.edu.eam.ingesoft.pa.negocio.beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.ingesoft.hospital.persistencia.entidades.HorariosDisponibles;

@LocalBean
@Stateless
public class HorariosDisponiblesEJB {

	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Metodo para registar el horario disponible de un medico
	 * @param h parametro que recibe
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crearHorario(HorariosDisponibles h){
		em.persist(h);
		
	}
	
}
