/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intranet.mantenimiento.ejb;

import com.intranet.entity.Marca;
import com.intranet.entity.Modelo;
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
public class ModeloFacade extends AbstractFacade<Modelo> implements ModeloFacadeLocal {

    @PersistenceContext(unitName = "ConexionBDFactura")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ModeloFacade() {
        super(Modelo.class);
    }
    
    public List<Modelo> findByCodigoMarca(Marca marca){
        List<Modelo> lis_modelo = null;
        try {
            String consuta = "SELECT m From Modelo m WHERE m.codMarca = ?1";
            Query query = em.createQuery(consuta);
            query.setParameter(1, marca);
            lis_modelo = query.getResultList();
            
        } catch (Exception e) {
            System.out.println("Error al buscar en findByCodigoMarca(Marca marca): " + e.toString());
            e.printStackTrace();
        }
        return lis_modelo;
    }
    
}
