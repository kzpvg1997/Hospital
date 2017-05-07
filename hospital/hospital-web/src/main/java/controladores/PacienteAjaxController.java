package controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.omnifaces.cdi.ViewScoped;

import co.edu.eam.ingesoft.pa.negocio.beans.PacienteEJB;
import co.edu.ingesoft.hospital.persistencia.entidades.Eps;

@ViewScoped
@Named("pacienteAjaxController")
public class PacienteAjaxController implements Serializable {
	
	@EJB
	private	PacienteEJB pacienteEJB;
	
	@Pattern(regexp="[A-Za-z ]*",message="Ingrese solo letras")
	@Length(min=4,max=30,message="Lonitud entre 4 y 30")
	private String nombre;
	
	@Pattern(regexp="[A-Za-z ]*",message="Ingrese solo letras")
	@Length(min=4,max=30,message="Lonitud entre 4 y 30")
	private String apellido;
	
	@Pattern(regexp="[0-9]*",message="Ingrese solo numeros")
	@Length(min=3,max=10,message="Lonitud entre 3 y 10")
	private String numeroDocumento;
	
	private String generoSeleccionado;
	
	private String epsSeleccionada;
	
	private List<Eps> listaEps;
	
	private String fecha;
	
	@Pattern(regexp="[0-9]*",message="Ingrese solo numeros")
	@Length(min=10,max=10,message="Lonitud entre 3 y 10")
	private String telefono;
	
	@Pattern(regexp="[A-Za-z ]*",message="Ingrese solo letras")
	@Length(min=4,max=30,message="Lonitud entre 4 y 30")
	private String email;
	
	@PostConstruct
	public void inicializar(){
	
		listaEps = pacienteEJB.listarEps();
	}
	
	public void registrar(){
		
	}
	
	public void buscar(){
		
	}
	
	
	

}
