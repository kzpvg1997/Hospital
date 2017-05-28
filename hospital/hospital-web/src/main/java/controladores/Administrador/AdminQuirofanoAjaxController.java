/**
 * 
 */
package controladores.Administrador;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.pa.negocio.beans.QuirofanoEJB;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;
import co.edu.ingesoft.hospital.persistencia.entidades.Cama;
import co.edu.ingesoft.hospital.persistencia.entidades.Hospital;
import co.edu.ingesoft.hospital.persistencia.entidades.Quirofano;
import session.SessionController;

/**
 * @author TOSHIBAP55W
 *
 */
@ViewScoped
@Named("adminQuirofanoAjaxController")
public class AdminQuirofanoAjaxController implements Serializable{

	@EJB
	private QuirofanoEJB quirofanoEJB;
	
	@Inject
	private SessionController sesionController;
	
	/*DATOS PARA GESTION DE QUIROFANOS*/
	private String busNumeroQuirofano;
	private String numeroQuirofano;
	private String nombre;
	private String tipoQuirofanoSeleccionado;
	private String localizacion;
	private String descripcion;
	private String ocupado;
	
	/*DATOS PARA GESTION DE CAMAS*/
	private String busNumeroCama;
	private String descripcionCama;
	private String numeroCama;
	private String disponibleCama;
	private String hospitalCama;
	private List<Cama> listaCamas;
	private String camaSeleccionada;
	
	
	@PostConstruct
	public void inicializar(){
	
		Hospital hospital = quirofanoEJB.buscarHospital(sesionController.getUsuario());
		listaCamas = quirofanoEJB.listaCamasHospital(hospital);
		
		
	}
	
	public void borrarCama(Cama cama){
		quirofanoEJB.eliminarCama(cama);
		Messages.addFlashGlobalInfo("Se ha eliminado correctamente!");
		Hospital hospital = quirofanoEJB.buscarHospital(sesionController.getUsuario());
		listaCamas = quirofanoEJB.listaCamasHospital(hospital);
		limpiarCama();
	}
	
	public void buscarCamaTabla(Cama cama){
		
		Cama cam= quirofanoEJB.buscarCama(cama.getNumeroCama());
		if(cam!=null){
			
			descripcionCama = cam.getDescripcion();
			numeroCama = String.valueOf(cam.getNumeroCama());
			Hospital hosp = cam.getHospital();
			hospitalCama = hosp.getNombre();
			if(cam.isDisponible()==true){
				disponibleCama="SI Disponible";
			}else{
			disponibleCama="NO Disponible";
			}
			
			
		}else{
			Messages.addFlashGlobalError("La cama con numero: ''"+busNumeroCama+"'' No se encuentra registrada");	
		}
	}
	
	
	public void registrarCama(){
		try{
			if(!numeroCama.isEmpty()&&!descripcionCama.isEmpty()){
				
				Hospital hospital = quirofanoEJB.buscarHospital(sesionController.getUsuario());
				Cama cama = new Cama(Integer.parseInt(numeroCama), true, descripcionCama, hospital);
				quirofanoEJB.registrarCama(cama);
				Messages.addFlashGlobalInfo("Se ha resgistrado una cama exitosamente en el hospital: ''"+
						hospital.getNombre()+"''. ");
				limpiarCama();

				listaCamas = quirofanoEJB.listaCamasHospital(hospital);
				
			}else{
				Messages.addFlashGlobalError("por favor ingrese el numero de la cama y su descripcion");
			}
			
		}catch (ExcepcionNegocio e){
			Messages.addFlashGlobalError(e.getMessage());
		}catch (NumberFormatException ex) {
			Messages.addFlashGlobalError("Por favor solo campos en el numero de la cama");
		}
	}
	
	public void buscarCama(){
		
		try{
			if(!busNumeroCama.isEmpty()){
				Cama cama= quirofanoEJB.buscarCama(Integer.parseInt(busNumeroCama));
				if(cama!=null){
					
					descripcionCama = cama.getDescripcion();
					numeroCama = String.valueOf(cama.getNumeroCama());
					Hospital hosp = cama.getHospital();
					hospitalCama = hosp.getNombre();
					if(cama.isDisponible()==true){
						disponibleCama="SI Disponible";
					}else{
					disponibleCama="NO Disponible";
					}
					
					
				}else{
					Messages.addFlashGlobalError("La cama con numero: ''"+busNumeroCama+"'' No se encuentra registrada");	
				}
			}else{
				Messages.addFlashGlobalWarn("Por favor ingrese el numero de la cama"); 
			}
			
			
		}catch (ExcepcionNegocio e){
			Messages.addFlashGlobalError(e.getMessage());
		}catch (NumberFormatException ex) {
			Messages.addFlashGlobalError("Por favor solo campos numericos para buscar");
		}
	}
	
	public void registrarQuirofano(){
		
		try{
			if(!nombre.isEmpty()&&!numeroQuirofano.isEmpty()&&!localizacion.isEmpty()&&
					!descripcion.isEmpty()&&!tipoQuirofanoSeleccionado.equals("Seleccione")){
				
				Hospital hospital = quirofanoEJB.buscarHospital(sesionController.getUsuario());
				
				Quirofano qui = new Quirofano(Integer.parseInt(numeroQuirofano), nombre, localizacion, descripcion, 
						tipoQuirofanoSeleccionado, false, hospital);
				quirofanoEJB.registrarQuirofano(qui);
				limpiarQuirofano();
				
				Messages.addFlashGlobalInfo("Se ha resgistrado un quirofano exitosamente en el hospital: ''"+
						hospital.getNombre()+"''. ");
			}else{
				Messages.addFlashGlobalWarn("Por favor ingrese todos los campos");
			}
			
			
		}catch (ExcepcionNegocio ex){
			Messages.addFlashGlobalWarn(ex.getMessage());
		}catch (NumberFormatException e) {
			Messages.addFlashGlobalWarn("Por favor solo campos numericos en el numero de quirofano");
		}
	}
	
	public void editarQuirofano(){
		try{

				Quirofano quiro = quirofanoEJB.buscarQuirofano(Integer.parseInt(busNumeroQuirofano));
				if(quiro==null){
					Messages.addFlashGlobalWarn("El quirofano con numero: ''"+busNumeroQuirofano+"'' (NO)"
							+ " se encuentra registrado");
				}else{
					if(!nombre.isEmpty()&&!numeroQuirofano.isEmpty()&&!localizacion.isEmpty()&&
							!descripcion.isEmpty()&&!tipoQuirofanoSeleccionado.equals("Seleccione")){
						
							Hospital hospital = quirofanoEJB.buscarHospital(sesionController.getUsuario());
							quiro.setHospital(hospital);
							quiro.setNombre(nombre);
							quiro.setLocalizacion(localizacion);
							quiro.setDescripcion(descripcion);
							quiro.setTipoQuirofano(tipoQuirofanoSeleccionado);
							quirofanoEJB.editarQuirofano(quiro);
							limpiarQuirofano();
							Messages.addFlashGlobalInfo("Se ha editado un quirofano exitosamente el quirofano: ''"+
									quiro.getNombre()+"''. ");
					}else{
						Messages.addFlashGlobalWarn("Por favor ingrese todos los campos");
					}
				}

		}catch (ExcepcionNegocio ex){
			Messages.addFlashGlobalWarn(ex.getMessage());
		}catch (NumberFormatException e) {
			Messages.addFlashGlobalWarn("Por favor solo campos numericos y busque el quirofano");
		}
	}
	
	public void buscarQuirofano(){
		
		try{
		if(!busNumeroQuirofano.isEmpty()){
			
			Quirofano qui = quirofanoEJB.buscarQuirofano(Integer.parseInt(busNumeroQuirofano));
			if(qui!=null){
				System.out.println("EXISTE");
				nombre = qui.getNombre();
				numeroQuirofano = String.valueOf(qui.getIdQuirofano());
				localizacion = qui.getLocalizacion();
				descripcion = qui.getDescripcion();
				tipoQuirofanoSeleccionado = qui.getTipoQuirofano();
				if(qui.isOcupado()==true){
					ocupado= "Ocupado";
				}else{
					ocupado="Desocupado";
				}
				
			}else{
				Messages.addFlashGlobalWarn("El quirofano con numero: ''"+busNumeroQuirofano+"'' (NO)"
						+ " se encuentra registrado");
			}
		}else{
			Messages.addFlashGlobalWarn("Por favor ingrese numero de quirofano");
		}
		
		} catch (ExcepcionNegocio ex){
			Messages.addFlashGlobalWarn(ex.getMessage());
		}catch (NumberFormatException e) {
			Messages.addFlashGlobalWarn("Por favor solo campos numericos");
		}
		
	}
	
	public void limpiarQuirofano(){
		nombre = "";
		numeroQuirofano = "";
		localizacion = "";
		descripcion = "";
		tipoQuirofanoSeleccionado = "Seleccione";
		ocupado= "No disponible para registro";
	}
	
	public void limpiarCama(){
		numeroCama= "";
		descripcionCama="";
		disponibleCama="";
		hospitalCama="";
	}

	/**
	 * @return the numeroQuirofano
	 */
	public String getNumeroQuirofano() {
		return numeroQuirofano;
	}

	/**
	 * @param numeroQuirofano the numeroQuirofano to set
	 */
	public void setNumeroQuirofano(String numeroQuirofano) {
		this.numeroQuirofano = numeroQuirofano;
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
	 * @return the tipoQuirofanoSeleccionado
	 */
	public String getTipoQuirofanoSeleccionado() {
		return tipoQuirofanoSeleccionado;
	}

	/**
	 * @param tipoQuirofanoSeleccionado the tipoQuirofanoSeleccionado to set
	 */
	public void setTipoQuirofanoSeleccionado(String tipoQuirofanoSeleccionado) {
		this.tipoQuirofanoSeleccionado = tipoQuirofanoSeleccionado;
	}

	/**
	 * @return the localizacion
	 */
	public String getLocalizacion() {
		return localizacion;
	}

	/**
	 * @param localizacion the localizacion to set
	 */
	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
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
	 * @return the ocupado
	 */
	public String getOcupado() {
		return ocupado;
	}

	/**
	 * @param ocupado the ocupado to set
	 */
	public void setOcupado(String ocupado) {
		this.ocupado = ocupado;
	}


	/**
	 * @return the busNumeroQuirofano
	 */
	public String getBusNumeroQuirofano() {
		return busNumeroQuirofano;
	}


	/**
	 * @param busNumeroQuirofano the busNumeroQuirofano to set
	 */
	public void setBusNumeroQuirofano(String busNumeroQuirofano) {
		this.busNumeroQuirofano = busNumeroQuirofano;
	}

	/**
	 * @return the busNumeroCama
	 */
	public String getBusNumeroCama() {
		return busNumeroCama;
	}

	/**
	 * @param busNumeroCama the busNumeroCama to set
	 */
	public void setBusNumeroCama(String busNumeroCama) {
		this.busNumeroCama = busNumeroCama;
	}

	/**
	 * @return the descripcionCama
	 */
	public String getDescripcionCama() {
		return descripcionCama;
	}

	/**
	 * @param descripcionCama the descripcionCama to set
	 */
	public void setDescripcionCama(String descripcionCama) {
		this.descripcionCama = descripcionCama;
	}

	/**
	 * @return the disponibleCama
	 */
	public String getDisponibleCama() {
		return disponibleCama;
	}

	/**
	 * @param disponibleCama the disponibleCama to set
	 */
	public void setDisponibleCama(String disponibleCama) {
		this.disponibleCama = disponibleCama;
	}

	/**
	 * @return the numeroCama
	 */
	public String getNumeroCama() {
		return numeroCama;
	}

	/**
	 * @param numeroCama the numeroCama to set
	 */
	public void setNumeroCama(String numeroCama) {
		this.numeroCama = numeroCama;
	}

	/**
	 * @return the hospitalCama
	 */
	public String getHospitalCama() {
		return hospitalCama;
	}

	/**
	 * @param hospitalCama the hospitalCama to set
	 */
	public void setHospitalCama(String hospitalCama) {
		this.hospitalCama = hospitalCama;
	}

	/**
	 * @return the listaCamas
	 */
	public List<Cama> getListaCamas() {
		return listaCamas;
	}

	/**
	 * @param listaCamas the listaCamas to set
	 */
	public void setListaCamas(List<Cama> listaCamas) {
		this.listaCamas = listaCamas;
	}

	/**
	 * @return the camaSeleccionada
	 */
	public String getCamaSeleccionada() {
		return camaSeleccionada;
	}

	/**
	 * @param camaSeleccionada the camaSeleccionada to set
	 */
	public void setCamaSeleccionada(String camaSeleccionada) {
		this.camaSeleccionada = camaSeleccionada;
	}

	
}
