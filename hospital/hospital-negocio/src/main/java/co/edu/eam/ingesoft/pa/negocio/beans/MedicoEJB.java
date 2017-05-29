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
import co.edu.ingesoft.hospital.persistencia.entidades.Especializaciones;
import co.edu.ingesoft.hospital.persistencia.entidades.Hospital;
import co.edu.ingesoft.hospital.persistencia.entidades.Medico;
import co.edu.ingesoft.hospital.persistencia.entidades.MedicoEspecialista;
import co.edu.ingesoft.hospital.persistencia.entidades.MedicoEspecialistaPK;
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
	 * 
	 * @return una lista con todos los hospitales
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Hospital> listarHospitales() {
		Query q = em.createNamedQuery(Hospital.ListaHospital);
		List<Hospital> hos = q.getResultList();
		return hos;
	}

	/**
	 * Metodo para listar las especializaciones registradas en la base de datos
	 * 
	 * @return lista de especializaciones
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Especializaciones> listaEspecialidades() {
		Query q = em.createNamedQuery(Especializaciones.LISTA_ESPECIALIZACIONES);
		List<Especializaciones> esp = q.getResultList();
		return esp;
	}

	/**
	 * Metodo para listar los medicos
	 * 
	 * @return una lista de medicos
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Medico> listarMedicos() {
		Query q = em.createNamedQuery(Medico.ListaMedico);
		List<Medico> me = q.getResultList();
		return me;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void registrarMedico(Medico medico) {
		Medico med = buscarMedico(medico.getIdentificacion());
		Persona persona = personaEJB.buscarPersona(medico.getIdentificacion());
		if (med == null) {
			if (persona == null) {
				Rol rol = rolEJB.buscarRol(2); // Rol 2 Medico
				medico.setRol(rol);
				em.persist(medico);
			} else {
				throw new ExcepcionNegocio(
						"La persona con documento: " + medico.getIdentificacion() + " ya esta en el sistema");
			}
		} else {
			throw new ExcepcionNegocio(
					"El medico con documento: " + medico.getIdentificacion() + " ya se encuentra registrado");
		}
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Hospital buscarHospital(int id) {
		return em.find(Hospital.class, id);
	}

	/**
	 * Metodo para buscar un medico
	 * 
	 * @param cedula
	 *            parametro que recibe
	 * @return el medico buscado
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Medico buscarMedico(int id) {
		return em.find(Medico.class, id);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarMedico(Medico medico) {
		
		Medico me = buscarMedico(medico.getIdentificacion());
		Persona pe = personaEJB.buscarPersona(medico.getIdentificacion());
		if (me != null) {

			seguridadEJB.borrarUsuarioPersona(medico);
			em.remove(me);
			personaEJB.eliminarPersona(pe);
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<MedicoEspecialista> especializacionesMedico(Medico medico) {

		Query q = em.createNamedQuery(Especializaciones.ESPECIALIZACIONES_MEDICO);
		q.setParameter(1, medico);
		List<MedicoEspecialista> especializaciones = q.getResultList();
		return especializaciones;

	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Especializaciones buscarEspecialidad(int id) {
		return em.find(Especializaciones.class, id);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void asignarEspecialidad(MedicoEspecialista me) {

		Medico med = buscarMedico(me.getMedico().getIdentificacion());
		if (med != null) {
			Especializaciones esp = buscarEspecialidad(me.getEspecializacion().getIdEspecializacion());
			if (esp != null) {

				MedicoEspecialista me2 = buscarMedicoEspecialista(me.getMedico().getIdentificacion(),
						me.getEspecializacion().getIdEspecializacion());

				if (me2 == null) {
					me.setEspecializacion(esp);
					me.setMedico(med);
					em.persist(me);
				} else {
					throw new ExcepcionNegocio("El medico ya tiene esta especialidad asignada");
				}
			}
		}

	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public MedicoEspecialista buscarMedicoEspecialista(int medico, int especialidad) {

		MedicoEspecialistaPK me = new MedicoEspecialistaPK();
		me.setEspecializacion(especialidad);
		me.setMedico(medico);

		return em.find(MedicoEspecialista.class, me);

	}
	

	
	/**
	 * Metodo para editar un medico
	 * @param medico parametro que recibe
	 */
	public void editarMedico(Medico medico) {
		Rol rol= rolEJB.buscarRol(2);
		medico.setRol(rol);
		em.merge(medico); // Actualizar
	}
}
