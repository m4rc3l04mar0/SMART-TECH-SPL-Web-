package com.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.persistence.PersistenceException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.entities.TieneEnfermedade;
import com.exception.ServiciosException;

@Path("eventosClinicos")
public class TieneEnfermedadesRest {
	
	@EJB
	private TieneEnfermedadBean tieneEnfermedad;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<TieneEnfermedade> getEnferfemdades() throws ServiciosException{
		
		try {
			return tieneEnfermedad.obtenerTodosE();
		} catch (PersistenceException e) {
			// TODO: handle exception
			throw new ServiciosException("error en el Servicio REST"+e.getMessage());
		} 
		
		
	}

}
