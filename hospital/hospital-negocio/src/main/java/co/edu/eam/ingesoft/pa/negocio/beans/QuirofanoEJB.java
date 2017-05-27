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
import co.edu.ingesoft.hospital.persistencia.entidades.Cama;
import co.edu.ingesoft.hospital.persistencia.entidades.Hospital;
import co.edu.ingesoft.hospital.persistencia.entidades.Medico;
import co.edu.ingesoft.hospital.persistencia.entidades.Quirofano;
import co.edu.ingesoft.hospital.persistencia.entidades.Usuario;

/**
 * @author TOSHIBAP55W
 *
 */
@LocalBean
@Stateless
public class QuirofanoEJB {

	
	@PersistenceContext
	private EntityManager em;
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void registrarQuirofano(Quirofano quirofano){
	
		Quirofano qui = buscarQuirofano(quirofano.getIdQuirofano());
		if(qui==null){
			em.persist(quirofano);
		}else{
			throw new ExcepcionNegocio("Este numero de quirofano ya esta registrado");
		}
		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void registrarCama(Cama cama){
		Cama ca = buscarCama(cama.getNumeroCama());
		if(ca==null){
			em.persist(cama);
		}else{
			throw new ExcepcionNegocio("La cama con el numero: ''"+cama.getNumeroCama()+"'' ya se encuentra registrada");
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void editarQuirofano(Quirofano quirofano){
		Quirofano qui = buscarQuirofano(quirofano.getIdQuirofano());
		if(qui!=null){
		em.merge(quirofano);
		}else{
			throw new ExcepcionNegocio("Este quirofano (NO) esta registrado");
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Quirofano buscarQuirofano(int numero){
		return em.find(Quirofano.class, numero);
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Cama buscarCama(int numero){
		return em.find(Cama.class, numero);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Hospital buscarHospital(Usuario usuario) {
		Query q = em.createNamedQuery(Hospital.usuarioHospital);
		q.setParameter(1, usuario);
		List<Hospital> hosp = q.getResultList();
		if (hosp.isEmpty()) {
			return null;
		} else {
			return hosp.get(0);
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Cama> listaCamasHospital (Hospital hospital){
		Query q = em.createNamedQuery(Cama.ListaCamas);
		q.setParameter(1, hospital);
		List<Cama> camas = q.getResultList();
		return camas;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarCama (Cama cama){
		Cama cam = buscarCama(cama.getNumeroCama());
		if(cam!=null){
		em.remove(cam);
		}
	}
}
