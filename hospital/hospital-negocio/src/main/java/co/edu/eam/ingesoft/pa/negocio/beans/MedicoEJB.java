package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.ingesoft.hospital.persistencia.entidades.Hospital;

/**
 * 
 * @author TOSHIBAP55W
 *
 */
@LocalBean
@Stateless
public class MedicoEJB {

	@PersistenceContext
	private EntityManager em;
	
	
	public List<Hospital> listaHospitales(){
		Query q = em.createNamedQuery(Hospital.LISTA_HOSPITALES);
		List<Hospital> hospitales = q.getResultList();
		return hospitales;
	}
	
}
