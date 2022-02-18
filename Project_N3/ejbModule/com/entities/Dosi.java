package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the DOSIS database table.
 * 
 */
@Entity
@Table(name="DOSIS")
@NamedQuery(name="Dosi.findAll", query="SELECT d FROM Dosi d")
public class Dosi implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DosiPK id;

	private BigDecimal cantidad;

	//bi-directional many-to-one association to Medicamento
	@ManyToOne(optional=false)
	@JoinColumn(name="ID_MEDICAMENTO", referencedColumnName="ID_MEDICAMENTO" , insertable=false, updatable=false)
	private Medicamento medicamento;

	//bi-directional many-to-one association to Ternera
	@ManyToOne(optional=false)
	@JoinColumn(name="ID_TERNERA", referencedColumnName="ID_TERNERA" , insertable=false, updatable=false)
	private Ternera ternera;

	public Dosi() {
	}

	public DosiPK getId() {
		return this.id;
	}

	public void setId(DosiPK id) {
		this.id = id;
	}

	public BigDecimal getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public Medicamento getMedicamento() {
		return this.medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public Ternera getTernera() {
		return this.ternera;
	}

	public void setTernera(Ternera ternera) {
		this.ternera = ternera;
	}

}