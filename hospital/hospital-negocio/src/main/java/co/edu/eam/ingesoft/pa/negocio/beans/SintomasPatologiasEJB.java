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
import co.edu.ingesoft.hospital.persistencia.entidades.Especializaciones;
import co.edu.ingesoft.hospital.persistencia.entidades.Medico;
import co.edu.ingesoft.hospital.persistencia.entidades.MedicoEspecialista;
import co.edu.ingesoft.hospital.persistencia.entidades.MedicoEspecialistaPK;
import co.edu.ingesoft.hospital.persistencia.entidades.PatologiasDescritas;
import co.edu.ingesoft.hospital.persistencia.entidades.Sintoma;
import co.edu.ingesoft.hospital.persistencia.entidades.SintomasPatologias;
import co.edu.ingesoft.hospital.persistencia.entidades.SintomasPatologiasPK;
import co.edu.ingesoft.hospital.persistencia.entidades.Tratamiento;
import co.edu.ingesoft.hospital.persistencia.entidades.TratamientoSintoma;
import co.edu.ingesoft.hospital.persistencia.entidades.TratamientoSintomaPK;
import co.edu.ingesoft.hospital.persistencia.entidades.Usuario;

/**
 * @author TOSHIBAP55W
 *
 */

@LocalBean
@Stateless
public class SintomasPatologiasEJB {

	@PersistenceContext
	private EntityManager em;
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void asignarSintomaPatologia(SintomasPatologias sp) {

		PatologiasDescritas pat = buscarPatologia(sp.getPatologiaDescrita().getIdPatologia());
		if (pat != null) {
			Sintoma sin = buscarSintoma(sp.getSintoma().getIdSintoma());
			if (sin != null) {

				SintomasPatologias sp2 = buscarSintomasPatologias(sp.getSintoma().getIdSintoma(),
						sp.getPatologiaDescrita().getIdPatologia());

				if (sp2 == null) {
					sp.setPatologiaDescrita(pat);
					sp.setSintoma(sin);
					em.persist(sp);
				} else {
					throw new ExcepcionNegocio("Esta patologia ya tiene este sintoma asignado");
				}
			}
		}

	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public SintomasPatologias buscarSintomasPatologias(int sintoma, int patologia) {

		SintomasPatologiasPK sp = new SintomasPatologiasPK();
		sp.setPatologiaDescrita(patologia);
		sp.setSintoma(sintoma);
		
		return em.find(SintomasPatologias.class, sp);

	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void asignarTratamientoSintoma(TratamientoSintoma ts) {

		Tratamiento tra = buscarTratamiento(ts.getTratamiento().getIdTratamiento());
		if (tra != null) {
			Sintoma sin = buscarSintoma(ts.getSintoma().getIdSintoma());
			if (sin != null) {

				TratamientoSintoma ts2 = buscarTratamientoSintoma(ts.getSintoma().getIdSintoma(),
						ts.getTratamiento().getIdTratamiento());

				if (ts2 == null) {
					ts.setTratamiento(tra);
					ts.setSintoma(sin);
					em.persist(ts);
				} else {
					throw new ExcepcionNegocio("Esta patologia ya tiene este sintoma asignado");
				}
			}
		}

	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public TratamientoSintoma buscarTratamientoSintoma(int sintoma, int tratamiento) {

		TratamientoSintomaPK ts = new TratamientoSintomaPK();
		ts.setTratamiento(tratamiento);
		ts.setSintoma(sintoma);
		
		return em.find(TratamientoSintoma.class, ts);

	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void registrarPatologia(PatologiasDescritas patologia){
		PatologiasDescritas pat = buscarPatologiaXNombre(patologia.getNombrePatologia());
		if(pat==null){
			em.persist(patologia);
		}else{
			throw new ExcepcionNegocio("La patologia: ''"+patologia.getNombrePatologia()+"'' ya se encuentra registrada");
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public PatologiasDescritas buscarPatologiaXNombre(String nombrePatologia) {
		Query q = em.createNamedQuery(PatologiasDescritas.BUSCAR_PATOLOGIA_NOMBRE);
		q.setParameter(1, nombrePatologia);
		List<PatologiasDescritas> patos = q.getResultList();
		if (patos.isEmpty()) {
			return null;
		} else {
			return patos.get(0);
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void registrarSintomas(Sintoma sintoma){
		Sintoma sin = buscarSintomaXNombre(sintoma.getNombreSintoma());
		if(sin==null){
			em.persist(sintoma);
		}else{
			throw new ExcepcionNegocio("El sintoma: ''"+sintoma.getNombreSintoma()+"'' ya se encuentra registrado");
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Sintoma buscarSintomaXNombre(String nombreSintoma) {
		Query q = em.createNamedQuery(Sintoma.BUSCAR_SINTOMA_NOMBRE);
		q.setParameter(1, nombreSintoma);
		List<Sintoma> sinto = q.getResultList();
		if (sinto.isEmpty()) {
			return null;
		} else {
			return sinto.get(0);
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void registrarTratamiento(Tratamiento tratamiento){
		Tratamiento tra = buscarTratamientoXNombre(tratamiento.getNombreTratamiento());
		if(tra==null){
			em.persist(tratamiento);
		}else{
			throw new ExcepcionNegocio("El tratamiento: ''"+tratamiento.getNombreTratamiento()+"'' ya se encuentra registrado");
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Tratamiento buscarTratamientoXNombre(String nombreTratamiento) {
		Query q = em.createNamedQuery(Tratamiento.BUSCAR_TRATAMIENTO_NOMBRE);
		q.setParameter(1, nombreTratamiento);
		List<Tratamiento> trat = q.getResultList();
		if (trat.isEmpty()) {
			return null;
		} else {
			return trat.get(0);
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Sintoma buscarSintoma(int id){
		return em.find(Sintoma.class, id);
	}
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public PatologiasDescritas buscarPatologia(int id){
		return em.find(PatologiasDescritas.class, id);
	}
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Tratamiento buscarTratamiento(int id){
		return em.find(Tratamiento.class, id);
	}
	
}
