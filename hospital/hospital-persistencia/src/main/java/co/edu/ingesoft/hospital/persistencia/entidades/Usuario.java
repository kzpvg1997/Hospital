/**
 * 
 */
package co.edu.ingesoft.hospital.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author TOSHIBAP55W
 *
 */
@Entity
@Table(name="Usuarios")
@NamedQueries({	
	@NamedQuery(name=Usuario.PERSONA_POR_USUARIO,query="SELECT u FROM Usuario u WHERE u.persona=?1"),
	@NamedQuery(name=Usuario.BUSCAR_USUARIO,query="SELECT u FROM Usuario u WHERE u.usuario=?1")
})
public class Usuario implements Serializable{

	public static final String PERSONA_POR_USUARIO= "Usuario.personaXUsuario";
	
	public static final String BUSCAR_USUARIO = "Usuario.BuscarUsuario";
	
	@Id
	@Column(name="codigo")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_SEQ")
    @SequenceGenerator(sequenceName = "usuario_seq", allocationSize = 1, name = "USUARIO_SEQ")
	private int codigo;
	
	@Column(name="usuario",length=40)
	private String usuario;
	
	@Column(name="password",length=40)
	private String password;
	
	@OneToOne
	@JoinColumn(name="Persona",unique=true)
	private Persona persona;
	
	public Usuario(){
		
	}

	/**
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the persona
	 */
	public Persona getPersona() {
		return persona;
	}

	/**
	 * @param persona the persona to set
	 */
	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.valueOf(persona.getIdentificacion());
	}
	
	
	
}
