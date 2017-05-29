package controladores.Administrador;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;
import org.primefaces.component.tabview.Tab;

import co.edu.eam.ingesoft.pa.negocio.beans.MedicoEJB;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;
import co.edu.ingesoft.hospital.persistencia.entidades.Especializaciones;
import co.edu.ingesoft.hospital.persistencia.entidades.Hospital;
import co.edu.ingesoft.hospital.persistencia.entidades.Medico;
import co.edu.ingesoft.hospital.persistencia.entidades.MedicoEspecialista;

@ViewScoped
@Named("medicoAjaxController")
public class MedicoAjaxController implements Serializable {

	@EJB
	private MedicoEJB medicoEJB;

	private String nombre;

	private String apellido;

	private String numeroDocumento;

	private List<Hospital> listaHospitales;

	private int hospitalSeleccionado;

	private List<Medico> medicos;

	private String horario;
	
	private String telefono;

	private String busMedico;
	
	private String especialidad;
	
	/*DATOS PARA REGISTRAR ESPECIALISTA*/
	private String busMedEspe;
	private String idmedicoEsp;
	private String nomMedEsp;
	private String apellidoEsp;
	private int especialidadEsp;
	private List<Especializaciones> listaEspecializaciones;
	private List<MedicoEspecialista>especializacionesMedico;


	@PostConstruct
	public void inicializar() {
		listaHospitales = medicoEJB.listarHospitales();
		medicos = medicoEJB.listarMedicos();
		listaEspecializaciones =medicoEJB.listaEspecialidades();
	}
	
	public void eliminarMedico(Medico medico){
		medicoEJB.eliminarMedico(medico);
		Messages.addFlashGlobalInfo("Se ha eliminado el medico Exitosamente");
		medicos = medicoEJB.listarMedicos();
	}

	public void buscarMedEsp(){
		try{
			if(!busMedEspe.isEmpty()){
				
				Medico med = medicoEJB.buscarMedico(Integer.parseInt(busMedEspe));
				if(med!=null){
					
					
				    idmedicoEsp=String.valueOf(med.getIdentificacion());
					nomMedEsp=med.getNombre();
				    apellidoEsp=med.getApellido();
				    especializacionesMedico = medicoEJB.especializacionesMedico(med);
				    
				    
				}else{
					Messages.addFlashGlobalError("Este medico no se encuentra registrado");
				}
				
			}else{
				Messages.addFlashGlobalError("Por favor busque al medico para asignar especialidad");
			}
			
		}catch (ExcepcionNegocio e) {
			Messages.addFlashGlobalError(e.getMessage());
		}catch (NumberFormatException ex) {
			Messages.addFlashGlobalError("Por favor solo campos numericos");
		}
	}
	
	public void agregarEspecialidad(){
		try{
			if(!busMedEspe.isEmpty()){
				if(especialidadEsp>0){
					
					Medico medico = medicoEJB.buscarMedico(Integer.parseInt(busMedEspe));
					if(medico!=null){
						Especializaciones especialidad = medicoEJB.buscarEspecialidad(especialidadEsp);
						MedicoEspecialista me = new MedicoEspecialista();
						me.setEspecializacion(especialidad);
						me.setMedico(medico);
						medicoEJB.asignarEspecialidad(me);
						medico.setTipoMedico("ESPECIALISTA");
						medicoEJB.editarMedico(medico);
						especializacionesMedico = medicoEJB.especializacionesMedico(medico);
						Messages.addFlashGlobalInfo("se ha asignado la especialidad de: "+especialidad.getNombre()+" Exitosamente");
					}
					
				}else{
					Messages.addFlashGlobalError("seleccione una especialidad");
				}
				
			}else{
				Messages.addFlashGlobalError("Por favor busque al medico para asignar especialidad");
			}
			
		}catch (ExcepcionNegocio e) {
			Messages.addFlashGlobalError(e.getMessage());
		}catch (NumberFormatException ex) {
			Messages.addFlashGlobalError("Por favor solo campos numericos");
		}
	}
	
	public void registrar() {
		try{
			if(!nombre.isEmpty()&&!apellido.isEmpty()&&!numeroDocumento.isEmpty()&&!telefono.isEmpty()&&hospitalSeleccionado>0){
				
				Hospital hospital = medicoEJB.buscarHospital(hospitalSeleccionado);
				Medico m = new Medico();
				m.setIdentificacion(Integer.parseInt(numeroDocumento));
				m.setNombre(nombre);
				m.setApellido(apellido);
				m.setTelefono(telefono);
				m.setHospital(hospital);
				m.setTipoMedico("GENERAL");
				
				medicoEJB.registrarMedico(m);
				medicos = medicoEJB.listarMedicos();
				Messages.addFlashGlobalInfo("Se ha registrado el medico Exitosamente");
				limpiar();
			
			}else{
				Messages.addFlashGlobalError("Por favor ingrese todos los datos");
			}
			
		}catch (ExcepcionNegocio e) {
			Messages.addFlashGlobalError(e.getMessage());
		}catch (NumberFormatException ex) {
			Messages.addFlashGlobalError("Por favor solo campos numericos en documento");
		}
	}

	public void buscar() {
		try {

			if (!busMedico.isEmpty()) {
				Medico me = medicoEJB.buscarMedico(Integer.parseInt(busMedico));
				if (me != null) {

					nombre = me.getNombre();
					apellido = me.getApellido();
					numeroDocumento = String.valueOf(me.getIdentificacion());
					telefono = me.getTelefono();
					especialidad = me.getTipoMedico();
					hospitalSeleccionado = me.getHospital().getIdHospital();
					telefono = me.getTelefono();
					

				} else {
					Messages.addFlashGlobalWarn("Este medico no se encuentra registrado");
				}
				
			} else {
				Messages.addFlashGlobalError("Por favor ingrese la cedula del medico");
			}
		} catch (ExcepcionNegocio e) {
			Messages.addFlashGlobalError(e.getMessage());
		} catch (NumberFormatException ex) {
			Messages.addFlashGlobalError("Por favor solo campos numericos en documento");
		//}catch (NullPointerException ex) {
		//	Messages.addFlashGlobalError("ingrese documento y solo campos numericos");
		//}
		}

	}
	
	
	public void editarMedico(){
		
		try{
		if(!nombre.isEmpty() && !apellido.isEmpty() && !numeroDocumento.isEmpty() && !telefono.isEmpty() && hospitalSeleccionado != 0){
			
			Medico m = new Medico();
			Hospital h = medicoEJB.buscarHospital(hospitalSeleccionado);
			m.setHospital(h);
			m.setApellido(apellido);
			m.setIdentificacion(Integer.parseInt(numeroDocumento));
			m.setNombre(nombre);
			m.setTelefono(telefono);
			m.setTipoMedico(especialidad);
			
			Medico bm = medicoEJB.buscarMedico(m.getIdentificacion());
			if(bm != null){
				medicoEJB.editarMedico(m);
				Messages.addFlashGlobalInfo("Medico editado correctamente");
			}else{
				Messages.addFlashGlobalInfo("El medico con documento "+numeroDocumento+" no existe");
			}
		
		}else{
			Messages.addFlashGlobalError("Verifique que haya buscado el medico y que todos los campos esten llenos");
		}
		}catch (ExcepcionNegocio e) {
			Messages.addFlashGlobalError(e.getMessage());
		}catch (NumberFormatException ex) {
			Messages.addFlashGlobalError("Por favor solo campos numericos en documento");
		}
	}
	
	
	
	
	
	public void limpiar(){
		
		nombre = "";
		apellido = "";
		numeroDocumento = "";
		telefono ="";
		especialidad = "";
		hospitalSeleccionado = 0;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * @param apellido
	 *            the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	
	/**
	 * @return the numeroDocumento
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * @param numeroDocumento the numeroDocumento to set
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * @return the listaHospitales
	 */
	public List<Hospital> getListaHospitales() {
		return listaHospitales;
	}

	/**
	 * @param listaHospitales
	 *            the listaHospitales to set
	 */
	public void setListaHospitales(List<Hospital> listaHospitales) {
		this.listaHospitales = listaHospitales;
	}

	/**
	 * @return the especialidad
	 */
	public String getEspecialidad() {
		return especialidad;
	}

	/**
	 * @param especialidad the especialidad to set
	 */
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	/**
	 * @return the hospitalSeleccionado
	 */
	public int getHospitalSeleccionado() {
		return hospitalSeleccionado;
	}

	/**
	 * @param hospitalSeleccionado the hospitalSeleccionado to set
	 */
	public void setHospitalSeleccionado(int hospitalSeleccionado) {
		this.hospitalSeleccionado = hospitalSeleccionado;
	}

	/**
	 * @return the medicos
	 */
	public List<Medico> getMedicos() {
		return medicos;
	}

	/**
	 * @param medicos
	 *            the medicos to set
	 */
	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}

	/**
	 * @return the horario
	 */
	public String getHorario() {
		return horario;
	}

	/**
	 * @param horario
	 *            the horario to set
	 */
	public void setHorario(String horario) {
		this.horario = horario;
	}

	/**
	 * @return the busMedico
	 */
	public String getBusMedico() {
		return busMedico;
	}

	/**
	 * @param busMedico
	 *            the busMedico to set
	 */
	public void setBusMedico(String busMedico) {
		this.busMedico = busMedico;
	}
	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the idmedicoEsp
	 */
	public String getIdmedicoEsp() {
		return idmedicoEsp;
	}

	/**
	 * @param idmedicoEsp the idmedicoEsp to set
	 */
	public void setIdmedicoEsp(String idmedicoEsp) {
		this.idmedicoEsp = idmedicoEsp;
	}

	/**
	 * @return the nomMedEsp
	 */
	public String getNomMedEsp() {
		return nomMedEsp;
	}

	/**
	 * @param nomMedEsp the nomMedEsp to set
	 */
	public void setNomMedEsp(String nomMedEsp) {
		this.nomMedEsp = nomMedEsp;
	}

	/**
	 * @return the apellidoEsp
	 */
	public String getApellidoEsp() {
		return apellidoEsp;
	}

	/**
	 * @param apellidoEsp the apellidoEsp to set
	 */
	public void setApellidoEsp(String apellidoEsp) {
		this.apellidoEsp = apellidoEsp;
	}

	/**
	 * @return the busMedEspe
	 */
	public String getBusMedEspe() {
		return busMedEspe;
	}

	/**
	 * @param busMedEspe the busMedEspe to set
	 */
	public void setBusMedEspe(String busMedEspe) {
		this.busMedEspe = busMedEspe;
	}



	/**
	 * @return the especialidadEsp
	 */
	public int getEspecialidadEsp() {
		return especialidadEsp;
	}

	/**
	 * @param especialidadEsp the especialidadEsp to set
	 */
	public void setEspecialidadEsp(int especialidadEsp) {
		this.especialidadEsp = especialidadEsp;
	}

	/**
	 * @return the listaEspecializaciones
	 */
	public List<Especializaciones> getListaEspecializaciones() {
		return listaEspecializaciones;
	}

	/**
	 * @param listaEspecializaciones the listaEspecializaciones to set
	 */
	public void setListaEspecializaciones(List<Especializaciones> listaEspecializaciones) {
		this.listaEspecializaciones = listaEspecializaciones;
	}

	/**
	 * @return the especializacionesMedico
	 */
	public List<MedicoEspecialista> getEspecializacionesMedico() {
		return especializacionesMedico;
	}

	/**
	 * @param especializacionesMedico the especializacionesMedico to set
	 */
	public void setEspecializacionesMedico(List<MedicoEspecialista> especializacionesMedico) {
		this.especializacionesMedico = especializacionesMedico;
	}


	

}
