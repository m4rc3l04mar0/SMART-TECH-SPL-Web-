package com.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.persistence.PersistenceException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.entities.Alimento;
import com.exception.ServiciosException;

@Path("alimentos")
public class AlimentosRest {
	
	@EJB
	private AlimentoBean alimentoBean;
	
	//TRAE CONSUMOS=NULL Y UNIDADE=NULL
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Alimento> getAlimentos() throws ServiciosException{
		
		try {
			return alimentoBean.obtenerTodosAlimentos();
		} catch (PersistenceException e) {
			// TODO: handle exception
			throw new ServiciosException("error en el Servicio REST"+e.getMessage());
			
		}
		
	
	}
	
	
	
	@GET
	@Path("/id/{idAlimento}")
	@Produces(MediaType.APPLICATION_JSON)
	public Alimento getAlimentoById(@PathParam("idAlimento")Long idAlimento) throws ServiciosException{
		
		
		try {
			return alimentoBean.buscarAlimentoPorId(idAlimento);
		} catch (PersistenceException e) {
			// TODO: handle exception
			throw new ServiciosException("error en el Servicio Rest"+e.getMessage());
		}
	}
	
	
	
	//	NO FUNCIONA
	@POST
	@Path("/agregar/")
	@Produces(MediaType.APPLICATION_JSON)
	public void addAlimento(Alimento alimento) throws ServiciosException{
		
				
		try {
			alimentoBean.ingresarAlimento(alimento);
		} catch (PersistenceException e) {
			// TODO: handle exception
			throw new ServiciosException("error en el Servicio Rest"+e.getMessage());
		}
	}
	
	
	
	@PUT
	@Path("/modificar/")
	@Produces(MediaType.APPLICATION_JSON)
	public void modificarAlimento(Alimento alimento) throws ServiciosException{
		try {		
			// TODO: handle exception
			alimentoBean.actualizarAlimento(alimento.getNombre(), alimento.getCostoUnidad(), alimento.getCantidad());
		} catch (PersistenceException e) {
			// TODO: handle exception
			throw new ServiciosException("error en el Servicio Rest"+e.getMessage());
		}
		}
		
	}


