package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the MADRES database table.
 * 
 */
@Entity
@Table(name="MADRES")
@NamedQuery(name="Madre.findAll", query="SELECT m FROM Madre m")
public class Madre implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MADRES_IDMADRE_GENERATOR", sequenceName="SEQ_ID_MADRE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MADRES_IDMADRE_GENERATOR")
	@Column(name="ID_MADRE")
	private long idMadre;

	@Column(name="NRO_CARAVANA")
	private BigDecimal nroCaravana;

	//bi-directional many-to-one association to Ternera
	@OneToMany(mappedBy="madre")
	@Transient
	private List<Ternera> terneras;

	public Madre() {
	}

	public long getIdMadre() {
		return this.idMadre;
	}

	public void setIdMadre(long idMadre) {
		this.idMadre = idMadre;
	}

	public BigDecimal getNroCaravana() {
		return this.nroCaravana;
	}

	public void setNroCaravana(BigDecimal nroCaravana) {
		this.nroCaravana = nroCaravana;
	}

	public List<Ternera> getTerneras() {
		return this.terneras;
	}

	public void setTerneras(List<Ternera> terneras) {
		this.terneras = terneras;
	}

	public Ternera addTernera(Ternera ternera) {
		getTerneras().add(ternera);
		ternera.setMadre(this);

		return ternera;
	}

	public Ternera removeTernera(Ternera ternera) {
		getTerneras().remove(ternera);
		ternera.setMadre(null);

		return ternera;
	}

}