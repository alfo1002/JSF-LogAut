/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intranet.mantenimiento.ejb;

import com.intranet.entity.Color;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Walter Reyes
 */
@Stateless
public class ColorFacade extends AbstractFacade<Color> implements ColorFacadeLocal {

    @PersistenceContext(unitName = "ConexionBDFactura")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ColorFacade() {
        super(Color.class);
    }
    
    public List<Color> findAllActivos(){
        List<Color> lis_color = null;
        try {
            String consuta = "SELECT c From Color c  WHERE c.estado = ?1";
            Query query = em.createQuery(consuta);
            query.setParameter(1, "A");
            lis_color = query.getResultList();
            
        } catch (Exception e) {
            System.out.println("Error al buscar en findAllActivos: " + e.toString());
            e.printStackTrace();
        }
        return lis_color;
    }
    
}
