package com.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.persistence.PersistenceException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.exception.ServiciosException;
import com.bean.UsuariosBean;
import com.entities.Usuario;
import com.enums.Perfil;


@Path("usuarios")
public class UsuariosRest {

	@EJB
	private UsuarioBean usuarioBean;
	
	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> getUsuarios() throws ServiciosException{

		try {
			return usuarioBean.obtenerTodosUsuarios();
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			throw new ServiciosException("error en el Servicio REST"+e.getMessage());
		}
	}


	@GET
	@Path("/id/{idUsuario}")
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario getUsuarioById(@PathParam("idUsuario")Long idUsuario) throws ServiciosException{
		try {
			return usuarioBean.buscarUsuarioPorId(idUsuario);
		} catch (PersistenceException e) {
			// TODO: handle exception
			throw new ServiciosException("error en el Servicio Rest"+e.getMessage());
		}
		
	}


	
	@PUT
	@Path("/actualizar/{idUsuario}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateUsuario(@PathParam("idUsuario")Long idUsuario, String contrasenia) throws ServiciosException{
		try {
			usuarioBean.actualizar(idUsuario,  contrasenia);
		} catch (PersistenceException e) {
			// TODO: handle exception
			throw new ServiciosException("error en el Servicio Rest"+e.getMessage());
		}
		
		
	}
	
	
		
	@POST
	@Path("/agregar/")
	@Produces(MediaType.APPLICATION_JSON)
	public void addUsuario(Usuario usuario) throws ServiciosException{
		try {
			usuarioBean.ingresarUsuario(usuario);
		} catch (PersistenceException e) {
			// TODO: handle exception
			throw new ServiciosException("error en el Servicio Rest"+e.getMessage());
		}
		
		
	}
	
	
	
	@PUT
	@Path("/modificar/{idUsuario}")
	@Produces(MediaType.APPLICATION_JSON)
	public void modificarUsuario(Usuario usuario) throws ServiciosException{
		try {
			usuarioBean.actualizar(usuario.getIdUsuario(), usuario.getContraseña());
		} catch (PersistenceException e) {
			// TODO: handle exception
			throw new ServiciosException("error en el Servicio Rest"+e.getMessage());
		}
		
	}
	
	
	
	@DELETE
	@Path("/eliminar/{idUsuario}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delUsuarioById(@PathParam("idUsuario")Long idUsuario) throws ServiciosException{
		try {
			usuarioBean.eliminarUsuario(idUsuario);
		} catch (PersistenceException e) {
			// TODO: handle exception
			throw new ServiciosException("error en el Servicio Rest"+e.getMessage());
		}
		
		
	}
	
	@GET
    @Path("/login/{USUARIO}/{CONTRASENIA}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsuariosByUsuario(@PathParam("USUARIO")String usuario,@PathParam("CONTRASENIA") String contrasenia) throws ServiciosException {
    	try {
			return usuarioBean.obtenerUsuarios(usuario, contrasenia);
    	}catch (PersistenceException e){
			throw new ServiciosException("error en el Servicio REST"+e.getMessage());
    	}
    }
	
	

}

