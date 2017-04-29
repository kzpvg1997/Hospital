/**
 * 
 */
package co.edu.ingesoft.hospital.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
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
@IdClass(InventarioPK.class)
@Entity
@Table(name="Inventario")
public class Inventario implements Serializable{

	@Id
	@ManyToOne
	@JoinColumn(name="Medicamentos_id")
	private Medicamento medicamento;
	
	@Id
	@ManyToOne
	@JoinColumn(name="Farmacia_id")
	private Farmacia farmacia;
	
	@Column(name="cantidad")
	private int cantidad;
	
	public Inventario(){
		
	}
	
}
