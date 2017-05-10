/**
 * 
 */
package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;
import co.edu.ingesoft.hospital.persistencia.entidades.Eps;
import co.edu.ingesoft.hospital.persistencia.entidades.Paciente;
import co.edu.ingesoft.hospital.persistencia.entidades.Rol;


/**
 * @author TOSHIBAP55W
 *
 */
@LocalBean
@Stateless
public class PacienteEJB {

	@PersistenceContext
	private EntityManager em;
	
	@EJB
	private RolEJB rolEJB;
	
	/**
	 * Metodo que sirve para listar las eps
	 * @return las eps
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Eps> listarEps(){
		Query q = em.createNamedQuery(Eps.LISTA_EPS);
		List<Eps> eps = q.getResultList();
		return eps;
	}
	
	
	/**
	 * Metodo que sirve para buscar un  paciente
	 * @param cedula para buscar el paciente
	 * @return el paciente bscado
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Paciente buscarPaciente(int cedula){
		return em.find(Paciente.class,cedula);
	}
	
	/**
	 * Metodo que sirve para crear pacientes
	 * @param p paciente que recibe 
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crearPaciente(Paciente p){
		Paciente paciente = buscarPaciente(p.getIdentificacion());
		if(paciente == null){
			
			Rol rol= rolEJB.buscarRol(1);
			p.setRol(rol);
			
			em.persist(p);
			
		}else{
			throw new ExcepcionNegocio("Este paciente ya se encuentra registrado");
		}
		
	}
	
	
}
