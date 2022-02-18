package com.bean;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.entities.Alimento;
import com.entities.Consumo;
import com.entities.Unidade;
import com.enums.TipoUnidad;
import com.exception.ServiciosException;
import com.servicios.AlimentoBean;
import com.servicios.UnidadBean;

@ManagedBean(name="alimento")
@SessionScoped

public class AlimentosBean {

	private static AlimentosBean repository = new AlimentosBean();

	public static AlimentosBean getInstance() {
		return repository;
	}


	@EJB
	private AlimentoBean alimentoBean;

			@EJB
			private UnidadBean unidadBean;


	private long idAlimento;
	private BigDecimal cantidad;
	private BigDecimal costoUnidad;
	private String nombre;
	private String idUnidad;
	

	


	private List<Consumo> consumos;
	private List<Unidade> unidade;

	private List<Alimento> alimentos;
	private List<Alimento> alimentosL;

	

	private int i;

	public long getIdAlimento() {
		return idAlimento;
	}
	public void setIdAlimento(long idAlimento) {
		this.idAlimento = idAlimento;
	}
	public BigDecimal getCantidad() {
		return cantidad;
	}
	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}
	public BigDecimal getCostoUnidad() {
		return costoUnidad;
	}
	public void setCostoUnidad(BigDecimal costoUnidad) {
		this.costoUnidad = costoUnidad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Consumo> getConsumos() {
		return consumos;
	}
	public void setConsumos(List<Consumo> consumos) {
		this.consumos = consumos;
	}

	public List<Unidade> getUnidade() {
		return unidade;
	}
	public void setUnidade(List<Unidade> unidade) {
		this.unidade = unidade;
	}

	public String getIdUnidad() {
		return idUnidad;
	}
	public void setIdUnidad(String idUnidad) {
		this.idUnidad = idUnidad;
	}
	
	


	public String ingresarAlimento() throws ServiciosException {


		FacesContext context = FacesContext.getCurrentInstance();

		long id_Unidad=0;
		if(idUnidad.equals("KG")) {
			id_Unidad=1;
		}else if(idUnidad.equals("L")){
			id_Unidad=2;

		}


		Unidade uni = unidadBean.obtenerUnidad(id_Unidad);

		Alimento ali = new Alimento(0L,nombre,costoUnidad,cantidad,uni);

		try {

			alimentoBean.ingresarAlimento(ali);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingreso Correcto", "Alimento agregado correctamente"));
		} catch (ServiciosException e) {
			// TODO Auto-generated catch block
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error al ingresar alimento"));
		}

		return"mostrar";
	}


	public void actualizarAlimento() {
		FacesContext context = FacesContext.getCurrentInstance();

		try {

			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Alimento modificado correctamente"));
			alimentoBean.actualizarAlimento(nombre, costoUnidad, cantidad);

		} catch (ServiciosException e) {
			// TODO Auto-generated catch block
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error al modificar alimento"));
		}
	}

	public List<Alimento> getAlimento(){
		try {
			alimentos = alimentoBean.obtenerTodosAlimentos();
		} catch (ServiciosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alimentos;
	}

	public String[] getAlimentosL() throws ServiciosException{
		alimentosL = alimentoBean.obtenerTodosAlimentos();
		String[] arrayidAlimentos= new String[alimentosL.size()];
		i=0;
		for(Alimento a : alimentosL) {
			arrayidAlimentos[i]=a.getNombre();
			i++;
		}
		return arrayidAlimentos;
	}


}
