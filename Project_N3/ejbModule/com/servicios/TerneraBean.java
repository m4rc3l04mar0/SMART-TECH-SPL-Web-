package com.servicios;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Ternera;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class TerneraBean
 */
@Stateless
@LocalBean
public class TerneraBean {
	
	@PersistenceContext
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public TerneraBean() {
        // TODO Auto-generated constructor stub
    }
    
    public Ternera obtenerPorCaravana(Long nro_Caravana) throws ServiciosException {
    	Ternera t = null;
    	
    	if(nro_Caravana != null) {
    		try {
    			TypedQuery<Ternera> query = em.createQuery("SELECT t FROM Ternera t WHERE t.nroCaravana LIKE :nro_caravana" ,Ternera.class).setParameter("nro_caravana",nro_Caravana);
				t = query.getSingleResult();
			} catch (PersistenceException e) {
				// TODO: handle exception
				throw new ServiciosException("Error en la consulta de Ternera por Nro");
			}
    	}
    	return t;
    	
    }
    public Ternera buscarTerneraPorId (long ID_TERNERA) throws ServiciosException  {
    	try {
    		Ternera ternera = em.find(Ternera.class, ID_TERNERA);
    		return ternera;
    	} catch (PersistenceException e) {
   		throw new ServiciosException("Error al buscar la ternera por el Id " );
   	}

    }
    
    public Ternera obtenerPorId(Long idTernera) throws ServiciosException {
    	Ternera t = null;
    	if(idTernera != null) {
    		
    		try {
    			TypedQuery<Ternera> query = em.createQuery("SELECT t FROM Ternera t WHERE t.idTernera LIKE :idTernera", Ternera.class).setParameter("idTernera", idTernera);
        		t = query.getSingleResult();
			} catch (PersistenceException e) {
				// TODO: handle exception
				throw new ServiciosException("Error en la consulta de Ternera");
			}
    		   		
    	}
    	return t;
    	
    }
    
    
    
    public List<Ternera> obtenerTodasTerneras() throws ServiciosException{
    	List<Ternera> terneras = null;
    	try {
			TypedQuery<Ternera> query = em.createQuery("SELECT t FROM Ternera t", Ternera.class);
			terneras= query.getResultList();
			
		} catch (PersistenceException e) {
			// TODO: handle exception
			throw new ServiciosException("Error en la consulta al obtener Terneras");
		}
    	return terneras;
    }
    

}
