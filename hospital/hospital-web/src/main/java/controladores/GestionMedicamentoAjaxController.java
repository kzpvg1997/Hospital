package controladores;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.pa.negocio.beans.MedicamentoEJB;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;
import co.edu.ingesoft.hospital.persistencia.entidades.Medicamento;

@ViewScoped
@Named("gestionMedicamentoAjaxController")
public class GestionMedicamentoAjaxController implements Serializable {
	
	@EJB
	private MedicamentoEJB  medicamentoEJB;
	
	private String nombre;
	
	private String descripcion;
	
	private String precio;
	
	
	@PostConstruct
	public void inicializar() {
		
	}
	
	
	
	
	public void registrarMedicamento(){
		
		try{
		
		if(!nombre.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()){
			
			Medicamento m = new Medicamento();
			
			m.setNombre(nombre);
			m.setPrecio(Integer.parseInt(precio));
			m.setDescripcion(descripcion);
			
			medicamentoEJB.crearMedicamento(m);
			Messages.addFlashGlobalInfo("Se registro correctamento el medicamento");
			
		}else{
			Messages.addFlashGlobalWarn("Verifique que esten llenos los campos");
		}
		
		}catch (ExcepcionNegocio e) {
			Messages.addFlashGlobalError(e.getMessage());
		}catch (NumberFormatException ex) {
			Messages.addFlashGlobalError("Por favor solo campos numericos en el precio");
		}
		
	}
	
	
	public void limpiar(){
		
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
