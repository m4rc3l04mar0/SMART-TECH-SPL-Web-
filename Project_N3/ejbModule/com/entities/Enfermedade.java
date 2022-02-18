package com.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.enums.NombreEnfermedad;

import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the ENFERMEDADES database table.
 * 
 */
@Entity
@Table(name="ENFERMEDADES")
@NamedQuery(name="Enfermedade.findAll", query="SELECT e FROM Enfermedade e")
public class Enfermedade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ENFERMEDADES_IDENFERMEDAD_GENERATOR", sequenceName="SEQ_ID_ENFERMEDAD")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ENFERMEDADES_IDENFERMEDAD_GENERATOR")
	@Column(name="ID_ENFERMEDAD")
	private long idEnfermedad;

	@Column(name="GRADO_GRAVEDAD")
	private BigDecimal gradoGravedad;
	
	@Enumerated(EnumType.STRING)
	private NombreEnfermedad nombre;

	//bi-directional many-to-one association to TieneEnfermedade
	@OneToMany(mappedBy="enfermedade")
	@Transient
	private List<TieneEnfermedade> tieneEnfermedades;

	public Enfermedade() {
	}

	public long getIdEnfermedad() {
		return this.idEnfermedad;
	}

	public void setIdEnfermedad(long idEnfermedad) {
		this.idEnfermedad = idEnfermedad;
	}

	public BigDecimal getGradoGravedad() {
		return this.gradoGravedad;
	}

	public void setGradoGravedad(BigDecimal gradoGravedad) {
		this.gradoGravedad = gradoGravedad;
	}

	

	public NombreEnfermedad getNombre() {
		return nombre;
	}

	public void setNombre(NombreEnfermedad nombre) {
		this.nombre = nombre;
	}

	public List<TieneEnfermedade> getTieneEnfermedades() {
		return this.tieneEnfermedades;
	}

	public void setTieneEnfermedades(List<TieneEnfermedade> tieneEnfermedades) {
		this.tieneEnfermedades = tieneEnfermedades;
	}

	public TieneEnfermedade addTieneEnfermedade(TieneEnfermedade tieneEnfermedade) {
		getTieneEnfermedades().add(tieneEnfermedade);
		tieneEnfermedade.setEnfermedade(this);

		return tieneEnfermedade;
	}

	public TieneEnfermedade removeTieneEnfermedade(TieneEnfermedade tieneEnfermedade) {
		getTieneEnfermedades().remove(tieneEnfermedade);
		tieneEnfermedade.setEnfermedade(null);

		return tieneEnfermedade;
	}

}