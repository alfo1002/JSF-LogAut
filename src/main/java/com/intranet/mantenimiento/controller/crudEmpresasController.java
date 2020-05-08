/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intranet.mantenimiento.controller;

import com.intranet.entity.Empresa;
import com.intranet.mantenimiento.ejb.EmpresaFacadeLocal;
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
@Named(value = "crudEmpresasController")
@ViewScoped
public class crudEmpresasController implements Serializable {

    @EJB
    private EmpresaFacadeLocal empresaEJB;

    private List<Empresa> lis_empresas;

    private Empresa empresa;

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Empresa> getLis_empresas() {
        return lis_empresas;
    }

    public void setLis_empresas(List<Empresa> lis_empresas) {
        this.lis_empresas = lis_empresas;
    }

    public crudEmpresasController() {
    }

    @PostConstruct
    public void init() {
        empresa = new Empresa();
        obtenerTodasEmpresas();
    }

    public void obtenerTodasEmpresas() {
        try {

            lis_empresas = empresaEJB.findAll();

        } catch (Exception e) {
            System.out.println("Excepción en obtenerTodasEmpresas: " + e.toString());
            e.printStackTrace();
        }
    }

    public void agregarEmpresa() {
        try {
            //empresa = new Empresa();
            empresa.setEstado("A");
            empresa.setContador(0);

        } catch (Exception e) {
            System.out.println("Error agregando usuario: " + e.toString());
        }
    }

    /*
    tipo = 1 para guardar localmente
    tipo <> 1 para guardar desde modal externo
    */
    public void guardaEmpresa(int tipo) {

        try {
            if (empresa != null) {
                empresaEJB.create(empresa);

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Registro Exitoso!"));
                init();
                if(tipo == 1){
                    RequestContext.getCurrentInstance().execute("PF('modalNuevo').hide();");
                }else{
                    RequestContext.getCurrentInstance().execute("PF('addEmpresa').hide();");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al guardaEmpresa:" + e.toString());
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al registrar...."));
        }
    }
    
    public void guardarEdicionEmpresa(){
         try {
            empresaEJB.edit(empresa);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Edicion Exitosa!"));
            init();
            RequestContext.getCurrentInstance().execute("PF('windowsEditar').hide();");

        } catch (Exception e) {
            System.out.println("Error al guardar Edicion: " + e.toString() + "Error: " + e.getMessage());
            e.getStackTrace();
        }
    }

}
