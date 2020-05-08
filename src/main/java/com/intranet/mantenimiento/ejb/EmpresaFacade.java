/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intranet.mantenimiento.ejb;

import com.intranet.entity.Empresa;
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
public class EmpresaFacade extends AbstractFacade<Empresa> implements EmpresaFacadeLocal {

    @PersistenceContext(unitName = "ConexionBDFactura")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpresaFacade() {
        super(Empresa.class);
    }
    
    public List<Empresa> findAllActivas(){
        List<Empresa> lis_empresa = null;
        
        try {
            String consulta = "SELECT e FROM Empresa e WHERE e.estado = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, "A");
            lis_empresa = query.getResultList();
            
        } catch (Exception e) {
            System.out.println("Error:"+ e.toString() + " ----- " + e.getMessage());
            e.printStackTrace();
        }
        return lis_empresa;
    } 
}
