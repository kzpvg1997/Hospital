package controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.pa.negocio.beans.MedicoEJB;
import co.edu.ingesoft.hospital.persistencia.entidades.Hospital;
import co.edu.ingesoft.hospital.persistencia.entidades.Medico;



@ViewScoped
@Named("medicoAjaxController")
public class MedicoAjaxController implements Serializable {
	
	@EJB
	private MedicoEJB medicoEJB;
	
	@Pattern(regexp="[A-Za-z ]*",message="Ingrese solo letras")
	@Length(min=4,max=30,message="Lonitud entre 4 y 30")
	private String nombre;
	
	@Pattern(regexp="[A-Za-z ]*",message="Ingrese solo letras")
	@Length(min=4,max=30,message="Lonitud entre 4 y 30")
	private String apellido;
	
	@Pattern(regexp="[0-9]*",message="Ingrese solo numeros")
	@Length(min=3,max=10,message="Lonitud entre 3 y 10")
	private int numeroDocumento;
	
	private List<Hospital> listaHospitales;
		
	private String especialidadSeleccionda;
	
	private String hospitalSeleccionado;
	
	private List<Hospital> hospitales;
	
	private List<Medico> medicos;
	
	private String horario;
	
	private int busMedico;
	
	private boolean general;
	
	private boolean activo;
	
	private boolean noActivo;
	
	@PostConstruct
	public void inicializar(){
		hospitales = medicoEJB.listarHospitales();
		medicos = medicoEJB.listarMedicos();
	}
	
	public void registrar(){
		
	}
	
	public void buscar(){
		
		Medico me = medicoEJB.buscarMedico(busMedico);
		if(me != null){
			
			nombre = me.getNombre();
			apellido = me.getApellido();
			numeroDocumento = me.getIdentificacion();
			especialidadSeleccionda = me.getTipoMedico();
			hospitalSeleccionado = me.getHospital().getNombre();			
	
		}else{
			Messages.addFlashGlobalInfo("El Medico no existe");
		}
		
		
	}
	}
	

