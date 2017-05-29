package co.edu.eam.ingesoft.pa.negocio.beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.ingesoft.hospital.persistencia.entidades.Medicamento;

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
		em.persist(m);
		
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

}
