package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the MEDICAMENTOS database table.
 * 
 */
@Entity
@Table(name="MEDICAMENTOS")
@NamedQuery(name="Medicamento.findAll", query="SELECT m FROM Medicamento m")
public class Medicamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MEDICAMENTOS_IDMEDICAMENTO_GENERATOR", sequenceName="SEQ_ID_MEDICAMENTO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MEDICAMENTOS_IDMEDICAMENTO_GENERATOR")
	@Column(name="ID_MEDICAMENTO")
	private long idMedicamento;

	private BigDecimal costo;

	private String dosis;

	@Temporal(TemporalType.DATE)
	@Column(name="FEC_DESDE")
	private Date fecDesde;

	@Temporal(TemporalType.DATE)
	@Column(name="FEC_HASTA")
	private Date fecHasta;

	private String nombre;

	//bi-directional many-to-one association to Dosi
	@OneToMany(mappedBy="medicamento")
	private List<Dosi> dosisSet;

	public Medicamento() {
	}

	public long getIdMedicamento() {
		return this.idMedicamento;
	}

	public void setIdMedicamento(long idMedicamento) {
		this.idMedicamento = idMedicamento;
	}

	public BigDecimal getCosto() {
		return this.costo;
	}

	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}

	public String getDosis() {
		return this.dosis;
	}

	public void setDosis(String dosis) {
		this.dosis = dosis;
	}

	public Date getFecDesde() {
		return this.fecDesde;
	}

	public void setFecDesde(Date fecDesde) {
		this.fecDesde = fecDesde;
	}

	public Date getFecHasta() {
		return this.fecHasta;
	}

	public void setFecHasta(Date fecHasta) {
		this.fecHasta = fecHasta;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Dosi> getDosisSet() {
		return this.dosisSet;
	}

	public void setDosisSet(List<Dosi> dosisSet) {
		this.dosisSet = dosisSet;
	}

	public Dosi addDosisSet(Dosi dosisSet) {
		getDosisSet().add(dosisSet);
		dosisSet.setMedicamento(this);

		return dosisSet;
	}

	public Dosi removeDosisSet(Dosi dosisSet) {
		getDosisSet().remove(dosisSet);
		dosisSet.setMedicamento(null);

		return dosisSet;
	}

}