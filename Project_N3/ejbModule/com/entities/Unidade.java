package com.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.enums.TipoUnidad;

import java.util.List;


/**
 * The persistent class for the UNIDADES database table.
 * 
 */
@Entity
@Table(name="UNIDADES")
@NamedQuery(name="Unidade.findAll", query="SELECT u FROM Unidade u")
public class Unidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_UNIDAD")
	private long idUnidad;
	
	@Enumerated(EnumType.STRING)
	private TipoUnidad unidad;

	//bi-directional many-to-one association to Alimento
	@OneToMany(mappedBy="unidade")
	@Transient
	private List<Alimento> alimentos;

	public Unidade() {
	}

	public long getIdUnidad() {
		return this.idUnidad;
	}

	public void setIdUnidad(long idUnidad) {
		this.idUnidad = idUnidad;
	}

	

	public TipoUnidad getUnidad() {
		return unidad;
	}

	public void setUnidad(TipoUnidad unidad) {
		this.unidad = unidad;
	}

	public List<Alimento> getAlimentos() {
		return this.alimentos;
	}

	public void setAlimentos(List<Alimento> alimentos) {
		this.alimentos = alimentos;
	}

	public Alimento addAlimento(Alimento alimento) {
		getAlimentos().add(alimento);
		alimento.setUnidade(this);

		return alimento;
	}

	public Alimento removeAlimento(Alimento alimento) {
		getAlimentos().remove(alimento);
		alimento.setUnidade(null);

		return alimento;
	}

}