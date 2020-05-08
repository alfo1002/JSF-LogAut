/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intranet.mantenimiento.controller;

import com.intranet.entity.TipoEquipo;
import com.intranet.mantenimiento.ejb.TipoEquipoFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Walter Reyes
 */
@Named(value = "crudTipoEquipoController")
@ViewScoped
public class crudTipoEquipoController implements Serializable{

    @EJB
    private TipoEquipoFacadeLocal tipoEquipoEJB;
    
    private TipoEquipo tipo_equipo;
    
    private List<TipoEquipo> lis_tipo_equipo;
    
    
    public crudTipoEquipoController() {
    }

    public TipoEquipo getTipo_equipo() {
        return tipo_equipo;
    }

    public void setTipo_equipo(TipoEquipo tipo_equipo) {
        this.tipo_equipo = tipo_equipo;
    }

    public List<TipoEquipo> getLis_tipo_equipo() {
        return lis_tipo_equipo;
    }

    public void setLis_tipo_equipo(List<TipoEquipo> lis_tipo_equipo) {
        this.lis_tipo_equipo = lis_tipo_equipo;
    }
    
    @PostConstruct
    public void init(){
        try {
            lis_tipo_equipo = tipoEquipoEJB.findAll();
            tipo_equipo = new TipoEquipo();
            
        } catch (Exception e) {
            System.out.println("Error init: " + e.toString());
            e.printStackTrace();
        }
    }
    
    public void agregarTipoEquipo(){
        try {
            tipo_equipo.setEstado("A");
        } catch (Exception e) {
        }
    }
    
    
     /*
    tipo = 1 para guardar localmente
    tipo <> 1 para guardar desde modal externo
    */
    public void guardaTipoEquipo(int tipo) {

        try {
            if (tipo_equipo != null) {
                tipoEquipoEJB.create(tipo_equipo);
                tipo_equipo = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Registro Exitoso!"));
                init();
                if(tipo == 1){
                    RequestContext.getCurrentInstance().execute("PF('modalNuevo').hide();");
                }else{
                    RequestContext.getCurrentInstance().execute("PF('addTipoEquipo').hide();");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al guardaEmpresa:" + e.toString());
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al registrar...."));
        }
    }
    
     public void guardarEdicionTipoEquipo(){
         try {
            tipoEquipoEJB.edit(tipo_equipo);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Edicion Exitosa!"));
            init();
            RequestContext.getCurrentInstance().execute("PF('windowsEditar').hide();");

        } catch (Exception e) {
            System.out.println("Error al guardar Edicion: " + e.toString() + "Error: " + e.getMessage());
            e.getStackTrace();
        }
    }
}
