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

import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;
import co.edu.ingesoft.hospital.persistencia.entidades.Cita;
import co.edu.ingesoft.hospital.persistencia.entidades.CitaExamen;
import co.edu.ingesoft.hospital.persistencia.entidades.Examen;
import co.edu.ingesoft.hospital.persistencia.entidades.Hospital;
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
	public Examen buscarExamenNombre(String nombreExamen){
		Query q = em.createNamedQuery(Examen.EXAMEN_POR_NOMBRE);
		q.setParameter(1, nombreExamen);
		List<Examen> exam = q.getResultList();
		if (exam.isEmpty()) {
			return null;
		} else {
			return exam.get(0);
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public CitaExamen buscarCitaExamenXCitaExamen(Cita cita,Examen examen){
		Query q = em.createNamedQuery(CitaExamen.BUSCAR_CITAEXAMEN);
		q.setParameter(1, cita);
		q.setParameter(2, examen);
		List<CitaExamen> ce = q.getResultList();
		if (ce.isEmpty()) {
			return null;
		} else {
			return ce.get(0);
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
	
	public Resultados resultadoPorExamen (String nombreExamen){
		
		Query q = em.createNativeQuery("SELECT * FROM  RESULTADOS R JOIN CITAS_EXAMENES CE ON(R.CITAS_EXAMENES=CE.ID) JOIN EXAMENES E ON(CE.EXAMEN=E.ID_EXAMEN) WHERE E.NOMBRE_EXAMEN=?1");
		q.setParameter(1, nombreExamen);
		List<Resultados> res = q.getResultList();
		if (res.isEmpty()) {
			return null;
		} else {
			System.out.println("{{{{{{{{{"+res.get(0).getNombreResultado());
			return res.get(0);
		}
		

	}
}
