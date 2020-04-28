/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intranet.mantenimiento.controller;

import com.intranet.config.Configuraciones;
import com.intranet.mantenimiento.ejb.DetRolModuloFacadeLocal;
import com.intranet.mantenimiento.ejb.ModuloFacadeLocal;
import com.intranet.mantenimiento.ejb.PermisosFacadeLocal;
import com.intranet.mantenimiento.ejb.UsuarioFacadeLocal;
import com.intranet.entity.Modulo;
import com.intranet.entity.Permisos;
import com.intranet.entity.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
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

    @EJB
    private UsuarioFacadeLocal usuarioEJB;

    @EJB
    private DetRolModuloFacadeLocal detRolModuloEJB;

    @EJB
    private PermisosFacadeLocal permisosEJB;
    
    @Inject
    private Configuraciones config;

    List<Modulo> lista_modulos;

    private MenuModel model;

    String url_principal;

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }

    public String getUrl_principal() {
        return url_principal;
    }

    public void setUrl_principal(String url_principal) {
        this.url_principal = url_principal;
    }

    @PostConstruct
    public void init() {

        url_principal = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();

        Usuario usu = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        if (usu != null) {
            model = new DefaultMenuModel();
            this.construirMenu();
        }

    }

    public templateController() {

    }

    public void verificarSesion() {
        try {
            String path_ini = config.retornarVariable("path_ini");
            Usuario usu = null;
            usu = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            if (usu == null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect(path_ini + "faces/private/login/index.xhtml");
            } else {
                HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                String uri = request.getRequestURI();

                if (uri.contains("/intranet/faces/private/principal/")) {
                    return;
                }

                Usuario usuariox = usuarioEJB.findByCedulax(usu.getCedula());
                List<Modulo> lista_modulosx = detRolModuloEJB.findByCodigoRol(usuariox.getCodRol());
                
                Modulo moduloSeleccionado = null;

                for (int i = 0; i < lista_modulosx.size(); i++) {
                    if (uri.contains(lista_modulosx.get(i).getNombre().toLowerCase())) {
                        moduloSeleccionado = lista_modulosx.get(i);
                        System.out.println("Igualdad en módulo: " + moduloSeleccionado.getNombre());
                        break;
                    }
                }

                if (moduloSeleccionado == null) {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("../../private/principal/principal.xhtml");
                } else {
                    List<Permisos> permisosx = null;
                    permisosx = permisosEJB.findPermisosByUsuarioModulo(moduloSeleccionado.getCodigo(), usuariox.getId());
                    if (permisosx.size() <= 0) {
                        FacesContext.getCurrentInstance().getExternalContext().redirect(path_ini + "faces/private/principal/principal.xhtml");
                    } else {

                        if (uri.contains("principal.xhtml")) {

                        } else {
                            int ban = 0;
                            for (Permisos per : permisosx) {
                                System.out.println("URI:" + uri);
                                System.out.println("URL permiso: " + per.getUrl());
                                if (uri.contains(per.getUrl().replace("..", ""))) {
                                    System.out.println("Permisos Coinciden: " + per.getNombre() + " --- " + per.getUrl());
                                    return;
                                }
                            }
                            if (ban == 0) {
                                FacesContext.getCurrentInstance().getExternalContext().redirect(path_ini + "faces/private/principal/principal.xhtml");
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("xxxx -------    Error en verificación de Sesion!..............." + e.toString() + " ------  " + e.getMessage());
            e.getStackTrace();
        }
    }

    public void cerrarSesion() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        } catch (Exception e) {
        }
    }

    public void obtenerModulos() {
        try {
            Usuario usu = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            List<Modulo> lista_modulos = moduloEJB.findByCodigoRolUsuario(usu.getCodRol().getId());

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
                    item.setIcon(m.getIcon());
                    item.setUrl(m.getUrl() + "?codModulo=" + m.getCodigo());
                    //DefaultSubMenu firstSubmenu = new DefaultSubMenu(m.getNombre());
                    //model.addElement(firstSubmenu);
                    model.addElement(item);
                }
            } else {
                FacesContext.getCurrentInstance().getExternalContext().redirect("../index.xhtml");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.toString() + " ----- " + e.getMessage());
            e.getStackTrace();
        }
    }
}
