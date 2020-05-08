/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intranet.mantenimiento.ejb;

import com.intranet.entity.Marca;
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
public class MarcaFacade extends AbstractFacade<Marca> implements MarcaFacadeLocal {

    @PersistenceContext(unitName = "ConexionBDFactura")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public MarcaFacade() {
        super(Marca.class);
    }

    @Override
    public List<Marca> findAllActivos() {
        List<Marca> lis_marcas = null;
        try {
            String consulta = "SELECT m From Marca m WHERE m.estado = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, "A");
            lis_marcas = query.getResultList();
            
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            e.printStackTrace();
        }
        return lis_marcas;
    }
    
}
