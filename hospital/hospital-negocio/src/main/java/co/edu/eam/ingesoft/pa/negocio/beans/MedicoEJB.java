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

import co.edu.ingesoft.hospital.persistencia.entidades.Hospital;
import co.edu.ingesoft.hospital.persistencia.entidades.Medico;
import co.edu.ingesoft.hospital.persistencia.entidades.Paciente;

/**
 * @author GAR-T
 *
 */

@LocalBean
@Stateless
public class MedicoEJB {

	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Metodo para listar todos lo hospitales
	 * @return una lista con todos los hospitales
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Hospital> listarHospitales(){
		Query q = em.createNamedQuery(Hospital.ListaHospital);
		List<Hospital> hos = q.getResultList();
		return hos;
	}
	
	/**
	 * Metodo para listar los medicos
	 * @return una lista de medicos
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Medico> listarMedicos(){
		Query q = em.createNamedQuery(Medico.ListaMedico);
		List<Medico> me = q.getResultList();
		return me;
	}
	

	
	
	
	/**
	 * Metodo para buscar un medico
	 * @param cedula parametro que recibe
	 * @return el medico buscado
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Medico buscarMedico(int id){
		return em.find(Medico.class,id);
	}
}
