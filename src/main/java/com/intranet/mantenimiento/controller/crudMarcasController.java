/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intranet.mantenimiento.controller;

import com.intranet.entity.Marca;
import com.intranet.mantenimiento.ejb.MarcaFacadeLocal;
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
@Named(value = "crudMarcasController")
@ViewScoped
public class crudMarcasController implements Serializable{

    @EJB
    private MarcaFacadeLocal marcaEJB;
    
    private List<Marca> lis_marcas;
    
    private Marca marca;



    public List<Marca> getLis_marcas() {
        return lis_marcas;
    }

    public void setLis_marcas(List<Marca> lis_marcas) {
        this.lis_marcas = lis_marcas;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }
    
    public crudMarcasController() {
    }
    
    @PostConstruct
    public void init(){
        marca = new Marca();
        obtenerTodasMarcas();
    }
    
    public void agregarMarca(){
        try {
            marca.setEstado("A");
        } catch (Exception e) {
        }
    }
    
    public void obtenerTodasMarcas() {
        try {

            lis_marcas = marcaEJB.findAll();

        } catch (Exception e) {
            System.out.println("Excepción en obtenerTodasMarcas: " + e.toString());
            e.printStackTrace();
        }
    }
    
     /*
    tipo = 1 para guardar localmente
    tipo <> 1 para guardar desde modal externo
    */
    public void guardaMarca(int tipo) {

        try {
            if (marca != null) {
                marcaEJB.create(marca);
                marca = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Registro Exitoso!"));
                init();
                if(tipo == 1){
                    RequestContext.getCurrentInstance().execute("PF('modalNuevo').hide();");
                }else{
                    RequestContext.getCurrentInstance().execute("PF('addMarca').hide();");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al guardaEmpresa:" + e.toString());
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al registrar...."));
        }
    }
    
    public void guardarEdicionMarca(){
         try {
            marcaEJB.edit(marca);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Edicion Exitosa!"));
            init();
            RequestContext.getCurrentInstance().execute("PF('windowsEditar').hide();");

        } catch (Exception e) {
            System.out.println("Error al guardar Edicion: " + e.toString() + "Error: " + e.getMessage());
            e.getStackTrace();
        }
    }
    
    
    
}
