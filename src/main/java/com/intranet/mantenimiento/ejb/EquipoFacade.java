/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intranet.mantenimiento.ejb;

import com.intranet.entity.Empresa;
import com.intranet.entity.Equipo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Walter Reyes
 */
@Stateless
public class EquipoFacade extends AbstractFacade<Equipo> implements EquipoFacadeLocal {

    @PersistenceContext(unitName = "ConexionBDFactura")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EquipoFacade() {
        super(Equipo.class);
    }

    @Override
    public void actualizarContador(Empresa emp) {
//        try {
//            String consulta;
//            
//            consulta = "UPDATE Empresa e SET e.contador = contador+1 WHERE e = ?1";
//            Query query = em.createQuery(consulta);
//            query.setParameter(1, emp);
//            System.out.println("Se actualizo : " + query.executeUpdate());
//            
//        } catch (Exception e) {
//            System.out.println("Error resetenado clave de usuario: " + e.toString() + " ---- " + e.getMessage());
//            e.getStackTrace();
//        }
    }
    
}
