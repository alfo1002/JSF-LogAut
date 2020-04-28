/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intranet.mantenimiento.controller;

import com.intranet.mantenimiento.ejb.ModuloFacadeLocal;
import com.intranet.entity.Modulo;
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
@Named(value = "principalController")
@ViewScoped
public class principalController implements Serializable{

    @EJB
    private ModuloFacadeLocal moduloEJB;
    
    List<Modulo> lista_modulos;

    public List<Modulo> getLista_modulos() {
        return lista_modulos;
    }

    public void setLista_modulos(List<Modulo> lista_modulos) {
        this.lista_modulos = lista_modulos;
    }
    
    
    
    
    public principalController() {
    }
    
    @PostConstruct
    public void init(){
        obtenerModulos();
    }
    
    
    public void obtenerModulos() {
        System.out.println("Obteniendo Modulos::::");
        try {
            Usuario usu = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            lista_modulos = moduloEJB.findByCodigoRolUsuario(usu.getCodRol().getId());
            System.out.println("Lista modulos:" + lista_modulos);

            for (int i = 0; i < lista_modulos.size(); i++) {
                System.out.println("Módulos:" + lista_modulos.get(i).getNombre());
            }

        } catch (Exception e) {
            System.out.println("Error obteniendo Módulos:" + e.toString());
        }
    }
    
    public void redirect(int cod, String url){
        try {
            System.out.println("Redireccionado desde modulo.......");
            
            String urlx = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + url;
            System.out.println("URL: " + urlx);
            
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
            
        } catch (Exception e) {
            
        }
    }
}
