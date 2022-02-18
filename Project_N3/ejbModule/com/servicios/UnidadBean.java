package com.servicios;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;


import com.entities.Unidade;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class UnidadBean
 */
@Stateless
@LocalBean
public class UnidadBean {
	
	@PersistenceContext
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public UnidadBean() {
        // TODO Auto-generated constructor stub
    }

    public Unidade obtenerUnidad(Long idUnidad) throws ServiciosException {
    	Unidade u = null;
    	if(idUnidad != null) {
    	try {
    		TypedQuery<Unidade> query = em.createQuery("SELECT u FROM Unidade u WHERE u.idUnidad  LIKE :idUnidad",Unidade.class).setParameter("idUnidad",idUnidad);
			u = query.getSingleResult();
		} catch (PersistenceException e) {
			// TODO: handle exception
			throw new ServiciosException("Ha ocurrido un error al obtener la Unidad");
		}
    	
    	}
    	return u;
    }
    
   
}
