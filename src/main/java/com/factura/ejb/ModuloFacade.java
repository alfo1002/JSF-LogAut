/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.factura.ejb;

import com.factura.entity.Modulo;
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
public class ModuloFacade extends AbstractFacade<Modulo> implements ModuloFacadeLocal {

    @PersistenceContext(unitName = "ConexionBDFactura")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ModuloFacade() {
        super(Modulo.class);
    }

    @Override
    public List<Modulo> findByCodigoRolUsuario(int codRolUsu) {

        List<Modulo> lista_modulo = null;
        String consulta = "";
        try {
            //consulta = "SELECT m From Modulo m WHERE m.codrol = ?1";
            consulta = "SELECT m From Modulo m WHERE m.codRol.id = ?1 and m.estado = ?2";
            Query query = em.createQuery(consulta);
            query.setParameter(1, codRolUsu);
            query.setParameter(2, "A");
            lista_modulo = query.getResultList();
            
        } catch (Exception e) {
            System.out.println("Error en facade modulo:" + e.toString());
        }
        
        return lista_modulo;

    }
    
    
    
}
