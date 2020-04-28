/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intranet.config;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Walter Reyes
 */
@Named(value = "configuraciones")
@ViewScoped
public class Configuraciones implements Serializable {

    /**
     * Creates a new instance of Configuraciones
     */
    private static String path_ini = null;

    @PostConstruct
    public void init() {

    }

    public Configuraciones() {
    }

    public String retornarVariable(String var) {
        String nombre = "";
        try {
            InputStream inputStream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/WEB-INF/parametros.properties");

            Properties propiedades = new Properties();
            propiedades.load(inputStream);
            nombre = propiedades.getProperty(var);

        } catch (Exception e) {
            System.out.println("Error: " + e.toString() + "   -  " + e.getMessage());
            e.printStackTrace();
        }
        
        return nombre;
    }

}
