/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intranet.mantenimiento.controller;

import com.intranet.mantenimiento.ejb.UsuarioFacadeLocal;
import com.intranet.entity.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author Walter Reyes
 */
@Named
@ViewScoped
public class loginController implements Serializable{

    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    
    private String login;
    private String clave;
    
    
    
    @PostConstruct
    public void init(){
        
    }
    
    public String iniciarSesion(){
        String redireccion = "";
        
        if(this.login != "" && this.clave != ""){
            Usuario usu = null;
            usu = usuarioEJB.findByCedulax(this.login);
            System.out.println("Datos: " + usu);
            if(usu != null){
                
                if(BCrypt.checkpw(this.clave, usu.getClave())){
                    System.out.println("Login Exitoso con Usuario:" + usu.getNombre()+ " " + usu.getApellido() + " y con rol:" +usu.getCodRol().getNombre());
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usu);
                    redireccion = "/private/principal/principal.xhtml";
                }else{
                    System.out.println("Login Erroneo!.....");
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Datos Incorrectos.",""));
                }
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Datos Incorrectos.",""));
                System.out.println("Usuario no existe!");
            }
        }
        
        return redireccion;
    }
    
    public void verificarSesion(){
        
        try {
            Usuario usu = null;
             usu = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            if(usu != null){
                FacesContext.getCurrentInstance().getExternalContext().redirect("faces/private/principal/principal.xhtml");
            }
            
        } catch (Exception e) {
            
            System.out.println("Error en la verificacion de sesion login");
        }
        
    }
    

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
    
}
