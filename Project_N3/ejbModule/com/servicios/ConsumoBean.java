package com.servicios;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Alimento;
import com.entities.Consumo;
import com.entities.Ternera;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class ConsumoBean
 */
@Stateless
@LocalBean
public class ConsumoBean {
	
	@PersistenceContext
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public ConsumoBean() {
        // TODO Auto-generated constructor stub
    }
    
    public void ingresarConsumo(Consumo consumo) throws ServiciosException {
		
		 try {
			em.persist(consumo);
			em.flush();
		} catch (PersistenceException e) {
			
			throw new ServiciosException("Ha ocurrido un error al ingresar un consumo");
		}
	}
    
    
    public List<Consumo> obtenerTodos() throws ServiciosException{
    	try {
    		TypedQuery<Consumo> query = em.createQuery("SELECT c FROM Consumo c", Consumo.class );
			return query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			throw new ServiciosException("Error al conseguir registros ");
		}
    }

	

	

	
    

}
