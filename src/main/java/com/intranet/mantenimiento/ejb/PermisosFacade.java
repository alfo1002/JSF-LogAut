/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intranet.mantenimiento.ejb;

import com.intranet.entity.Modulo;
import com.intranet.entity.Permisos;
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
public class PermisosFacade extends AbstractFacade<Permisos> implements PermisosFacadeLocal {

    @PersistenceContext(unitName = "ConexionBDFactura")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PermisosFacade() {
        super(Permisos.class);
    }

    @Override
    public List<Permisos> findPermisosByUsuarioModulo(int codModulo, int codUsuario) {
    
        List<Permisos> lista_permisos = null;
        
        try {
            
            //select p from private.permisos p, private.modulo m, private.det_usuario_permisos det where
            //p.cod_modulo = m.codigo and m.codigo=2
            //and p.id=det.cod_per and det.cod_usu=1
            
            //"SELECT m From Modulo m, DetRolModulo det, Rol r  "
            //        + " WHERE m.codigo = det.codModulo.codigo "
            //        + " and det.codRol.id = r.id and r.id  = ?1 and r.estado = ?2";
            
            String consulta = "SELECT p From Permisos p, Modulo m, DetUsuarioPermisos det "
                    + " WHERE p.codModulo.codigo = m.codigo and m.codigo = ?1"
                    + " and p.id = det.codPermiso.id and det.codUsuario.id = ?2 ";
            Query query = em.createQuery(consulta);
            query.setParameter(1, codModulo);
            query.setParameter(2, codUsuario);
            lista_permisos = query.getResultList();
            
        } catch (Exception e) {
            System.out.println("Error en findPermisosByUsuarioModulo()" + e.toString());
        }
        
        return lista_permisos;
    }

    @Override
    public List<Permisos> findPermisosByCodigoModulo(int codModulo) {
        
        List<Permisos> lista_permisos = null;
        
        try {
            
            String consulta = "SELECT p From Permisos p WHERE p.codModulo.codigo = ?1 ";
            Query query = em.createQuery(consulta);
            query.setParameter(1, codModulo);
            lista_permisos = query.getResultList();
            
        } catch (Exception e) {
            System.out.println("Error en findPermisosByCodigoModulo()" + e.toString());
        }
        
        return lista_permisos;
        
    }
    
    @Override
    public void eliminarPermisosPorUsuario(int codUsuario){
        try {
            String consulta;
            
            consulta = "DELETE FROM DetUsuarioPermisos det WHERE det.codUsuario.id = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, codUsuario);
            System.out.println("Resultado del DELETE: " + query.executeUpdate());
            
        } catch (Exception e) {
            System.out.println("Error eliminando permisos por usuario: " + e.toString() + " ---- " + e.getMessage());
            e.getStackTrace();
        }
    }
    
    @Override
    public void eliminarPermisosPorUsuarioyPermiso(Usuario usu, Permisos per){
        try {
            String consulta;
            
            consulta = "DELETE FROM DetUsuarioPermisos det WHERE det.codUsuario = ?1 and det.codPermiso = ?2";
            Query query = em.createQuery(consulta);
            query.setParameter(1, usu);
            query.setParameter(2, per);
            System.out.println("Se borro permiso: " + query.executeUpdate());
            
        } catch (Exception e) {
            System.out.println("Error eliminando permisos por usuario: " + e.toString() + " ---- " + e.getMessage());
            e.getStackTrace();
        }
    }
    
    @Override
    public List<Permisos> findPermisosByUsuarioModulo(Modulo mod, Usuario usu) {
    
        List<Permisos> lista_permisos = null;
        
        try {
            
            String consulta = "SELECT p From Permisos p, Modulo m, DetUsuarioPermisos det "
                    + " WHERE p.codModulo.codigo = m.codigo and m = ?1"
                    + " and p.id = det.codPermiso.id and det.codUsuario = ?2 ";
            Query query = em.createQuery(consulta);
            query.setParameter(1, mod);
            query.setParameter(2, usu);
            lista_permisos = query.getResultList();
            
        } catch (Exception e) {
            System.out.println("Error en findPermisosByUsuarioModulo()" + e.toString());
        }
        
        return lista_permisos;
    }
    
    
    
    
}
