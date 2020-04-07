/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.factura.controller;

import com.factura.ejb.ModuloFacadeLocal;
import com.factura.entity.Modulo;
import com.factura.entity.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author Walter Reyes
 */
@Named(value = "templateController")
@ViewScoped
public class templateController implements Serializable {

    @EJB
    private ModuloFacadeLocal moduloEJB;

    List<Modulo> lista_modulos;

    private MenuModel model;

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }

    @PostConstruct
    public void init() {
        model = new DefaultMenuModel();
        this.construirMenu();
    }

    public templateController() {

    }

    public void verificarSesion() {
        try {
            Usuario usu = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            if (usu == null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("../index.xhtml");
            }
        } catch (Exception e) {
            System.out.println("Error en verificación de Sesion!...............");
        }
    }

    public void cerrarSesion() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        } catch (Exception e) {
        }
    }

    public void obtenerModulos() {
        System.out.println("Obteniendo Modulos::::");
        try {
            Usuario usu = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            List<Modulo> lista_modulos = moduloEJB.findByCodigoRolUsuario(usu.getCodRol().getId());
            System.out.println("Lista modulos:" + lista_modulos);

            for (int i = 0; i < lista_modulos.size(); i++) {
                System.out.println("Módulos:" + lista_modulos.get(i).getNombre());
            }

        } catch (Exception e) {
            System.out.println("Error obteniendo Módulos:" + e.toString());
        }
    }

    public void construirMenu() {
        try {
            Usuario usu = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

            if (usu != null) {
                lista_modulos = moduloEJB.findByCodigoRolUsuario(usu.getCodRol().getId());

                for (Modulo m : lista_modulos) {
                    DefaultMenuItem item = new DefaultMenuItem();
                    item.setValue(m.getNombre());
                    item.setIcon("pi pi-cog");
                    item.setUrl(m.getUrl());
                    //DefaultSubMenu firstSubmenu = new DefaultSubMenu(m.getNombre());
                    //model.addElement(firstSubmenu);
                    model.addElement(item);
                }
            } else {
                FacesContext.getCurrentInstance().getExternalContext().redirect("../index.xhtml");
            }
        } catch (Exception e) {
        }
    }
}
