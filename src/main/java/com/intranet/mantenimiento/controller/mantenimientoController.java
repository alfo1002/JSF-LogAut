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

    List<Permisos> lis_permisos = null;

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

        cargarPermisosSubModulos();

    }

    public void cargarPermisosSubModulos() {

        try {
            int codModulo = 0;
            Object dato = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codModulo");
            Usuario usu = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            if (dato != null) {
                try {
                    codModulo = Integer.parseInt(dato.toString());
                } catch (Exception e) {
                    codModulo = 0;
                }
                lis_permisos = permisosEJB.findPermisosByUsuarioModulo(codModulo, usu.getId());
            }
        } catch (Exception e) {
            System.out.println("Error en cargar permisos mantenimientox:" + e.toString());
        }

    }

}
