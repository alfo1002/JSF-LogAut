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
import javax.ejb.Local;

/**
 *
 * @author Walter Reyes
 */
@Local
public interface DetRolModuloFacadeLocal {

    void create(DetRolModulo detRolModulo);

    void edit(DetRolModulo detRolModulo);

    void remove(DetRolModulo detRolModulo);

    DetRolModulo find(Object id);

    List<DetRolModulo> findAll();

    List<DetRolModulo> findRange(int[] range);

    int count();
    
    List<Modulo> findByCodigoRol(Rol rol);
    
}
