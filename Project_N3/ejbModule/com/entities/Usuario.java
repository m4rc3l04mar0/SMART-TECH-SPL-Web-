package com.entities;

import java.io.Serializable;
import javax.persistence.*;


import com.enums.Perfil;


/**
 * The persistent class for the USUARIOS database table.
 * 
 */


@Entity
@Table(name="USUARIOS")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;




	@Id
	@SequenceGenerator(name="USUARIOS_IDUSUARIO_GENERATOR", sequenceName="SEQ_ID_USUARIO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USUARIOS_IDUSUARIO_GENERATOR")
	@Column(name="ID_USUARIO")

	private long idUsuario;

	private String apellido;


	private String contraseña;

	private String nombre;

	@Enumerated(EnumType.STRING)
	private Perfil perfil;



	private String usuario;

	//bi-directional many-to-one association to Guachera
	@ManyToOne
	@JoinColumn(name="ID_GUACHERA")
	@Transient
	private Guachera guachera;

	public Usuario() {
	}



	public Usuario(long id, String usuario, String nombre, String apellido, Perfil perfil, String password) {
		// TODO Auto-generated constructor stub
		this.idUsuario = id;
		this.usuario = usuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.perfil = perfil;
		this.contraseña = password;
	}


	public long getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getContraseña() {
		return this.contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Guachera getGuachera() {
		return this.guachera;
	}

	public void setGuachera(Guachera guachera) {
		this.guachera = guachera;
	}

	/*
	public String getIdGuachera() {
		return getGuachera().getIdGuachera();
	}
	*/


}