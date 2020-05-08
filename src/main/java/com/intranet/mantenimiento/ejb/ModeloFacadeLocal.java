/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intranet.mantenimiento.ejb;

import com.intranet.entity.Marca;
import com.intranet.entity.Modelo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Walter Reyes
 */
@Local
public interface ModeloFacadeLocal {

    void create(Modelo modelo);

    void edit(Modelo modelo);

    void remove(Modelo modelo);

    Modelo find(Object id);

    List<Modelo> findAll();

    List<Modelo> findRange(int[] range);

    int count();
    
    List<Modelo> findByCodigoMarca(Marca marca);
    
}
