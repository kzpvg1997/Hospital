/**
 * 
 */
package controladores.Administrador;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.pa.negocio.beans.CitaEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.MedicoEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.SintomasPatologiasEJB;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;
import co.edu.ingesoft.hospital.persistencia.entidades.PatologiasDescritas;
import co.edu.ingesoft.hospital.persistencia.entidades.Sintoma;
import co.edu.ingesoft.hospital.persistencia.entidades.SintomasPatologias;
import co.edu.ingesoft.hospital.persistencia.entidades.Tratamiento;
import co.edu.ingesoft.hospital.persistencia.entidades.TratamientoSintoma;
import session.SessionController;

/**
 * @author TOSHIBAP55W
 *
 */
@ViewScoped
@Named("patoSintAjaxController")
public class PatologiaSintomasAjaxController implements Serializable{

	@Inject
	private SessionController sesionController;
	
	@EJB
	private MedicoEJB medicoEJB;
	
	@EJB
	private CitaEJB citaEJB;
	
	@EJB
	private SintomasPatologiasEJB spEJB;
	
	/*DATOS PATOLOGIAS*/
	private String descripcionPatologia;
	private String nombrePatologia;
	
	/*DATOS SINTOMAS*/
	private String nombreSintoma;
	private String descripcionSintoma;
	
	/*DATOS TRATAMIENTOS*/
	private String nombreTratamiento;
	private String descripcionTratamiento;
	
	
	public void buscarTratamiento(){
		try{
			if(!nombreTratamiento.isEmpty()){
				Tratamiento tra = spEJB.buscarTratamientoXNombre(nombreTratamiento);
				if(tra!=null){
					descripcionTratamiento = tra.getDescripcion();
				}else{
					Messages.addFlashGlobalError("El tratamiento con nombre: ''"+nombreTratamiento+"'' no esta registrado");
				}
				
			}else{
				Messages.addFlashGlobalWarn("Por favor ingrese el nombre del tratamiento");
			}
			
		}catch (ExcepcionNegocio e) {
			Messages.addFlashGlobalError(e.getMessage());
		}
	}
	
	public void registrarTratamiento(){
		try{
			
			if(!nombreTratamiento.isEmpty()&&!descripcionTratamiento.isEmpty()){
				if(!nombreSintoma.isEmpty()){
					Sintoma sin = spEJB.buscarSintomaXNombre(nombreSintoma);
					if(sin!=null){
						
						Tratamiento tra = new Tratamiento(nombreTratamiento, descripcionTratamiento);
						spEJB.registrarTratamiento(tra);
						TratamientoSintoma ts = new TratamientoSintoma();
						ts.setSintoma(sin);
						ts.setTratamiento(tra);
						spEJB.asignarTratamientoSintoma(ts);
						Messages.addFlashGlobalInfo("Se ha asignado un tratamiento a: ''"+sin.getNombreSintoma()+"'' exitosamente!.");
						limpiarTratamineto();
					}else{
						Messages.addFlashGlobalError("El sintoma con nombre: "+nombreSintoma+" No se encuentra registrado");
					}
					
				}else{
					Messages.addFlashGlobalWarn("Por favor ingrese el nombre del sintoma que desea asignar el tratamiento");
				}
			}else{
				Messages.addFlashGlobalWarn("Por favor ingrese todos los datos de tratamiento");
			}
			
		}catch (ExcepcionNegocio e) {
			Messages.addFlashGlobalError(e.getMessage());
		}
	}
	
	
	public void buscarSintoma(){
		try{
			
		if(!nombreSintoma.isEmpty()){
			
			Sintoma sin = spEJB.buscarSintomaXNombre(nombreSintoma);
			if(sin!=null){
				descripcionSintoma= sin.getDescripcion();
			}else{
				Messages.addFlashGlobalError("El sintoma con nombre: "+nombreSintoma+" No se encuentra registrado");
			}
		}else{
			Messages.addFlashGlobalWarn("Para buscar ingrese el nombre del sintoma");
		}
	}catch (ExcepcionNegocio e) {
		Messages.addFlashGlobalError(e.getMessage());
	}
	}
	
	public void registrarSintoma(){
		try{
			
			if(!nombreSintoma.isEmpty()&&!descripcionSintoma.isEmpty()){
				if(!nombrePatologia.isEmpty()){
					
				PatologiasDescritas pat = spEJB.buscarPatologiaXNombre(nombrePatologia);	
				if(pat!=null){
				Sintoma sintoma = new Sintoma(nombreSintoma, descripcionSintoma);
				spEJB.registrarSintomas(sintoma);
				SintomasPatologias sinPat = new SintomasPatologias();
				sinPat.setPatologiaDescrita(pat);
				sinPat.setSintoma(sintoma);
				spEJB.asignarSintomaPatologia(sinPat);
				Messages.addFlashGlobalInfo("Se ha asignado un sintoma a: ''"+pat.getNombrePatologia()+"'' exitosamente!.");
				limpiarSintoma();
					}else{
						Messages.addFlashGlobalError("La patologia con este nombre no se encuentra registrada");
					}
				}else{
					Messages.addFlashGlobalError("Por favor busque la patologia para asignar un sintoma");
				}
			}else{
				Messages.addFlashGlobalWarn("Por favor ingrese todos los datos de sintoma");
			}
			
		}catch (ExcepcionNegocio e) {
			Messages.addFlashGlobalError(e.getMessage());
		}
	}
	
	public void buscarPatologia(){
		try{
			
			if(!nombrePatologia.isEmpty()){
				
				PatologiasDescritas pato = spEJB.buscarPatologiaXNombre(nombrePatologia);
				if(pato!=null){
					descripcionPatologia = pato.getDescripcion();
				}else{
					Messages.addFlashGlobalError("La patologia con este nombre no se encuentra registrada");
				}
				
			}else{
				Messages.addFlashGlobalWarn("Para buscar ingrese el nombre de la patologia");
			}
			
		}catch (ExcepcionNegocio e) {
			Messages.addFlashGlobalError(e.getMessage());
		}
	}
	
	
	public void registrarPatologia(){
		try{
			if(!descripcionPatologia.isEmpty()&&!nombrePatologia.isEmpty()){
				
				PatologiasDescritas pat = new PatologiasDescritas(nombrePatologia, descripcionPatologia);
				spEJB.registrarPatologia(pat);
				limpiarPatologia();
				Messages.addFlashGlobalInfo("Se ha registrado la patologia con exito");
				
			}else{
				Messages.addFlashGlobalWarn("Por favor ingrese todos los campos de patologias");
			}
			
		}catch (ExcepcionNegocio e) {
			Messages.addFlashGlobalError(e.getMessage());
		}
	}
	
	public void limpiarTratamineto(){
		descripcionTratamiento="";
		nombreTratamiento="";
	}
	
	public void limpiarSintoma(){
		descripcionSintoma="";
		nombreSintoma="";
	}
	
	public void limpiarPatologia(){
		descripcionPatologia="";
		nombrePatologia="";
	}
	
	/**
	 * @return the sesionController
	 */
	public SessionController getSesionController() {
		return sesionController;
	}
	/**
	 * @param sesionController the sesionController to set
	 */
	public void setSesionController(SessionController sesionController) {
		this.sesionController = sesionController;
	}
	/**
	 * @return the descripcionPatologia
	 */
	public String getDescripcionPatologia() {
		return descripcionPatologia;
	}
	/**
	 * @param descripcionPatologia the descripcionPatologia to set
	 */
	public void setDescripcionPatologia(String descripcionPatologia) {
		this.descripcionPatologia = descripcionPatologia;
	}
	/**
	 * @return the nombrePatologia
	 */
	public String getNombrePatologia() {
		return nombrePatologia;
	}
	/**
	 * @param nombrePatologia the nombrePatologia to set
	 */
	public void setNombrePatologia(String nombrePatologia) {
		this.nombrePatologia = nombrePatologia;
	}
	/**
	 * @return the nombreSintoma
	 */
	public String getNombreSintoma() {
		return nombreSintoma;
	}
	/**
	 * @param nombreSintoma the nombreSintoma to set
	 */
	public void setNombreSintoma(String nombreSintoma) {
		this.nombreSintoma = nombreSintoma;
	}
	/**
	 * @return the descripcionSintoma
	 */
	public String getDescripcionSintoma() {
		return descripcionSintoma;
	}
	/**
	 * @param descripcionSintoma the descripcionSintoma to set
	 */
	public void setDescripcionSintoma(String descripcionSintoma) {
		this.descripcionSintoma = descripcionSintoma;
	}
	/**
	 * @return the nombreTratamiento
	 */
	public String getNombreTratamiento() {
		return nombreTratamiento;
	}
	/**
	 * @param nombreTratamiento the nombreTratamiento to set
	 */
	public void setNombreTratamiento(String nombreTratamiento) {
		this.nombreTratamiento = nombreTratamiento;
	}
	/**
	 * @return the descripcionTratamiento
	 */
	public String getDescripcionTratamiento() {
		return descripcionTratamiento;
	}
	/**
	 * @param descripcionTratamiento the descripcionTratamiento to set
	 */
	public void setDescripcionTratamiento(String descripcionTratamiento) {
		this.descripcionTratamiento = descripcionTratamiento;
	}
	
	
	
	
	
	
}
