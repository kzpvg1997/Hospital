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
import co.edu.ingesoft.hospital.persistencia.entidades.Persona;
import co.edu.ingesoft.hospital.persistencia.entidades.Rol;
import co.edu.ingesoft.hospital.persistencia.entidades.Usuario;

@LocalBean
@Stateless
public class PacienteEJB {

	@PersistenceContext
	private EntityManager em;
	
	@EJB
	private RolEJB rolEJB;
	
	@EJB
	private SeguridadEJB seguridadEJB;
	
	@EJB
	private PersonaEJB personaEJB;
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
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Paciente> listarPacietes(){
		Query q = em.createNamedQuery(Paciente.ListaPaciente);
		List<Paciente> paciente = q.getResultList();
		return paciente;
	}
	
	
	/**
	 * Metodo que sirve para buscar un  paciente
	 * @param cedula para buscar el paciente
	 * @return el paciente bscado
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
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
		Persona persona = personaEJB.buscarPersona(p.getIdentificacion());
		if(paciente == null){
			if(persona==null){
			Rol rol= rolEJB.buscarRol(1);
			p.setRol(rol);
			em.persist(p);
			}else{
				throw new ExcepcionNegocio("La persona con documento: "+p.getIdentificacion()+" ya esta en el sistema");
			}
			
		}else{
			throw new ExcepcionNegocio("Este paciente ya se encuentra registrado");
		}
		
	}
	
	/**
	 * Metodo para eliminar un paciente
	 * @param p paciente que recibe
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void borrarPaciente(Paciente p){
		Paciente pa = buscarPaciente(p.getIdentificacion());
		Persona pe = personaEJB.buscarPersona(p.getIdentificacion());
		if(pa != null){
			seguridadEJB.borrarUsuarioPersona(p);
			em.remove(pa);
			personaEJB.eliminarPersona(pe);
		}
		
	}
	
	/**
	 * Metodo para buscar una eps
	 * @param id que recibe el metodo
	 * @return la eps 
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Eps buscarEps(int id){
		return em.find(Eps.class,id);
	}
	
	/**
	 * Metodo para editar un paciente
	 * @param paciente parametro que recibe
	 */
	public void editarPaciente(Paciente paciente) {
		Rol rol= rolEJB.buscarRol(1);
		paciente.setRol(rol);
		em.merge(paciente); // Actualizar
	}
	
	
	
}
