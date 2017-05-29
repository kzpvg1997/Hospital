package controladores;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.pa.negocio.beans.InventarioEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.MedicamentoEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.QuirofanoEJB;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;
import co.edu.ingesoft.hospital.persistencia.entidades.Farmacia;
import co.edu.ingesoft.hospital.persistencia.entidades.Inventario;
import co.edu.ingesoft.hospital.persistencia.entidades.Medicamento;
import session.SessionController;

@ViewScoped
@Named("gestionMedicamentoAjaxController")
public class GestionMedicamentoAjaxController implements Serializable {
	
	@EJB
	private MedicamentoEJB  medicamentoEJB;
	
	@EJB
	private InventarioEJB inventarioEJB;

	
	@Inject
	private SessionController sesionController;
	
	private String nombre;
	
	private String descripcion;
	
	private String precio;
	
	private String busMedicamento;
	
	private int cantidad;
	
	
	@PostConstruct
	public void inicializar() {
		
	}
	
	
	
	
	public void registrarMedicamento(){
		
		try{
		
		if(!nombre.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty() && cantidad >0){
			
			Medicamento m = new Medicamento();
			m.setNombre(nombre);
			m.setPrecio(Integer.parseInt(precio));
			m.setDescripcion(descripcion);
			medicamentoEJB.crearMedicamento(m);
			
			
			
			Farmacia far = inventarioEJB.buscarFarmaciaUsuario(sesionController.getUsuario());
			System.out.println(far+"??????????===========");
			if(far!=null){
				Inventario inv = new Inventario();
				inv.setCantidad(cantidad);
				inv.setMedicamento(m);
				inv.setFarmacia(far);
				inventarioEJB.asignarInventario(inv);
				Messages.addFlashGlobalInfo("Se registro correctamento el medicamento");
				limpiar();
//				Medicamento medi = medicamentoEJB.buscarMedicamento(m.getIdMedicamento());
//				System.out.println(medi+"??????????===========");
//				if(medi!=null){
//					Inventario inv = new Inventario();
//					inv.setCantidad(cantidad);
//					inv.setMedicamento(medi);
//					inv.setFarmacia(far);
//					System.out.println(inv.getCantidad());
//					System.out.println(inv.getFarmacia().getNombre());
//					System.out.println(inv.getMedicamento().getNombre());
//					System.out.println(inv);
//					inventarioEJB.asignarInventario(inv);
//					Messages.addFlashGlobalInfo("Se registro correctamento el medicamento");
//					limpiar();
//				}
				
			}
			
			
		}else{
			Messages.addFlashGlobalWarn("Verifique que esten llenos los campos.");
		}
		
		}catch (ExcepcionNegocio e) {
			Messages.addFlashGlobalError(e.getMessage());
		}catch (NumberFormatException ex) {
			Messages.addFlashGlobalError("Por favor solo campos numericos en el precio");
		}
		
	}
	
	public void buscarMEdicamento(){
		
		if(!busMedicamento.isEmpty()){
			Medicamento m = medicamentoEJB.buscarMedicamentoNomre(busMedicamento);
			if(m!=null){
				nombre = m.getNombre();
				descripcion = m.getDescripcion();
				precio = String.valueOf(m.getPrecio());
				
			}else{
				Messages.addFlashGlobalError("El medicamento con nombre "+busMedicamento+" no existe");
			}

		}else{
			Messages.addFlashGlobalError("Para buscar ingrese el nombre del medicamento");
		}
		
	}
	
	
	public void limpiar(){
		
		nombre = "";
		descripcion = "";
		precio = "";
		cantidad = 0;
		
	}
	



	/**
	 * @return the cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}




	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}




	/**
	 * @return the busMedicamento
	 */
	public String getBusMedicamento() {
		return busMedicamento;
	}




	/**
	 * @param busMedicamento the busMedicamento to set
	 */
	public void setBusMedicamento(String busMedicamento) {
		this.busMedicamento = busMedicamento;
	}




	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}


	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	/**
	 * @return the precio
	 */
	public String getPrecio() {
		return precio;
	}


	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	
	
	
	
	

}
