/**
 * 
 */
package co.edu.ingesoft.hospital.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author TOSHIBAP55W
 *
 */
@IdClass(SintomasPatologiasPK.class)
@Entity
@Table(name="Sintomas_Patologias")
public class SintomasPatologias implements Serializable {

	@Id
	@ManyToOne
	@JoinColumn(name="Patologias_Descritas")
	private PatologiasDescritas patologiaDescrita;
	
	@Id
	@ManyToOne
	@JoinColumn(name="Sintomas_id")
	private Sintoma sintoma;
	
	public SintomasPatologias(){
		
	}

	/**
	 * @return the patologiaDescrita
	 */
	public PatologiasDescritas getPatologiaDescrita() {
		return patologiaDescrita;
	}

	/**
	 * @param patologiaDescrita the patologiaDescrita to set
	 */
	public void setPatologiaDescrita(PatologiasDescritas patologiaDescrita) {
		this.patologiaDescrita = patologiaDescrita;
	}

	/**
	 * @return the sintoma
	 */
	public Sintoma getSintoma() {
		return sintoma;
	}

	/**
	 * @param sintoma the sintoma to set
	 */
	public void setSintoma(Sintoma sintoma) {
		this.sintoma = sintoma;
	}
	
	
}
