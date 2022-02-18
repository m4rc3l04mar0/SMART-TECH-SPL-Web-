package com.bean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.enums.TipoUnidad;
import com.servicios.UnidadBean;

@ManagedBean(name="unidad")
@SessionScoped

public class UnidadesBean {
	
	private static UnidadesBean repository = new UnidadesBean();
	
	public static UnidadesBean getInstance() {
		return repository;
	}
	
	
	@EJB
	private UnidadBean unidadBean;
	
	
	private long idUnidad;
	private TipoUnidad unidad;

	public long getIdUnidad() {
		return idUnidad;
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
	
	public TipoUnidad[] getTipoUnidades() {
		return TipoUnidad.values();
	}
	
	
}
