/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intranet.mantenimiento.controller;

import com.intranet.entity.Sector;
import com.intranet.mantenimiento.ejb.SectorFacadeLocal;
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
@Named(value = "crudSectoresController")
@ViewScoped
public class crudSectoresController implements Serializable{

    @EJB
    private SectorFacadeLocal sectorEJB;
    
    private List<Sector> lis_sector;

    private Sector sector;

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }
    
    public List<Sector> getLis_sector() {
        return lis_sector;
    }

    public void setLis_sector(List<Sector> lis_sector) {
        this.lis_sector = lis_sector;
    }
    
    public crudSectoresController() {
    }
    
    @PostConstruct
    public void init(){
        try {
            sector = new Sector();
            lis_sector = sectorEJB.findAll();
            
            
        } catch (Exception e) {
        }
    }
    
    public void agregarSector(){
        try {
            sector.setEstado("A");
        } catch (Exception e) {
        }
    }
    
    
    public void guardaSector(int tipo) {

        try {
            if (sector != null) {
                sectorEJB.create(sector);
                sector = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Registro Exitoso!"));
                init();
                if(tipo == 1){
                    RequestContext.getCurrentInstance().execute("PF('modalNuevo').hide();");
                }else{
                    RequestContext.getCurrentInstance().execute("PF('addSector').hide();");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al guardaSector:" + e.toString());
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al registrar...."));
        }
    }
    
    
     public void guardarEdicionSector(){
         try {
            sectorEJB.edit(sector);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Edicion Exitosa!"));
            init();
            RequestContext.getCurrentInstance().execute("PF('windowsEditar').hide();");

        } catch (Exception e) {
            System.out.println("Error al guardar Edicion: " + e.toString() + "Error: " + e.getMessage());
            e.getStackTrace();
        }
    }
    
}
