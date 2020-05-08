/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intranet.mantenimiento.ejb;

import com.intranet.entity.TipoEquipo;
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
public class TipoEquipoFacade extends AbstractFacade<TipoEquipo> implements TipoEquipoFacadeLocal {

    @PersistenceContext(unitName = "ConexionBDFactura")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoEquipoFacade() {
        super(TipoEquipo.class);
    }

    @Override
    public List<TipoEquipo> findAllActivos() {
        List<TipoEquipo> lis_tipo_equipo = null;
        try {
            String consulta = "SELECT t FROM TipoEquipo t WHERE t.estado = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, "A");
            lis_tipo_equipo = query.getResultList();
            
        } catch (Exception e) {
            System.out.println("Error al buscar tipo de equipos!");
        }
        return lis_tipo_equipo;
    }
    
}
