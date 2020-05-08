/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intranet.mantenimiento.ejb;

import com.intranet.entity.DetUsuarioPermisos;
import com.intranet.entity.Modulo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Walter Reyes
 */
@Local
public interface DetUsuarioPermisosFacadeLocal {

    void create(DetUsuarioPermisos detUsuarioPermisos);

    void edit(DetUsuarioPermisos detUsuarioPermisos);

    void remove(DetUsuarioPermisos detUsuarioPermisos);

    DetUsuarioPermisos find(Object id);

    List<DetUsuarioPermisos> findAll();

    List<DetUsuarioPermisos> findRange(int[] range);

    int count();
    
   
}
