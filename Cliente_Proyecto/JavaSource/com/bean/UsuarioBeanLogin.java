package com.bean;


import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.ejb.UsuariosEJBBean;
import com.entities.Usuario;



@ManagedBean(name="usuarioLogin")
@SessionScoped

public class UsuarioBeanLogin {
	
private Usuario currentUser = new Usuario();
	
	@EJB
	private UsuariosEJBBean usuariosEJBBean;
	
	
	private long Id;
	private String usuario;
	private String password;
	
	
	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getNombre() {
		return usuario;
	}

	public void setNombre(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setCurrentUser(Usuario currentUser) {
		this.currentUser=currentUser;
	}
	
	public Usuario getCurrentUser() {
		return currentUser;
	}
	
	public String login() {
		Usuario result = usuariosEJBBean.login(currentUser.getUsuario(),currentUser.getContraseña());
		if(result!=null) {
			currentUser=result;
			return "nuevoUsuario";
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("username and password not found"));
		return null;
	}

}
