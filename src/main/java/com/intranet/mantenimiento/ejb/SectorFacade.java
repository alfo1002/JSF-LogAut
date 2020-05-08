/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intranet.mantenimiento.ejb;

import com.intranet.entity.Sector;
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
public class SectorFacade extends AbstractFacade<Sector> implements SectorFacadeLocal {

    @PersistenceContext(unitName = "ConexionBDFactura")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SectorFacade() {
        super(Sector.class);
    }

    @Override
    public List<Sector> findAllActivos() {
        List<Sector> lis_sector = null;
        try {
            String consuta = "SELECT s From Sector s  WHERE s.estado = ?1";
            Query query = em.createQuery(consuta);
            query.setParameter(1, "A");
            lis_sector = query.getResultList();
            
        } catch (Exception e) {
            System.out.println("Error al buscar en findAllActivos Sector : " + e.toString());
            e.printStackTrace();
        }
        return lis_sector;
    }
    
    
    
}
