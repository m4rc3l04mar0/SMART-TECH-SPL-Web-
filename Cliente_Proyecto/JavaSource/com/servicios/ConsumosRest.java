package com.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.entities.Consumo;
import com.exception.ServiciosException;

@Path("consumos")
public class ConsumosRest {
	
	
	@EJB
	private ConsumoBean consumoBean;
	
	
	@POST
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void addConsumoNew(Consumo consumo) throws ServiciosException{
		try {
			consumoBean.ingresarConsumo(consumo);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ServiciosException("Error en el Servicio REST"+e.getMessage());
		}
		
		
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Consumo> getConsumos()  throws ServiciosException{
		try {
			return consumoBean.obtenerTodos();
		} catch (Exception e) {
			// TODO: handle exception
			throw new ServiciosException(" Error en el Servicio REST "+e.getMessage());
		}
		
	}
	

}
