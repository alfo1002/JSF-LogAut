/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.factura.ejb;

import com.factura.entity.Modulo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Walter Reyes
 */
@Local
public interface ModuloFacadeLocal {

    void create(Modulo modulo);

    void edit(Modulo modulo);

    void remove(Modulo modulo);

    Modulo find(Object id);

    List<Modulo> findAll();

    List<Modulo> findRange(int[] range);

    int count();
    
    List<Modulo> findByCodigoRolUsuario(int codRolUsu);
    
    
}
