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
import co.edu.ingesoft.hospital.persistencia.entidades.Medicamento;
import co.edu.ingesoft.hospital.persistencia.entidades.Usuario;

@LocalBean
@Stateless
public class MedicamentoEJB {
	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Metodo para registrar un medicamento
	 * @param m medicamento que recibe
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crearMedicamento(Medicamento m){
		Medicamento me = buscarMedicamento(m.getIdMedicamento());
		if(me==null){
			em.persist(m);
		}else{
			throw new ExcepcionNegocio("No se pudo regisrar el medicamento");
		}
	}
	
	/**
	 * Metodo para buscar un medicamento
	 * @param id parametrro que recibe
	 * @return el meicamento buscado
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Medicamento buscarMedicamento(int id){
		return em.find(Medicamento.class,id);
	}
	
	
	/**
	 * Metodo para editar un medicamento
	 * @param m medicamento que se va editar
	 */
	public void editarMedicamento(Medicamento m) {
		em.merge(m); // Actualizar
	}
	
	/**
	 * Metodo para eliminar un medicamento
	 * @param m medicamento que recibe
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void borrarMedicamento(Medicamento m){
		Medicamento me = buscarMedicamento(m.getIdMedicamento());
		if(me != null){
			em.remove(m);
		}
		
	}
	
	
	/**
	 * Metodo para buscar un medicamento por el nombre
	 * @param nombre para metro quee recibe para la busqueda
	 * @return el medicamento
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Medicamento buscarMedicamentoNomre(String nombre) {
		Query q = em.createNamedQuery(Medicamento.BUSCAR_MEDICAMENTO);
		q.setParameter(1, nombre);
		List<Medicamento> med = q.getResultList();
		if (med.isEmpty()) {
			return null;
		} else {
			return med.get(0);
		}
	}

}
