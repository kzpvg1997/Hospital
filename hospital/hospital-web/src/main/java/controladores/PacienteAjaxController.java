package controladores;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.pa.negocio.beans.PacienteEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.RolEJB;
import co.edu.ingesoft.hospital.persistencia.entidades.Eps;
import co.edu.ingesoft.hospital.persistencia.entidades.Paciente;


@ViewScoped
@Named("pacienteAjaxController")
public class PacienteAjaxController implements Serializable {
	
	@EJB
	private	PacienteEJB pacienteEJB;
	
	@EJB
	private RolEJB rolEJB;
	
	private String nombre;
	
	private String apellido;
	
	private String busNumeroDocumento;
	
	private String numeroDocumento;
	
	private String generoSeleccionado;
	
	private int epsSeleccionada;
	
	private List<Eps> listaEps;
	
	private String fecha;
	
	private String telefono;

	private String email;
	
	private List<Paciente> pacientes;
	
	private Date fechaNacimiento;
	
	@PostConstruct
	public void inicializar(){
	
		listarCombos();
		pacientes = pacienteEJB.listarPacietes();
		
		
	}
	
	public void listarCombos(){
		listaEps = pacienteEJB.listarEps();
		
	}
	//registrar
	public void registrar() throws ParseException{
		
		if(!(fecha.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty() || email.isEmpty() ||
				generoSeleccionado.equalsIgnoreCase("Seleccione") || (epsSeleccionada == 0) || (numeroDocumento.isEmpty())) ){


			System.out.println("entro");
			
		Paciente pa = pacienteEJB.buscarPaciente(Integer.parseInt(numeroDocumento));
		if(pa == null){
			Paciente p = new Paciente();
			
			p.setNombre(nombre);
			p.setApellido(apellido);
			p.setIdentificacion(Integer.parseInt(numeroDocumento));
			p.setGenero(generoSeleccionado);
			p.setEmail(email);
			Eps e = pacienteEJB.buscarEps(epsSeleccionada);
			p.setEps(e);
			fechaNacimiento = new SimpleDateFormat("dd-MM-yyyy").parse(fecha);
			p.setFechaNacimiento(fechaNacimiento);
			p.setTelefono(telefono);
			if(p.getFechaNacimiento() != null){
				pacienteEJB.crearPaciente(p);
				limpiar();
				Messages.addFlashGlobalInfo("El paciente se ha registrado con exito");
			}else{
				System.out.println("No entro fecha");
				Messages.addFlashGlobalError("No entro fecha");
			}
			
			
		}else{
			Messages.addFlashGlobalError("El paciente con identificacion: "+numeroDocumento+" ya existe");
		}
		
		}else{
			Messages.addFlashGlobalError("Ingrese todos los datos");
			System.out.println("No entro");
		}
	}
	
	public void buscar(){
		
		if(!busNumeroDocumento.isEmpty()){

		Paciente pa = pacienteEJB.buscarPaciente(Integer.parseInt(busNumeroDocumento));
		if(pa != null){
			nombre = pa.getNombre();
			apellido = pa.getApellido();
			numeroDocumento = String.valueOf(pa.getIdentificacion());
			telefono = pa.getTelefono();
			fecha = pa.getFechaNacimiento().toString();
			email = pa.getEmail();
			generoSeleccionado = pa.getGenero();
			epsSeleccionada = pa.getEps().getIdEps();
		}else{
			Messages.addFlashGlobalWarn("El paciente no existe");
			limpiar();
		
		}
		}else{
			Messages.addFlashGlobalWarn("Por favor ingrese documento");
		}

	}
	
	public void editar() throws ParseException{
		
		if(!fecha.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty() || email.isEmpty() ||
				generoSeleccionado.equalsIgnoreCase("Seleccione") || (epsSeleccionada == 0) || (numeroDocumento.isEmpty()) ){

			System.out.println("entro");

			Paciente p = new Paciente();
			
			p.setNombre(nombre);
			p.setApellido(apellido);
			p.setIdentificacion(Integer.parseInt(numeroDocumento));
			p.setGenero(generoSeleccionado);
			p.setEmail(email);
			Eps e = pacienteEJB.buscarEps(epsSeleccionada);
			p.setEps(e);
			fechaNacimiento = new SimpleDateFormat("dd-MM-yyyy").parse(fecha);
			p.setFechaNacimiento(fechaNacimiento);
			p.setTelefono(telefono);
			if(p.getFechaNacimiento() != null){
				pacienteEJB.editarPaciente(p);
				limpiar();
				Messages.addFlashGlobalInfo("El paciente se editado correctamente");
			}else{
				System.out.println("No entro fecha");
				Messages.addFlashGlobalError("Vrifique la fecha");
			}

		
		}else{
			Messages.addFlashGlobalError("Ingrese todos los datos");
			System.out.println("No entro");
		}
	}
	
	public void borrar(Paciente p) {
		pacienteEJB.borrarPaciente(p);
		Messages.addFlashGlobalInfo("El paciente ha sido eliminada exitosamente");
	}
	
	
	public void limpiar(){
		nombre = "";
		apellido = "";
		numeroDocumento = "";
		telefono = "";
		fecha = "";
		email = "";
		generoSeleccionado = "Seleccione";
		epsSeleccionada = 111;
		numeroDocumento = "";
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
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * @return the busNumeroDocumento
	 */
	public String getBusNumeroDocumento() {
		return busNumeroDocumento;
	}

	/**
	 * @param busNumeroDocumento the busNumeroDocumento to set
	 */
	public void setBusNumeroDocumento(String busNumeroDocumento) {
		this.busNumeroDocumento = busNumeroDocumento;
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
	 * @return the generoSeleccionado
	 */
	public String getGeneroSeleccionado() {
		return generoSeleccionado;
	}

	/**
	 * @param generoSeleccionado the generoSeleccionado to set
	 */
	public void setGeneroSeleccionado(String generoSeleccionado) {
		this.generoSeleccionado = generoSeleccionado;
	}

	/**
<<<<<<< HEAD
=======
	 * @return the epsSeleccionada
	 */
	public int getEpsSeleccionada() {
		return epsSeleccionada;
	}

	/**
>>>>>>> branch 'master' of https://github.com/kzpvg1997/Hospital.git
	 * @param epsSeleccionada the epsSeleccionada to set
	 */
	public void setEpsSeleccionada(int epsSeleccionada) {
		this.epsSeleccionada = epsSeleccionada;
	}

	/**
	 * @return the listaEps
	 */
	public List<Eps> getListaEps() {
		return listaEps;
	}

	/**
	 * @param listaEps the listaEps to set
	 */
	public void setListaEps(List<Eps> listaEps) {
		this.listaEps = listaEps;
	}

	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the pacientes
	 */
	public List<Paciente> getPacientes() {
		return pacientes;
	}

	/**
	 * @param pacientes the pacientes to set
	 */
	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	/**
	 * @return the fechaNacimiento
	 */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	
	
}
