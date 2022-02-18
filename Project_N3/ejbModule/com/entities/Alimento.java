package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the ALIMENTOS database table.
 * 
 */
@Entity
@Table(name="ALIMENTOS")
@NamedQuery(name="Alimento.findAll", query="SELECT a FROM Alimento a")
public class Alimento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ALIMENTOS_IDALIMENTO_GENERATOR", sequenceName="SEQ_ID_ALIMENTO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ALIMENTOS_IDALIMENTO_GENERATOR")
	@Column(name="ID_ALIMENTO")
	private long idAlimento;

	private BigDecimal cantidad;

	@Column(name="COSTO_UNIDAD")
	private BigDecimal costoUnidad;

	private String nombre;

	/*
	 * //bi-directional many-to-one association to Consumo
	 */
	
	//@OneToMany(mappedBy="alimento" )
	@Transient
	private List<Consumo> consumos;

	//bi-directional many-to-one association to Unidade
	@ManyToOne
	@JoinColumn(name="ID_UNIDAD")
	
	private Unidade unidade;

	public Alimento() {
	}




	public Alimento(long idAlimento, String nombre, BigDecimal costoUnidad, BigDecimal cantidad, Unidade unidade) {
		// TODO Auto-generated constructor stub
		
		this.idAlimento = idAlimento;
		this.nombre = nombre;
		this.costoUnidad = costoUnidad;
		this.cantidad = cantidad;
		this.unidade = unidade;
	}




	



	public long getIdAlimento() {
		return this.idAlimento;
	}

	public void setIdAlimento(long idAlimento) {
		this.idAlimento = idAlimento;
	}

	public BigDecimal getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getCostoUnidad() {
		return this.costoUnidad;
	}

	public void setCostoUnidad(BigDecimal costoUnidad) {
		this.costoUnidad = costoUnidad;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	public List<Consumo> getConsumos() {
		return this.consumos;
	}

	public void setConsumos(List<Consumo> consumos) {
		this.consumos = consumos;
	}

	public Consumo addConsumo(Consumo consumo) {
		getConsumos().add(consumo);
		consumo.setAlimento(this);

		return consumo;
	}

	public Consumo removeConsumo(Consumo consumo) {
		getConsumos().remove(consumo);
		consumo.setAlimento(null);

		return consumo;
	}

	public Unidade getUnidade() {
		return this.unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

}