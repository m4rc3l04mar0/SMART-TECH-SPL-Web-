package com.servicios;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Alimento;
import com.entities.Unidade;
import com.entities.Usuario;
import com.enums.Perfil;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class AlimentoBean
 */
@Stateless
@LocalBean
public class AlimentoBean {
	
	@PersistenceContext
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public AlimentoBean() {
        // TODO Auto-generated constructor stub
    }
    
    public void ingresarAlimento(Alimento alimento) throws ServiciosException {
		   	
    	
		 try {
			em.persist(alimento);
			em.flush();
		} catch (PersistenceException e) {
			
			throw new ServiciosException("Ha ocurrido un error al dar de alta un alimento");
		}
	}
    
    public List<Alimento> obtenerTodosAlimentos() throws ServiciosException {
		// TODO Auto-generated method stub
    	List<Alimento> alimento = null;
    	try {
    		TypedQuery<Alimento> query = em.createQuery("SELECT a FROM Alimento a ORDER BY a.idAlimento",Alimento.class);
    		alimento = query.getResultList();
		} catch (PersistenceException e) {
			// TODO: handle exception
			throw new ServiciosException("Ha ocurrido un error al obtener todos los Alimentos");
		}
		
		return alimento;
	}
    
    
    public void editarAlimento(Alimento alimento) throws ServiciosException {
		// TODO Auto-generated method stub
		try {
			em.merge(alimento);
			em.flush();
		} catch (PersistenceException e) {
			// TODO: handle exception
			throw new ServiciosException("No se pudo actualizar el alimento");
		}
		
	}
   
     
    
    
    public List<Alimento> obtenerNombreAlimentos(String nombre) throws ServiciosException{
    	List<Alimento> nombres = null;
    	try {
			if(!nombre.isEmpty()) {
				TypedQuery<Alimento> query = em.createQuery("SELECT a FROM Alimento a WHERE a.nombre", Alimento.class);
				nombres = query.getResultList();
			}
		} catch (PersistenceException e) {
			// TODO: handle exception
			throw new ServiciosException("Error al obtener nombres de Alimentos");
		}
		return nombres;
    }
    
    
    public String actualizarAlimento(String nombre, BigDecimal costoUnidad, BigDecimal cantidad) throws ServiciosException {
    	try{
       		Alimento alimentoModifi = em.createQuery("SELECT a from Alimento a WHERE a.nombre = :nombre", Alimento.class).
       		setParameter("nombre", nombre).getSingleResult();
       		//alimentoModifi.setNombre(alimentoModifi.getNombre());
       		alimentoModifi.setCostoUnidad(costoUnidad);
    		alimentoModifi.setCantidad(cantidad);
    		alimentoModifi.setUnidade(alimentoModifi.getUnidade());
    		em.merge(alimentoModifi);
    		em.flush();
    		return "actualizado";
    	} catch (PersistenceException e) {
    		throw new ServiciosException("Error al actualizar el alimento ");
    	}
    }
    
    public Alimento buscarAlimentoPorId (long ID_ALIMENTO) throws ServiciosException  {
    	try {
    		Alimento alimento = em.find(Alimento.class, ID_ALIMENTO);
    		return alimento;
    	} catch (PersistenceException e) {
   		throw new ServiciosException("Error al buscar la ternera por el Id " );
   	}

    }
    
    public Alimento obtenerPorId(Long idAlimento) throws ServiciosException {
    	Alimento a = null;
    	if(idAlimento != null) {
    		
    		try {
    			TypedQuery<Alimento> query = em.createQuery("SELECT a FROM Alimento a WHERE a.idAlimento LIKE :idAlimento", Alimento.class).setParameter("idAlimento", idAlimento);
        		a = query.getSingleResult();
			} catch (PersistenceException e) {
				// TODO: handle exception
				throw new ServiciosException("Error en la consulta de Alimento");
			}
    		   		
    	}
    	return a;
    	
    }

}
