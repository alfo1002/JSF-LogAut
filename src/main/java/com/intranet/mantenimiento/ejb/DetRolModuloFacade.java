/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intranet.mantenimiento.ejb;

import com.intranet.entity.DetRolModulo;
import com.intranet.entity.Modulo;
import com.intranet.entity.Rol;
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
public class DetRolModuloFacade extends AbstractFacade<DetRolModulo> implements DetRolModuloFacadeLocal {

    @PersistenceContext(unitName = "ConexionBDFactura")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetRolModuloFacade() {
        super(DetRolModulo.class);
    }

    @Override
    public List<Modulo> findByCodigoRol(Rol rol) {
        
        List<Modulo> lista_modulos = null;
        
        try {
            
            String consulta = "SELECT det.codModulo FROM DetRolModulo det WHERE det.codRol = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, rol);
            lista_modulos = query.getResultList();
            
        } catch (Exception e) {
            System.out.println("Error: " + e.toString() + " ----- " + e.getMessage());
            e.getStackTrace();
        }
        return lista_modulos;
    }
    
}
