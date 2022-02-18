package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * The primary key class for the CONSUMOS database table.
 * 
 */
@Embeddable
public class ConsumoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	
	@Column(name="ID_ALIMENTO",  insertable=false, updatable=false)
	private long idAlimento;

	@Column(name="ID_TERNERA", insertable=false, updatable=false)
	private long idTernera;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date fecha;

	public ConsumoPK() {
		super();
	}
	public long getIdAlimento() {
		return this.idAlimento;
	}
	public void setIdAlimento(long idAlimento) {
		this.idAlimento = idAlimento;
	}
	public long getIdTernera() {
		return this.idTernera;
	}
	public void setIdTernera(long idTernera) {
		this.idTernera = idTernera;
	}
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date getFecha() {
		return this.fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ConsumoPK)) {
			return false;
		}
		ConsumoPK castOther = (ConsumoPK)other;
		return 
			(this.idAlimento == castOther.idAlimento)
			&& (this.idTernera == castOther.idTernera)
			&& this.fecha.equals(castOther.fecha);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.idAlimento ^ (this.idAlimento >>> 32)));
		hash = hash * prime + ((int) (this.idTernera ^ (this.idTernera >>> 32)));
		hash = hash * prime + this.fecha.hashCode();
		
		return hash;
	}
}