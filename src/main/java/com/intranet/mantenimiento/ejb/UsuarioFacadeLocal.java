/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intranet.mantenimiento.ejb;

import com.intranet.entity.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Walter Reyes
 */
@Local
public interface UsuarioFacadeLocal {

    void create(Usuario usuario);

    void edit(Usuario usuario);

    void remove(Usuario usuario);

    Usuario find(Object id);

    List<Usuario> findAll();

    List<Usuario> findRange(int[] range);

    int count();
    
    Usuario findByCedulax(String cedula);
    
    List<Usuario> findAllOrdenados();
    
    void resetearClaveUsuario(String clave , int id);
    
    List<Usuario> findByUsuario(Usuario usuario);
}
