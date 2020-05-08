/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intranet.mantenimiento.ejb;

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
            //select * from private.modulo m, private.det_rol_modulo det, private.rol r
            //where m.codigo=det.cod_modulo and det.cod_rol=r.id and r.id=1 
            consulta = "SELECT m From Modulo m, DetRolModulo det, Rol r  "
                    + " WHERE m.codigo = det.codModulo.codigo "
                    + " and det.codRol.id = r.id and r.id  = ?1 and r.estado = ?2 and m.estado = ?3";
            Query query = em.createQuery(consulta);
            query.setParameter(1, codRolUsu);
            query.setParameter(2, "A");
            query.setParameter(3, "A");
            lista_modulo = query.getResultList();

        } catch (Exception e) {
            System.out.println("Error en facade modulo:" + e.toString());
        }

        return lista_modulo;

    }

    @Override
    public Modulo findbyIdActivos(int id) {
        List<Modulo> lista_modulos = null;
        Modulo mod = null;
        try {
            String consulta = "";
            consulta = "SELECT m From Modulo m WHERE m.codigo = ?1 and m.estado = ?2";
            Query query = em.createQuery(consulta);
            query.setParameter(1, id);
            query.setParameter(2, "A");
            lista_modulos = query.getResultList();
            if(lista_modulos.size() > 0){
                mod = lista_modulos.get(0);
            }
        } catch (Exception e) {
            System.out.println("Error en buscar modulo por ID:"+ e.toString());
        }

        return mod;
    }

    @Override
    public List<Modulo> findByAllActivos() {

        List<Modulo> lista_modulo = null;
        String consulta = "";
        try {
            consulta = "SELECT m From Modulo m WHERE m.estado = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, "A");
            lista_modulo = query.getResultList();

        } catch (Exception e) {
            System.out.println("Error en facade modulo:" + e.toString());
        }

        return lista_modulo;

    }
    
    
    @Override
    public void eliminarModulosPorModuloyRol(Modulo mod, Rol rol){
        try {
            String consulta;
            
            consulta = "DELETE FROM DetRolModulo det WHERE det.codRol = ?1 and det.codModulo = ?2";
            Query query = em.createQuery(consulta);
            query.setParameter(1, rol);
            query.setParameter(2, mod);
            System.out.println("Se borro modulo: " + query.executeUpdate());
            
        } catch (Exception e) {
            System.out.println("Error eliminando modulos por modulo y rol: " + e.toString() + " ---- " + e.getMessage());
            e.getStackTrace();
        }
    }
    
    
}
