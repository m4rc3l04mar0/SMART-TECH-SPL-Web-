package com.servicios;



import javax.ejb.EJB;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import com.entities.Peso;
import com.exception.ServiciosException;

@Path("pesos")
public class PesosRest {
	
	
	@EJB
	private PesoBean pesoBean;
	
	
	@POST
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void addPesoNew(Peso peso) throws ServiciosException{
		try {
			pesoBean.ingresarPeso(peso);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ServiciosException("Error en el Servicio REST"+e.getMessage());
		}
		
		
	}
	
	/*
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Peso> getPesos()  throws ServiciosException{
		try {
			return pesoBean.
		} catch (Exception e) {
			// TODO: handle exception
			throw new ServiciosException(" Error en el Servicio REST "+e.getMessage());
		}
		
	}*/

}
