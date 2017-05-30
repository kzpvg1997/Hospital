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

import co.edu.ingesoft.hospital.persistencia.entidades.Cama;
import co.edu.ingesoft.hospital.persistencia.entidades.Cita;
import co.edu.ingesoft.hospital.persistencia.entidades.HorariosDisponibles;
import co.edu.ingesoft.hospital.persistencia.entidades.Hospitalizaciones;


@LocalBean
@Stateless
public class HospitalizacionesEJB {
	
	@EJB
	private QuirofanoEJB quirofanoEJB;
	
	@PersistenceContext
	private EntityManager em;

	
	/**
	 * Metodo para listar todas las camas disponibles
	 * @return una lista camas
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Cama> listarCamasDisponibles(){
		Query q = em.createNamedQuery(Cama.ListaCamasDisponibles);
		List<Cama> camas = q.getResultList();
		return camas;
	}
	
	/**
	 * MEtodo para buscar una cama
	 * @param id que recibe
	 * @return la cama buscada
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Hospitalizaciones buscarHospitalizacion(int id){
		return em.find(Hospitalizaciones.class,id);
	}
	
	/**
	 * MEtodo para registar camas
	 * @param c que recibe el metodo
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crearHospitalizacion(Hospitalizaciones h){
		Hospitalizaciones ho = buscarHospitalizacion(h.getId());
		em.persist(h);
		
	}
	
	/**
	 * MEtodo para edita una cama
	 * @param c cama que recibe
	 */
	public void editarCama(Cama c){
		Cama ca = quirofanoEJB.buscarCama(c.getNumeroCama());
		if(ca!=null){
			em.merge(c);
		}		
	}

	public Hospitalizaciones buscarPorCita(Cita cita){
		Query q = em.createNamedQuery(Hospitalizaciones.BUSCAR_POR_CITA);
		q.setParameter(1, cita);
		List<Hospitalizaciones> hosp = q.getResultList();
		if(hosp.isEmpty()){
			return null;
		}else{
			return hosp.get(0);
		}
	}
	
}
