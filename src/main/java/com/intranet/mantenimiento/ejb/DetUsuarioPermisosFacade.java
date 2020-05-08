/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intranet.mantenimiento.ejb;

import com.intranet.entity.DetUsuarioPermisos;
import com.intranet.entity.Modulo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Walter Reyes
 */
@Stateless
public class DetUsuarioPermisosFacade extends AbstractFacade<DetUsuarioPermisos> implements DetUsuarioPermisosFacadeLocal {

    @PersistenceContext(unitName = "ConexionBDFactura")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetUsuarioPermisosFacade() {
        super(DetUsuarioPermisos.class);
    }
    
}
