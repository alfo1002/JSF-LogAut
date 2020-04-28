/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intranet.mantenimiento.ejb;

import com.intranet.entity.Usuario;
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
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "ConexionBDFactura")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    public Usuario findByCedulax(String cedula) {
    
        em.flush();
        Usuario usu = null;
        String consulta;
        try{
            consulta = "SELECT u From Usuario u WHERE u.cedula = ?1  and u.estado = ?2";
            Query query = em.createQuery(consulta);
            query.setParameter(1, cedula);
            query.setParameter(2, "A");
            
            List<Usuario> lista = query.getResultList();
            
            if(!lista.isEmpty()){
                usu = lista.get(0);
            }
            
        }catch(Exception e){
            System.out.println("Error:" + e.toString());
        }        
        return usu;
    }
    
    @Override
    public List<Usuario> findAllOrdenados() {
    
        em.flush();
        List<Usuario> usu = null;
        String consulta;
        try{
            consulta = "SELECT u From Usuario u WHERE u.estado = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, "A");
            
            usu = query.getResultList();
            
        }catch(Exception e){
            System.out.println("Error:" + e.toString());
        }        
        return usu;
    }
    
    
}
