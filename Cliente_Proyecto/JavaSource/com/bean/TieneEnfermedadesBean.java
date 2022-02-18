package com.bean;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


import com.entities.Enfermedade;
import com.entities.Ternera;
import com.entities.TieneEnfermedade;
import com.enums.NombreEnfermedad;


@ManagedBean(name="TerneraTieneEnfermedad")
@SessionScoped

@Stateless
@LocalBean
public class TieneEnfermedadesBean {
	@PersistenceContext
	private EntityManager em;


	public List<TieneEnfermedade> obtenerTodosE(){
		TypedQuery<TieneEnfermedade> query = em.createQuery("SELECT d FROM TieneEnfermedade d " , TieneEnfermedade.class);
		return query.getResultList();
	}


	public NombreEnfermedad buscarNombreEnfermedadPorId (long idEnfermedad) {
		Enfermedade enfermedad = em.find(Enfermedade.class, idEnfermedad);
		return enfermedad.getNombre();
	}

	public Date buscaFecNacTernera(long idTernera)  {
		Ternera ternera = em.find(Ternera.class, idTernera);
		return ternera.getFecNacimiento();
	}


	public long calularDias(Date FechaNac, Date FechaDesde)  {
		/*
		long diasNace = 0;
		final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;
		diasNace = -1*((FechaNac.getTime()-FechaDesde.getTime())/MILLSECS_PER_DAY);
		System.out.println((FechaNac.getTime())/MILLSECS_PER_DAY);
		System.out.println((FechaDesde.getTime())/MILLSECS_PER_DAY);
		
		return diasNace;
		*/
		
		long diasNace = ((FechaNac.getTime()-FechaDesde.getTime())/86400000);
		return diasNace;
	
	}

}
