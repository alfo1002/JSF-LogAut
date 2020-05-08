/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intranet.mantenimiento.controller;

import com.intranet.entity.DetRolModulo;
import com.intranet.entity.Modulo;
import com.intranet.entity.Rol;
import com.intranet.entity.Usuario;
import com.intranet.mantenimiento.ejb.DetRolModuloFacadeLocal;
import com.intranet.mantenimiento.ejb.ModuloFacadeLocal;
import com.intranet.mantenimiento.ejb.RolFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Walter Reyes
 */
@Named(value = "crudRolesController")
@ViewScoped
public class crudRolesController implements Serializable{


    @EJB
    private RolFacadeLocal rolEJB;
    
    @EJB
    private ModuloFacadeLocal moduloEJB;
    
    @EJB
    private DetRolModuloFacadeLocal detalleRolModuloEJB;
    
    private List<Rol> lis_roles;
    private Rol rol;
    
    private List<Modulo> lis_modulos;

    
    private DualListModel<Modulo> modulos = null;



    public DualListModel<Modulo> getModulos() {
        return modulos;
    }

    public void setModulos(DualListModel<Modulo> modulos) {
        this.modulos = modulos;
    }

    public List<Modulo> getLis_modulos() {
        return lis_modulos;
    }

    public void setLis_modulos(List<Modulo> lis_modulos) {
        this.lis_modulos = lis_modulos;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Rol> getLis_roles() {
        return lis_roles;
    }

    public void setLis_roles(List<Rol> lis_roles) {
        this.lis_roles = lis_roles;
    }
    
    
    public crudRolesController() {
    }
    
    @PostConstruct
    public void init(){
        obtenerRoles();
        List<Modulo> lista_permisos_disponibles = new ArrayList<>();
        List<Modulo> lista_permisos_seleccionados = new ArrayList<>();
        modulos = new DualListModel<>(lista_permisos_disponibles, lista_permisos_seleccionados);
        
    }
    
    public void obtenerRoles(){
        try {
            rol = new Rol();
            lis_roles = rolEJB.findAll();
            
        } catch (Exception e) {
            System.out.println("Error: " + e.toString() + " --- " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void guardarRol() {
        try {
            Date fec = new Date();
            Usuario usu = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            rol.setCreado_por(usu.getId());
            rol.setFecha_crea(fec);
            rolEJB.create(rol);
            rol = null;

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Registro Exitoso!"));
            init();
            RequestContext.getCurrentInstance().execute("PF('modalNuevoRol').hide();");

        } catch (Exception e) {
            System.out.println("Error al guardarRol:" + e.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al registrar...."));
        }
    }
    
    
    public void guardarEdicionRol() {
        try {
            rolEJB.edit(rol);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Edicion Exitosa!"));
            init();
            RequestContext.getCurrentInstance().execute("PF('windowsEditar').hide();");

        } catch (Exception e) {
            System.out.println("Error al guardar Edicion: " + e.toString() + "Error: " + e.getMessage());
            e.getStackTrace();
        }
    }
    
    public void cargarModulos(Rol rol) {
        try {
            this.rol = rol;
            
            List<Modulo> lista_permisos_disponibles = moduloEJB.findByAllActivos();
            List<Modulo> lista_permisos_seleccionados = moduloEJB.findByCodigoRolUsuario(rol.getId());
            
            for(int i=0; i< lista_permisos_disponibles.size(); i++){
                for(int j=0; j< lista_permisos_seleccionados.size(); j++){
                    if(lista_permisos_disponibles.get(i).getCodigo() == lista_permisos_seleccionados.get(j).getCodigo()){
                        lista_permisos_disponibles.remove(i);
                    }
                }
            }
            modulos = new DualListModel<>(lista_permisos_disponibles, lista_permisos_seleccionados);

        } catch (Exception e) {
            System.out.println("Error en cargarPermisos(Ususairo usu)" + e.toString());
        }
    }
    
    
    public void onTransfer(TransferEvent event) {
        try {
            if (event.isAdd()) {
                for (Object item : event.getItems()) {
                    Modulo modulo = moduloEJB.find(Integer.parseInt((String) item));
                    DetRolModulo det_rol_modulo = new DetRolModulo();
                    det_rol_modulo.setCodModulo(modulo);
                    det_rol_modulo.setCodRol(rol);
                    detalleRolModuloEJB.create(det_rol_modulo); 
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Módulo Añadido"));
                }
            } else {
                if (event.isRemove()) {
                    for (Object item : event.getItems()) {
                        Modulo permiso = moduloEJB.find(Integer.parseInt((String) item));
                        moduloEJB.eliminarModulosPorModuloyRol(permiso, rol);
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Módulo Eliminado"));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.toString() + " ---- " + e.getMessage());
            e.getStackTrace();
        }
    }
    
}
