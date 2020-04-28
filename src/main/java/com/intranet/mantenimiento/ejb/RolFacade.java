/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intranet.mantenimiento.ejb;

import com.intranet.entity.Rol;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Walter Reyes
 */
@Stateless
public class RolFacade extends AbstractFacade<Rol> implements RolFacadeLocal {

    @PersistenceContext(unitName = "ConexionBDFactura")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolFacade() {
        super(Rol.class);
    }

    @Override
    public List<Rol> findAllActive() {
        List<Rol> lista = null;
        try {
            TypedQuery<Rol> consulta = em.createNamedQuery(
                    "Rol.findByEstado", Rol.class);
            consulta.setParameter("estado", "A");
            lista = consulta.getResultList();
        } catch (Exception e) {
        }

        return lista;

    }

}
