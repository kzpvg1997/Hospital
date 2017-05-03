package controladores;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.omnifaces.cdi.ViewScoped;

@ViewScoped
@Named("medicoAjaxController")
public class MedicoAjaxController implements Serializable {
	
	@Pattern(regexp="[A-Za-z ]*",message="Ingrese solo letras")
	@Length(min=4,max=30,message="Lonitud entre 4 y 30")
	private String nombre;
	
	@Pattern(regexp="[A-Za-z ]*",message="Ingrese solo letras")
	@Length(min=4,max=30,message="Lonitud entre 4 y 30")
	private String apellido;
	
	@Pattern(regexp="[0-9]*",message="Ingrese solo numeros")
	@Length(min=3,max=10,message="Lonitud entre 3 y 10")
	private String numeroDocumento;
	
	private String especialidadSeleccionda;
	
	private String hospitalSeleccionado;
	
	private String horario;
	
	@PostConstruct
	public void inicializar(){
	
	}
	
	public void registrar(){
		
	}
	
	public void buscar(){
		
	}

}
