package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the PADRES database table.
 * 
 */
@Entity
@Table(name="PADRES")
@NamedQuery(name="Padre.findAll", query="SELECT p FROM Padre p")
public class Padre implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PADRES_IDPADRE_GENERATOR", sequenceName="SEQ_ID_PADRE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PADRES_IDPADRE_GENERATOR")
	@Column(name="ID_PADRE")
	private long idPadre;

	@Column(name="NRO_CARAVANA")
	private BigDecimal nroCaravana;

	//bi-directional many-to-one association to Ternera
	@OneToMany(mappedBy="padre")
	@Transient
	private List<Ternera> terneras;

	public Padre() {
	}

	public long getIdPadre() {
		return this.idPadre;
	}

	public void setIdPadre(long idPadre) {
		this.idPadre = idPadre;
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
		ternera.setPadre(this);

		return ternera;
	}

	public Ternera removeTernera(Ternera ternera) {
		getTerneras().remove(ternera);
		ternera.setPadre(null);

		return ternera;
	}

}