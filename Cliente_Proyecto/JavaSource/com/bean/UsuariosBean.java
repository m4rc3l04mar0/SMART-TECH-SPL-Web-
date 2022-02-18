package com.bean;




import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.PersistenceException;

import com.entities.Usuario;
import com.enums.Perfil;
import com.exception.ServiciosException;
import com.servicios.UsuarioBean;






@ManagedBean(name="usuario")
@SessionScoped


public class UsuariosBean {
	
	
	private static UsuariosBean repository = new UsuariosBean();
	
	public static UsuariosBean getInstance(){
		return repository;
	}
	
	
	@EJB
	private UsuarioBean usuarioBean;
	
	private Long id;
    private String nombre;
	private String nombre1;
	private String nombre2;
	private String apellido;
	private String apellido1;
	private String apellido2;
	private String usuario;
	private String password;
	private Perfil perfil;
	
	
	private List<Usuario> usuariosL;
	private List<Usuario> usuarios;
	private Usuario usuarioPorId;
	
	private int i;
	
	
	public UsuarioBean getUsuarioBean() {
		return usuarioBean;
	}



	public void setUsuarioBean(UsuarioBean usuarioBean) {
		this.usuarioBean = usuarioBean;
	}


	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getNombre1() {
		return nombre1;
	}



	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}



	public String getNombre2() {
		return nombre2;
	}



	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}



	public String getApellido() {
		return apellido;
	}



	public void setApellido(String apellido) {
		this.apellido = apellido;
	}



	public String getApellido1() {
		return apellido1;
	}



	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}



	public String getApellido2() {
		return apellido2;
	}



	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}



	public String getUsuario() {
		return usuario;
	}



	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public Perfil getPerfil() {
		return perfil;
	}



	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	

	public Perfil[] getPerfiles(){
		return Perfil.values();
	}

	
	

	public String ingresarUsuario() throws ServiciosException{

		nombre=nombre1+" "+nombre2;
		apellido=apellido1+" "+apellido2;
		usuario=generarUsuario(nombre1,apellido1,apellido2);
		
		Usuario usu = new Usuario(0L, usuario,nombre,apellido,perfil,password);
			try {
				usuarioBean.ingresarUsuario(usu);
				
			} catch (PersistenceException e) {
				// TODO Auto-generated catch block
				throw new ServiciosException("Error al agregar un Usuario");
			}
			return "mostrar";
			
	}
	
	public String generarUsuario(String nombre1, String apellido1, String apellido2) {
		String usu="";
		
		
			usu=nombre1+"."+apellido1;
			
			List<Usuario> usuarios;
			try {
				usuarios = usuarioBean.obtenerTodosUsuarios();
				for(int i=0; i<usuarios.size();i++) {
					if(usuarios.get(i).getUsuario().equals(nombre1+"."+apellido1)) {
						usu=usu+"."+apellido2;
					}else if(usuarios.get(i).getUsuario().equals(nombre1+"."+apellido1+"."+apellido2)) {
						System.out.println("El usuario ya existe");
						usuario="";
						
					}
					
				}
			} catch (ServiciosException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
		return usu;
	}
	 public List<Usuario> getUsuarios() {
		  try {
			usuarios = usuarioBean.obtenerTodosUsuarios();
		} catch (ServiciosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return usuarios;
	  }
	 
	 
	 
	 public void eliminarUsuario() {
		 FacesContext context = FacesContext.getCurrentInstance();
		 try {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Usuario eliminado correctamente"));
			usuarioBean.eliminarUsuario(id);
			
		} catch (ServiciosException e) {
			// TODO: handle exception
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error al eliminar usuario"));
		}

	 
		 }
	 
	 public void actualizarUsuario() {
			FacesContext context = FacesContext.getCurrentInstance();
			try {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Transaccion exitosa", "Usuario modificado correctamente") );
				usuarioBean.actualizar(id, password);	
			} catch (ServiciosException e) {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error al modificar el  usuario"));
			}
		}
		
	 
	 public long[] getUsuariosL() throws ServiciosException{
			usuariosL = usuarioBean.obtenerTodosUsuarios();
			long[] arrayidUsuarios = new long[usuariosL.size()];
			i=0;
			for(Usuario u : usuariosL){
				arrayidUsuarios[i] = u.getIdUsuario();
				i++;
			}
			return arrayidUsuarios;     	
		}

	 public void cargaUsuarioPorId(long idUsuario) throws ServiciosException {
			usuarioPorId = usuarioBean.buscarUsuarioPorId(idUsuario);
		}

		public Usuario getUsuarioPorId() {
			return usuarioPorId;
		}

		public void setUsuarioPorId(Usuario usuarioPorId)  {
			this.usuarioPorId = usuarioPorId;
		}
		
		
				
		
		public String consultaUsuario() throws ServiciosException {
			String cadena="";
			
			
			if(usuario.equals("administrador")&&password.equals("admin12345")) {
				password="";
				return "/Usuarios/NuevoUsuario";
				
			}
			
			cadena=usuarioBean.obtenerUsuarios(usuario, password);
			if ( cadena == "" ){
				this.usuario="";
				return "errorLogin";
			}
			
			if(cadena.equals("ENCARGADO"))
			{
				perfil=Perfil.valueOf("ENCARGADO");
				nombre=usuario;
				password="";
				return "/Alimentos/NuevoAlimento";
			}else
		
			{
				perfil=Perfil.valueOf("PERSONAL");
				nombre=usuario;
				password="";
				return "/Alimentos/NuevoAlimento";
			}
			
			
		}
}


