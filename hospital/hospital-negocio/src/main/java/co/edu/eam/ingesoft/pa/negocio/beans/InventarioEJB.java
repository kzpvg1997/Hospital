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
import co.edu.ingesoft.hospital.persistencia.entidades.Farmacia;
import co.edu.ingesoft.hospital.persistencia.entidades.Inventario;
import co.edu.ingesoft.hospital.persistencia.entidades.InventarioPK;
import co.edu.ingesoft.hospital.persistencia.entidades.Medicamento;
import co.edu.ingesoft.hospital.persistencia.entidades.Usuario;

@LocalBean
@Stateless
public class InventarioEJB {

	@PersistenceContext
	private EntityManager em;

	@EJB
	private MedicamentoEJB medicamentoEJB;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void asignarInventario(Inventario i) {
		Farmacia farmacia = buscarFarmacia(i.getFarmacia().getIdFarmacia());
		if (farmacia != null) {
			Medicamento medicamento = medicamentoEJB.buscarMedicamento(i.getMedicamento().getIdMedicamento());
			if(medicamento != null){
				Inventario buscar = buscarInventario(i.getMedicamento().getIdMedicamento(),i.getFarmacia().getIdFarmacia());
				if(buscar == null){
					i.setFarmacia(farmacia);
					i.setMedicamento(medicamento);
					em.persist(i);
				}else{
					throw new ExcepcionNegocio("Este inventario ya existe");
				}
			}else{
				throw new ExcepcionNegocio("No existe este medicamento");
			}
		}else{
			throw new ExcepcionNegocio("la farmacia no existe");
		}
	}
	
	/**
	 * Metodo que sirve para buscar un inventario
	 * 
	 * @param medicamento
	 *            para metro que recibe
	 * @param farmacia
	 *            medicamento para metro que recibe
	 * @return el inventario buscado
	 */

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Inventario buscarInventario(int medicamento, int farmacia) {

		InventarioPK in = new InventarioPK();
		in.setFarmacia(farmacia);
		in.setMedicamento(medicamento);

		return em.find(Inventario.class, in);
	}

	public Inventario buscar(Medicamento medicamento, Farmacia farmacia) {
		InventarioPK in = new InventarioPK();
		in.setFarmacia(farmacia.getIdFarmacia());
		in.setMedicamento(medicamento.getIdMedicamento());

		return em.find(Inventario.class, in);

	}

	/**
	 * Metodo para buscar una farmacia
	 * 
	 * @param i
	 *            parametro que recibe
	 * @return la farmacia
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Farmacia buscarFarmacia(int i) {
		return em.find(Farmacia.class, i);

	}

	/**
	 * Metoso para buscar una farmacia por usuario
	 * 
	 * @param usuario
	 * @return la farmacia buscada
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Farmacia buscarFarmaciaUsuario(Usuario usuario) {
		Query q = em.createNamedQuery(Farmacia.usuarioFarmacia);
		q.setParameter(1, usuario);
		List<Farmacia> far = q.getResultList();
		if (far.isEmpty()) {
			return null;
		} else {
			return far.get(0);
		}
	}

}
