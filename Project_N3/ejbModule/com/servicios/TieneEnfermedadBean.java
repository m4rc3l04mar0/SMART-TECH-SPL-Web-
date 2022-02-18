package com.servicios;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Enfermedade;
import com.entities.TieneEnfermedade;
import com.enums.NombreEnfermedad;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class TieneEnfermedadBean
 */
@Stateless
@LocalBean
public class TieneEnfermedadBean {

	@PersistenceContext
	private EntityManager em;


	public List<TieneEnfermedade> obtenerTodosE() throws ServiciosException {
		List<TieneEnfermedade> tieneenfermedade=null;
		try {
			TypedQuery<TieneEnfermedade> query = em.createQuery("SELECT t FROM TieneEnfermedade t " , TieneEnfermedade.class);
			tieneenfermedade = query.getResultList();
		} catch (PersistenceException e) {
			throw new ServiciosException("Error al obtener todas las terneras con enfermedad ");
		}
	  return tieneenfermedade;
	}


	public NombreEnfermedad buscarNombreEnfermedadPorId (long idEnfermedad) throws ServiciosException {
		try {
			Enfermedade enfermedad = em.find(Enfermedade.class, idEnfermedad);
			return enfermedad.getNombre();
		} catch (PersistenceException e) {
			throw new ServiciosException("Error al buscr el nombre de la enfermedad ");
		}
	}



        

}