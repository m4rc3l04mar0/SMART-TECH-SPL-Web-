package com.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the CONSUMOS database table.
 * 
 */
@Entity
@Table(name="CONSUMOS")
@NamedQuery(name="Consumo.findAll", query="SELECT c FROM Consumo c")
public class Consumo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@EmbeddedId
	private ConsumoPK id;

	private BigDecimal cantidad;

	//bi-directional many-to-one association to Alimento
	
	@ManyToOne(optional=false)
	@JoinColumn(name="ID_ALIMENTO", referencedColumnName="ID_ALIMENTO" , insertable=false, updatable=false)
	@Transient
	private Alimento alimento;
	
	//bi-directional many-to-one association to Ternera
	@ManyToOne(optional=false)
	@JoinColumn(name="ID_TERNERA", referencedColumnName="ID_TERNERA" , insertable=false, updatable=false)
	@Transient
	private Ternera ternera;

	public Consumo() {
		super();
	}

	
	
	
	public ConsumoPK getId() {
		return this.id;
	}

	public void setId(ConsumoPK id) {
		this.id = id;
	}

	public BigDecimal getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public Alimento getAlimento() {
		return this.alimento;
	}

	public void setAlimento(Alimento alimento) {
		this.alimento = alimento;
	}

	public Ternera getTernera() {
		return this.ternera;
	}

	public void setTernera(Ternera ternera) {
		this.ternera = ternera;
	}
	/*
	
	public long getTerneraId(){
		  return getId().getIdTernera();
		}
	public long getAlimentoId(){
		  return getId().getIdAlimento();
		}
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date getFecha() {
		return getId().getFecha();
	}
*/
}