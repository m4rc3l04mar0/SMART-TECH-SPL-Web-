package com.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.enums.TipoRegistro;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the PESOS database table.
 * 
 */
@Entity
@Table(name="PESOS")
@NamedQuery(name="Peso.findAll", query="SELECT p FROM Peso p")
public class Peso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PESOS_IDPESO_GENERATOR", sequenceName="SEQ_ID_PESO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PESOS_IDPESO_GENERATOR")
	@Column(name="ID_PESO")
	private long idPeso;

	//@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date fecha;

	private BigDecimal peso;

	@Column(name="TIPO_REGISTRO")
	@Enumerated(EnumType.STRING)
	private TipoRegistro tipoRegistro;

	//bi-directional many-to-one association to Ternera
	@ManyToOne
	@JoinColumn(name="ID_TERNERA")
	private Ternera ternera;

	public Peso() {
	}

	public long getIdPeso() {
		return this.idPeso;
	}

	public void setIdPeso(long idPeso) {
		this.idPeso = idPeso;
	}
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getPeso() {
		return this.peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	

	public TipoRegistro getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(TipoRegistro tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	public Ternera getTernera() {
		return this.ternera;
	}

	public void setTernera(Ternera ternera) {
		this.ternera = ternera;
	}

}