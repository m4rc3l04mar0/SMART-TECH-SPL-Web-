package com.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.entities.Peso;
import com.entities.Ternera;
import com.entities.Usuario;
import com.enums.TipoRegistro;
import com.exception.ServiciosException;
import com.servicios.ConsumoBean;
import com.servicios.PesoBean;
import com.servicios.TerneraBean;

@ManagedBean(name="peso")
@SessionScoped

public class PesosBean {
	
private static PesosBean repository = new PesosBean();
	
	public static PesosBean getInstance() {
		return repository;

}
	
	@EJB
	private PesoBean pesoBean;
	@EJB
	private TerneraBean terneraBean;
	
	
	private long idPeso;
	private TipoRegistro tipoRegistro;
	private Date fechaPeso;
	private BigDecimal peso;
	private Ternera ternera;
	private long idTernera;

	public PesoBean getPesoBean() {
		return pesoBean;
	}
	public void setPesoBean(PesoBean pesoBean) {
		this.pesoBean = pesoBean;
	}
	public long getIdPeso() {
		return idPeso;
	}
	public void setIdPeso(long idPeso) {
		this.idPeso = idPeso;
	}
	public TipoRegistro getTipoRegistro() {
		return tipoRegistro;
	}
	public void setTipoRegistro(TipoRegistro tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}
	public Date getFechaPeso() {
		return fechaPeso;
	}
	public void setFechaPeso(Date fechaPeso) {
		this.fechaPeso = fechaPeso;
	}
	public BigDecimal getPeso() {
		return peso;
	}
	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}
	public Ternera getTernera() {
		return ternera;
	}
	public void setTernera(Ternera ternera) {
		this.ternera = ternera;
	}
	
	
	public TipoRegistro[] getTipoRegistros() {
		return TipoRegistro.values();
	}
	
	public long getIdTernera() {
		return idTernera;
	}
	public void setIdTernera(long idTernera) {
		this.idTernera = idTernera;
	}
	
	
	public String ingresarPeso() throws ServiciosException{
		
		
		Ternera tern = terneraBean.obtenerPorId(idTernera);

		Peso pezo=new Peso();
		pezo.setFecha(fechaPeso);
		pezo.setTipoRegistro(tipoRegistro);
		pezo.setTernera(tern);
		pezo.setPeso(peso);
		
		//BigDecimal ganancia = calcularPeso(idTernera,peso);
		
			try {
				pesoBean.ingresarPeso(pezo);
			} catch (ServiciosException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "mostrar";
		
	}
	
	/*
	public BigDecimal calcularPeso(long idTernera, BigDecimal peso) throws ServiciosException {
		
		List<Peso> pesos = pesoBean.obtenerPorIdTernera(idTernera);
		
		Ternera ternera = terneraBean.obtenerPorId(idTernera);
		
		BigDecimal ganancia;
		
		if(pesos.isEmpty()) {
			ganancia = peso.subtract(ternera.getPesoNacimiento());
		}else {
			ganancia= peso.subtract(pesos.get(pesos.size()-1).getPeso());
		}
		return ganancia;
	}
	*/
}
