/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intranet.mantenimiento.controller;

import com.intranet.entity.Marca;
import com.intranet.entity.Modelo;
import com.intranet.mantenimiento.ejb.MarcaFacadeLocal;
import com.intranet.mantenimiento.ejb.ModeloFacadeLocal;
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
@Named(value = "crudModelosController")
@ViewScoped
public class crudModelosController implements Serializable{

    @EJB
    private ModeloFacadeLocal modeloEJB;
    
    @EJB
    private MarcaFacadeLocal marcaEJB;
    
    private List<Modelo> lis_modelo;
    private List<Marca> lis_marca;
    
    private Modelo modelo;
    private Marca marca;
    
    private Integer codMarca;


    public List<Marca> getLis_marca() {
        return lis_marca;
    }

    public void setLis_marca(List<Marca> lis_marca) {
        this.lis_marca = lis_marca;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }
    
    public Integer getCodMarca() {
        return codMarca;
    }

    public void setCodMarca(Integer codMarca) {
        this.codMarca = codMarca;
    }

    public List<Modelo> getLis_modelo() {
        return lis_modelo;
    }

    public void setLis_modelo(List<Modelo> lis_modelo) {
        this.lis_modelo = lis_modelo;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public crudModelosController() {
    }
    
    @PostConstruct
    public void init(){
        modelo = new Modelo();
        obtenerTodosModelos();
        lis_marca = marcaEJB.findAllActivos();
    }
    
    public void obtenerTodosModelos() {
        try {

            lis_modelo = modeloEJB.findAll();

        } catch (Exception e) {
            System.out.println("Excepción en obtenerTodosModelos: " + e.toString());
            e.printStackTrace();
        }
    }
    
    public void agregarModelo(){
        try {
            modelo.setEstado("A");
        } catch (Exception e) {
        }
    }
    
    public void setearMarca() {
        try {
            marca = marcaEJB.find(codMarca);
            modelo.setCodMarca(marca);
        } catch (Exception e) {
            System.out.println("Error al buscar marca!" + e.toString());
        }
    }
    
    public void guardaModelo(int tipo){
        
         try {
            if (modelo != null) {
                modeloEJB.create(modelo);
                modelo = null;
                marca = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Registro Exitoso!"));
                init();
                if(tipo == 1){
                    RequestContext.getCurrentInstance().execute("PF('modalNuevo').hide();");
                }else{
                    RequestContext.getCurrentInstance().execute("PF('addModelo').hide();");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al guardaModelo:" + e.toString());
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al registrar...."));
        }
        
    }
    
    public void guardarEdicionModelo(){
        try {
            modeloEJB.edit(modelo);
            codMarca = null;

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Edicion Exitoso!"));
            init();
            RequestContext.getCurrentInstance().execute("PF('windowsEditar').hide();");

        } catch (Exception e) {
            System.out.println("Error al guardar Edicion: " + e.toString() + "Error: " + e.getMessage());
            e.getStackTrace();
        }
    }
    
}
