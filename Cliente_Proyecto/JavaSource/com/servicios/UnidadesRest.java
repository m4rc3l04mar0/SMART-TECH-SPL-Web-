package com.servicios;

import javax.ejb.EJB;
import javax.persistence.PersistenceException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import com.entities.Unidade;
import com.exception.ServiciosException;

@Path("unidades")
public class UnidadesRest {
	
	@EJB
	private UnidadBean unidadBean;
	
	
	@GET
	@Path("/id/{ID_UNIDAD}")
	@Produces(MediaType.APPLICATION_JSON)
	public Unidade getUnidadById(@PathParam("ID_UNIDAD")Long id_Unidad) throws ServiciosException{
		
		
		try {
			return unidadBean.obtenerUnidad(id_Unidad);
					} catch (PersistenceException e) {
			// TODO: handle exception
			throw new ServiciosException("error en el Servicio Rest"+e.getMessage());
		}
	}

}
