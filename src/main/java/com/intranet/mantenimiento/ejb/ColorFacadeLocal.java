/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intranet.mantenimiento.ejb;

import com.intranet.entity.Color;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Walter Reyes
 */
@Local
public interface ColorFacadeLocal {

    void create(Color color);

    void edit(Color color);

    void remove(Color color);

    Color find(Object id);

    List<Color> findAll();

    List<Color> findRange(int[] range);

    int count();
    
    List<Color> findAllActivos();
    
}
