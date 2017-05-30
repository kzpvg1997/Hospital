package co.edu.eam.ingesoft.pa.negocio.beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;
import co.edu.ingesoft.hospital.persistencia.entidades.Cama;
import co.edu.ingesoft.hospital.persistencia.entidades.OrdenCirugia;


@LocalBean
@Stateless
public class OrdenCirugiaEJB {
	
	@PersistenceContext
	private EntityManager em;
	
	
	/**
	 * Metodo apra buscar una ordenCirugia
	 * @param numero para mero que recibe
	 * @return la orden ciugia
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public OrdenCirugia buscarQuirofano(int numero){
		return em.find(OrdenCirugia.class, numero);
	}
	
	/**
	 * Metodo para registrar una orden
	 * @param o parametro que recibe
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void registrarOrdenCirugia(OrdenCirugia o){
		OrdenCirugia or = buscarQuirofano(o.getNumeroOrden());
		if(or==null){
			em.persist(o);
		}else{
			throw new ExcepcionNegocio("Esta orden ya se encuentra registrada");
		}
	}

}
