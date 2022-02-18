package com.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;



import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.entities.Alimento;
import com.entities.Consumo;
import com.entities.ConsumoPK;
import com.entities.Ternera;
import com.exception.ServiciosException;
import com.servicios.AlimentoBean;
import com.servicios.ConsumoBean;
import com.servicios.TerneraBean;




@ManagedBean(name="consumo")
@SessionScoped


@Stateless
public class ConsumosBean {

	@PersistenceContext
	private EntityManager em;

	private static ConsumosBean repository = new ConsumosBean();

	public static ConsumosBean getInstance() {
		return repository;

	}

	@EJB
	private ConsumoBean consumoBean;
	@EJB
	private TerneraBean terneraBean;
	@EJB
	private AlimentoBean alimentoBean;

	private long idConsumo;

	private Date fechaConsumo;
	private BigDecimal cantidad;
	private long idAlimento;
	private long idTernera;

	//private List<Consumo> terneras;
	private List<Ternera> ternerasL;
	private List<Alimento> alimentosL;
	private int i=0;




	public ConsumoBean getConsumoBean() {
		return consumoBean;
	}
	public void setConsumoBean(ConsumoBean consumoBean) {
		this.consumoBean = consumoBean;
	}
	public long getIdConsumo() {
		return idConsumo;
	}
	public void setIdConsumo(long idConsumo) {
		this.idConsumo = idConsumo;
	}
	
	public Date getFechaConsumo() {
		return fechaConsumo;
	}
	public void setFechaConsumo(Date fechaConsumo) {
		this.fechaConsumo = fechaConsumo;
	}
	public BigDecimal getCantidad() {
		return cantidad;
	}
	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public long getIdAlimento() {
		return idAlimento;
	}
	public void setIdAlimento(long idAlimento) {
		this.idAlimento = idAlimento;
	}
	public long getIdTernera() {
		return idTernera;
	}
	public void setIdTernera(long idTernera) {
		this.idTernera = idTernera;
	}


	public long[] getTernerasL() throws ServiciosException{
		ternerasL = terneraBean.obtenerTodasTerneras();
		long[] arrayidTerneras = new long[ternerasL.size()];
		i=0;
		for(Ternera t : ternerasL) {
			arrayidTerneras[i]=t.getIdTernera();
			i++;
		}
		return arrayidTerneras;
	}



	public long[] getAlimentosL() throws ServiciosException{
		alimentosL = alimentoBean.obtenerTodosAlimentos();
		long[] arrayidAlimentos= new long[alimentosL.size()];
		i=0;
		for(Alimento a : alimentosL) {
			arrayidAlimentos[i]=a.getIdAlimento();
			i++;
		}
		return arrayidAlimentos;
	}



	public BigDecimal buscarCostoAlimentoId (long idAlimento) {
		Alimento alimentos = em.find(Alimento.class, idAlimento);
		return alimentos.getCostoUnidad();
	}


	public String ingresarConsumo() throws ServiciosException {

	FacesContext context = FacesContext.getCurrentInstance();


		Ternera ter = terneraBean.obtenerPorId(idTernera);

		Alimento ali = alimentoBean.buscarAlimentoPorId(idAlimento);

		
		Consumo consumo = new Consumo();
		
		consumo.setAlimento(ali);
		consumo.setTernera(ter);
		consumo.setCantidad(cantidad);
		
		ConsumoPK consumopk = new ConsumoPK();
		consumopk.setFecha(fechaConsumo);
		consumopk.setIdAlimento(idAlimento);
		consumopk.setIdTernera(idTernera);
		
		consumo.setId(consumopk);
		
		
		
	
		

		
		
		try {
			
			consumoBean.ingresarConsumo(consumo);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingreso Correcto", "Consumo agregado correctamente"));
			
			
			ali.setCantidad(modificarStock(ali, cantidad));
			alimentoBean.editarAlimento(ali);
			
			
		} catch (ServiciosException e) {
			// TODO Auto-generated catch block
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error al ingresar consumo"));
		}

			return "mostrar_c";
	}
	
	
	
	
	public BigDecimal modificarStock(Alimento alimento, BigDecimal cantidad) {
		
		BigDecimal cantidadConsumida = cantidad;
		BigDecimal cantidadInicial = alimento.getCantidad();
		BigDecimal cantidadFinal = cantidadInicial.subtract(cantidadConsumida);
		return cantidadFinal;
	}
	
	/*
	public List<Consumo> obtenerTerneraPorId(long idTernera){
		TypedQuery<Consumo> query = em.createQuery("SELECT t FROM Consumo t WHERE t.ConsumoPK.idTernera LIKE :idTernera", Consumo.class).setParameter("idTernera", idTernera);
		return query.getResultList();
	}
*/

	/*
	public double acumulaCostos(long idTernera) {
		double total;
		total=0L;
		terneras = obtenerTerneraPorId(idTernera);
		for(Consumo t : terneras) {
			total = total + buscarCostoAlimentoId(t.getId().getIdAlimento()).doubleValue()* t.getCantidad().doubleValue();
		}
		return total;
	}
	 */
}
