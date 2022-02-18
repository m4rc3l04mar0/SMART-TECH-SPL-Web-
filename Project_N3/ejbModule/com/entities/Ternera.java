package com.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.enums.Raza;
import com.enums.TipoParto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TERNERAS database table.
 * 
 */
@Entity
@Table(name="TERNERAS")
@NamedQuery(name="Ternera.findAll", query="SELECT t FROM Ternera t")
public class Ternera implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TERNERAS_IDTERNERA_GENERATOR", sequenceName="SEQ_ID_TERNERA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TERNERAS_IDTERNERA_GENERATOR")
	@Column(name="ID_TERNERA")
	private long idTernera;

	@Column(name="CAUSA_MUERTE")
	private String causaMuerte;

	@Temporal(TemporalType.DATE)
	@Column(name="FEC_BAJA")
	private Date fecBaja;

	@Temporal(TemporalType.DATE)
	@Column(name="FEC_MUERTE")
	private Date fecMuerte;

	@Temporal(TemporalType.DATE)
	@Column(name="FEC_NACIMIENTO")
	private Date fecNacimiento;

	@Column(name="MOTIVO_BAJA")
	private String motivoBaja;

	@Column(name="NRO_CARAVANA")
	private BigDecimal nroCaravana;
	
	@Enumerated(EnumType.STRING)
	private TipoParto parto;

	@Column(name="PESO_NACIMIENTO")
	private BigDecimal pesoNacimiento;
	
	@Enumerated(EnumType.STRING)
	private Raza raza;

	//bi-directional many-to-one association to Consumo
	@OneToMany(mappedBy="ternera")
	@Transient
	private List<Consumo> consumos;

	//bi-directional many-to-one association to Dosi
	@OneToMany(mappedBy="ternera")
	@Transient
	private List<Dosi> dosis;

	//bi-directional many-to-one association to Peso
	@OneToMany(mappedBy="ternera")
	@Transient
	private List<Peso> pesos;

	//bi-directional many-to-one association to Guachera
	@ManyToOne
	@JoinColumn(name="ID_GUACHERA")
	@Transient
	private Guachera guachera;

	//bi-directional many-to-one association to Madre
	@ManyToOne
	@JoinColumn(name="ID_MADRE")
	@Transient
	private Madre madre;

	//bi-directional many-to-one association to Padre
	@ManyToOne
	@Transient
	@JoinColumn(name="ID_PADRE")
	private Padre padre;

	//bi-directional many-to-one association to TieneEnfermedade
	@OneToMany(mappedBy="ternera")
	@Transient
	private List<TieneEnfermedade> tieneEnfermedades;

	public Ternera() {
	}

	public long getIdTernera() {
		return this.idTernera;
	}

	public void setIdTernera(long idTernera) {
		this.idTernera = idTernera;
	}

	public String getCausaMuerte() {
		return this.causaMuerte;
	}

	public void setCausaMuerte(String causaMuerte) {
		this.causaMuerte = causaMuerte;
	}

	public Date getFecBaja() {
		return this.fecBaja;
	}

	public void setFecBaja(Date fecBaja) {
		this.fecBaja = fecBaja;
	}

	public Date getFecMuerte() {
		return this.fecMuerte;
	}

	public void setFecMuerte(Date fecMuerte) {
		this.fecMuerte = fecMuerte;
	}

	public Date getFecNacimiento() {
		return this.fecNacimiento;
	}

	public void setFecNacimiento(Date fecNacimiento) {
		this.fecNacimiento = fecNacimiento;
	}

	public String getMotivoBaja() {
		return this.motivoBaja;
	}

	public void setMotivoBaja(String motivoBaja) {
		this.motivoBaja = motivoBaja;
	}

	public BigDecimal getNroCaravana() {
		return this.nroCaravana;
	}

	public void setNroCaravana(BigDecimal nroCaravana) {
		this.nroCaravana = nroCaravana;
	}



	public TipoParto getParto() {
		return parto;
	}

	public void setParto(TipoParto parto) {
		this.parto = parto;
	}

	public BigDecimal getPesoNacimiento() {
		return this.pesoNacimiento;
	}

	public void setPesoNacimiento(BigDecimal pesoNacimiento) {
		this.pesoNacimiento = pesoNacimiento;
	}

	

	public Raza getRaza() {
		return raza;
	}

	public void setRaza(Raza raza) {
		this.raza = raza;
	}

	public List<Consumo> getConsumos() {
		return this.consumos;
	}

	public void setConsumos(List<Consumo> consumos) {
		this.consumos = consumos;
	}

	public Consumo addConsumo(Consumo consumo) {
		getConsumos().add(consumo);
		consumo.setTernera(this);

		return consumo;
	}

	public Consumo removeConsumo(Consumo consumo) {
		getConsumos().remove(consumo);
		consumo.setTernera(null);

		return consumo;
	}

	public List<Dosi> getDosis() {
		return this.dosis;
	}

	public void setDosis(List<Dosi> dosis) {
		this.dosis = dosis;
	}

	public Dosi addDosi(Dosi dosi) {
		getDosis().add(dosi);
		dosi.setTernera(this);

		return dosi;
	}

	public Dosi removeDosi(Dosi dosi) {
		getDosis().remove(dosi);
		dosi.setTernera(null);

		return dosi;
	}

	public List<Peso> getPesos() {
		return this.pesos;
	}

	public void setPesos(List<Peso> pesos) {
		this.pesos = pesos;
	}

	public Peso addPeso(Peso peso) {
		getPesos().add(peso);
		peso.setTernera(this);

		return peso;
	}

	public Peso removePeso(Peso peso) {
		getPesos().remove(peso);
		peso.setTernera(null);

		return peso;
	}

	public Guachera getGuachera() {
		return this.guachera;
	}

	public void setGuachera(Guachera guachera) {
		this.guachera = guachera;
	}

	public Madre getMadre() {
		return this.madre;
	}

	public void setMadre(Madre madre) {
		this.madre = madre;
	}

	public Padre getPadre() {
		return this.padre;
	}

	public void setPadre(Padre padre) {
		this.padre = padre;
	}

	public List<TieneEnfermedade> getTieneEnfermedades() {
		return this.tieneEnfermedades;
	}

	public void setTieneEnfermedades(List<TieneEnfermedade> tieneEnfermedades) {
		this.tieneEnfermedades = tieneEnfermedades;
	}

	public TieneEnfermedade addTieneEnfermedade(TieneEnfermedade tieneEnfermedade) {
		getTieneEnfermedades().add(tieneEnfermedade);
		tieneEnfermedade.setTernera(this);

		return tieneEnfermedade;
	}

	public TieneEnfermedade removeTieneEnfermedade(TieneEnfermedade tieneEnfermedade) {
		getTieneEnfermedades().remove(tieneEnfermedade);
		tieneEnfermedade.setTernera(null);

		return tieneEnfermedade;
	}

}