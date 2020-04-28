/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intranet.mantenimiento.controller;

import com.intranet.mantenimiento.ejb.PermisosFacadeLocal;
import com.intranet.entity.Permisos;
import com.intranet.entity.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Walter Reyes
 */
@Named(value = "mantenimientoController")
@ViewScoped
public class mantenimientoController implements Serializable {

    @EJB
    private PermisosFacadeLocal permisosEJB;

    List<Permisos> lis_permisos;
    
    private int codModulo;

    public int getCodModulo() {
        return codModulo;
    }

    public void setCodModulo(int codModulo) {
        this.codModulo = codModulo;
    }

    public List<Permisos> getLis_permisos() {
        return lis_permisos;
    }

    public void setLis_permisos(List<Permisos> lis_permisos) {
        this.lis_permisos = lis_permisos;
    }
    
    public mantenimientoController() {
    }

    @PostConstruct
    public void init() {
        System.out.println("Inicio mantenimiento");
        codModulo = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codModulo"));
        System.out.println("Modulo a Buscar:" + codModulo);
        cargarPermisosSubModulos();
        
    }

    public void cargarPermisosSubModulos() {

        try {
            Usuario usu =  (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            lis_permisos = permisosEJB.findPermisosByUsuarioModulo(codModulo, usu.getId());
            for(int i=0; i< lis_permisos.size(); i++){
                System.out.println("Permisos del Módulo:" + lis_permisos.get(i).getNombre() + " URL: " + lis_permisos.get(i).getUrl());
            }

        } catch (Exception e) {
            System.out.println("Error en cargar permisos mantenimiento:" + e.toString());
        }

    }

}
