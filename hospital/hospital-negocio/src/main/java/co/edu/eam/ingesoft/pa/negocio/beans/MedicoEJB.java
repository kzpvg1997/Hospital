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

import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;
import co.edu.ingesoft.hospital.persistencia.entidades.Hospital;
import co.edu.ingesoft.hospital.persistencia.entidades.Medico;
import co.edu.ingesoft.hospital.persistencia.entidades.Paciente;
import co.edu.ingesoft.hospital.persistencia.entidades.Persona;
import co.edu.ingesoft.hospital.persistencia.entidades.Rol;

/**
 * @author GAR-T
 *
 */

@LocalBean
@Stateless
public class MedicoEJB {

	@PersistenceContext
	private EntityManager em;
	
	@EJB
	private RolEJB rolEJB;
	
	@EJB
	private PersonaEJB personaEJB;
	
	@EJB
	private SeguridadEJB seguridadEJB;
	
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
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void registrarMedico(Medico medico){
		Medico med = buscarMedico(medico.getIdentificacion());
		Persona persona = personaEJB.buscarPersona(medico.getIdentificacion());
		if(med==null){
			if(persona==null){
			Rol rol= rolEJB.buscarRol(2); //Rol 2 Medico
			medico.setRol(rol);
			em.persist(medico);
			}else{
				throw new ExcepcionNegocio("La persona con documento: "+medico.getIdentificacion()+" ya esta en el sistema");
			}
		}else{
			throw new ExcepcionNegocio("El medico con documento: "+medico.getIdentificacion()+" ya se encuentra registrado");
		}
	}
	
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Hospital buscarHospital(int id){
		return em.find(Hospital.class,id);
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
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarMedico (Medico medico){
		Medico me = buscarMedico(medico.getIdentificacion());
		Persona pe = personaEJB.buscarPersona(medico.getIdentificacion());
		if(me != null){
			System.out.println(pe);
			System.out.println(me);

			//seguridadEJB.borrarUsuarioPersona(medico);
			em.remove(me);
			personaEJB.eliminarPersona(pe);
		}
	}
}
