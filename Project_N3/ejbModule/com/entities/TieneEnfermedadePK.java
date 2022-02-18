package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * The primary key class for the TIENE_ENFERMEDADES database table.
 * 
 */
@Embeddable
public class TieneEnfermedadePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ID_ENFERMEDAD", insertable=false, updatable=false)
	private long idEnfermedad;

	@Column(name="ID_TERNERA", insertable=false, updatable=false)
	private long idTernera;

	@Temporal(TemporalType.DATE)
	@Column(name="FEC_DESDE")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date fecDesde;

	public TieneEnfermedadePK() {
	}
	public long getIdEnfermedad() {
		return this.idEnfermedad;
	}
	public void setIdEnfermedad(long idEnfermedad) {
		this.idEnfermedad = idEnfermedad;
	}
	public long getIdTernera() {
		return this.idTernera;
	}
	public void setIdTernera(long idTernera) {
		this.idTernera = idTernera;
	}
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date getFecDesde() {
		return this.fecDesde;
	}
	public void setFecDesde(Date fecDesde) {
		this.fecDesde = fecDesde;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TieneEnfermedadePK)) {
			return false;
		}
		TieneEnfermedadePK castOther = (TieneEnfermedadePK)other;
		return 
			(this.idEnfermedad == castOther.idEnfermedad)
			&& (this.idTernera == castOther.idTernera)
			&& this.fecDesde.equals(castOther.fecDesde);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.idEnfermedad ^ (this.idEnfermedad >>> 32)));
		hash = hash * prime + ((int) (this.idTernera ^ (this.idTernera >>> 32)));
		hash = hash * prime + this.fecDesde.hashCode();
		
		return hash;
	}
}