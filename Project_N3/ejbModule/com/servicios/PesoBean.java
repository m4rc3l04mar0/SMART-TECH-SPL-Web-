package com.servicios;



import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Peso;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class PesoBean
 */
@Stateless
@LocalBean
public class PesoBean {
	
	@PersistenceContext
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public PesoBean() {
        // TODO Auto-generated constructor stub
    }
    
    public void ingresarPeso(Peso peso) throws ServiciosException {
		
		 try {
			em.persist(peso);
			em.flush();
		} catch (PersistenceException e) {
			
			throw new ServiciosException("Ha ocurrido un error al dar de alta un Peso");
		}
	}
    
    public List<Peso> obtenerPorIdTernera(Long idTernera) throws ServiciosException{
    	List<Peso> pesos= null;
    	if(idTernera != null) { 
    	try {
    		TypedQuery<Peso> query= em.createQuery("SELECT p FROM Peso p WHERE p.ternera LIKE :idTernera ",Peso.class).setParameter("idTernera", idTernera);
			pesos = query.getResultList();
		} catch (PersistenceException e) {
			// TODO: handle exception
			throw new ServiciosException("Ha ocurrido un error al obtener los Pesos");
		}
    	
    	}
    	return pesos;
    	
    	
    }
    

}
