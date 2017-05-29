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
import co.edu.ingesoft.hospital.persistencia.entidades.HorariosDisponibles;
import co.edu.ingesoft.hospital.persistencia.entidades.Paciente;



@LocalBean
@Stateless
public class CitaEJB {

	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Metodo para listar los horarios 
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<HorariosDisponibles> listarHorarios(){
		Query q = em.createNamedQuery(HorariosDisponibles.ListaHorarios);
		List<HorariosDisponibles> horarios = q.getResultList();
		return horarios;
	}
	
	/**
	 * Metodo para listar todas las citas NO atendidas
	 * @return una lista de citas
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Cita> listarCitas(){
		Query q = em.createNamedQuery(Cita.LISTA_CITA_NO_ATENDIDAS);
		List<Cita> citas = q.getResultList();
		return citas;
	}
	
	/**
	 * Metodo apra buscar la cita
	 * @param id parametro que recibe
	 * @return la cita
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Cita buscarCita(int id){
		return em.find(Cita.class,id);
	}
	
	/**
	 * Metodo que permite registar una cita de un paciente
	 * @param c la cita que recibe
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crearCita(Cita c){
		em.persist(c);
		
	}
	
	/**
	 * Metodo que sirve para listar las citas por medico
	 * @param m identificacion 	
	 * @return la lista de citas
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Cita> ListaCitasPorMedico(int m){
		Query q = em.createNamedQuery(Cita.LISTA_CITA_NO_ATENDIDAS);
		q.setParameter(1, m);
		List<Cita> citas = q.getResultList();
		return citas;
	}
	
	
	/**
	 * Metodo para editar una cita 
	 * @param c la cita que se va atender
	 */
	public void editarCita(Cita c){
		em.merge(c);
	}


	
	
	
}
