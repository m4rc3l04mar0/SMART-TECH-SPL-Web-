package com.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;


/**
 * The persistent class for the TIENE_ENFERMEDADES database table.
 * 
 */
@Entity
@Table(name="TIENE_ENFERMEDADES")
@NamedQuery(name="TieneEnfermedade.findAll", query="SELECT t FROM TieneEnfermedade t")
public class TieneEnfermedade implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TieneEnfermedadePK id;

	@Temporal(TemporalType.DATE)
	@Column(name="FEC_HASTA")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date fecHasta;

	private String observacion;

	//bi-directional many-to-one association to Enfermedade
	@ManyToOne(optional=false)
	@JoinColumn(name="ID_ENFERMEDAD", referencedColumnName="ID_ENFERMEDAD" , insertable=false, updatable=false)
	private Enfermedade enfermedade;

	//bi-directional many-to-one association to Ternera
	@ManyToOne(optional=false)
	@JoinColumn(name="ID_TERNERA", referencedColumnName="ID_TERNERA" , insertable=false, updatable=false)
	private Ternera ternera;

	public TieneEnfermedade() {
	}

	public TieneEnfermedadePK getId() {
		return this.id;
	}

	public void setId(TieneEnfermedadePK id) {
		this.id = id;
	}
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date getFecHasta() {
		return this.fecHasta;
	}

	public void setFecHasta(Date fecHasta) {
		this.fecHasta = fecHasta;
	}

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Enfermedade getEnfermedade() {
		return this.enfermedade;
	}

	public void setEnfermedade(Enfermedade enfermedade) {
		this.enfermedade = enfermedade;
	}

	public Ternera getTernera() {
		return this.ternera;
	}

	public void setTernera(Ternera ternera) {
		this.ternera = ternera;
	}
	
	
	
	

}