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
import javax.ejb.Local;

/**
 *
 * @author Walter Reyes
 */
@Local
public interface PermisosFacadeLocal {

    void create(Permisos permisos);

    void edit(Permisos permisos);

    void remove(Permisos permisos);

    Permisos find(Object id);

    List<Permisos> findAll();

    List<Permisos> findRange(int[] range);

    int count();
    
    List<Permisos> findPermisosByUsuarioModulo(int codModulo, int codUsuario);
    
    List<Permisos> findPermisosByCodigoModulo(int codModulo);
    
    void eliminarPermisosPorUsuario(int codUsuario);
    
    void eliminarPermisosPorUsuarioyPermiso(Usuario usu, Permisos per);
    
    List<Permisos> findPermisosByUsuarioModulo(Modulo mod, Usuario usu);
    
}
