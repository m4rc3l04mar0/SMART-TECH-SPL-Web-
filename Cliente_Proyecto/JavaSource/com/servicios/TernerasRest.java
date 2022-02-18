package com.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.persistence.PersistenceException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import com.entities.Ternera;
import com.exception.ServiciosException;

@Path("terneras")
public class TernerasRest {
	
	@EJB
	private TerneraBean terneraBean;
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Ternera> getTerneras() throws ServiciosException{
		
		try {
			return terneraBean.obtenerTodasTerneras();
		} catch (PersistenceException e) {
			// TODO: handle exception
			throw new ServiciosException("error en el Servicio REST"+e.getMessage());
			
		}
		
	
	}

}
