/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intranet.mantenimiento.controller;

import com.intranet.config.Configuraciones;
import com.intranet.mantenimiento.ejb.DetUsuarioPermisosFacadeLocal;
import com.intranet.mantenimiento.ejb.ModuloFacadeLocal;
import com.intranet.mantenimiento.ejb.PermisosFacadeLocal;
import com.intranet.mantenimiento.ejb.RolFacadeLocal;
import com.intranet.mantenimiento.ejb.UsuarioFacadeLocal;
import com.intranet.entity.DetUsuarioPermisos;
import com.intranet.entity.Modulo;
import com.intranet.entity.Permisos;
import com.intranet.entity.Rol;
import com.intranet.entity.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author Walter Reyes
 */
@Named(value = "crudUsuariosController")
@ViewScoped
public class crudUsuariosController implements Serializable {

    private static Properties prop = new Properties();

    @EJB
    private UsuarioFacadeLocal usuarioEJB;

    @EJB
    private RolFacadeLocal rolEJB;

    @EJB
    private ModuloFacadeLocal moduloEJB;

    @EJB
    private PermisosFacadeLocal permisoEJB;

    @EJB
    private DetUsuarioPermisosFacadeLocal detalleUsuarioPermisosEJB;

    List<Usuario> lis_usuarios;

    private Usuario usuario;
    private Integer codigoRol;
    private Rol codigoRolAntiguo = null;
    private Rol rol;

    private List<Modulo> lis_modulos;
    private Modulo modulo;
    private Integer codigoModulo;

    private List<Rol> lis_roles;

    private DualListModel<Permisos> permisos = null;

    public Rol getCodigoRolAntiguo() {
        return codigoRolAntiguo;
    }

    public void setCodigoRolAntiguo(Rol codigoRolAntiguo) {
        this.codigoRolAntiguo = codigoRolAntiguo;
    }

    public DualListModel<Permisos> getPermisos() {
        return permisos;
    }

    public void setPermisos(DualListModel<Permisos> permisos) {
        this.permisos = permisos;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public Integer getCodigoModulo() {
        return codigoModulo;
    }

    public void setCodigoModulo(Integer codigoModulo) {
        this.codigoModulo = codigoModulo;
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

    public Integer getCodigoRol() {
        return codigoRol;
    }

    public void setCodigoRol(Integer codigoRol) {
        this.codigoRol = codigoRol;
    }

    public List<Rol> getLis_roles() {
        return lis_roles;
    }

    public void setLis_roles(List<Rol> lis_roles) {
        this.lis_roles = lis_roles;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        lis_roles = rolEJB.findAllActive();
        codigoRolAntiguo = this.usuario.getCodRol();
    }

    public List<Usuario> getLis_usuarios() {
        return lis_usuarios;
    }

    public void setLis_usuarios(List<Usuario> lis_usuarios) {
        this.lis_usuarios = lis_usuarios;
    }

    public crudUsuariosController() {
    }

    @PostConstruct
    public void init() {
        try {
            obtenerTodosUsuarios();

            List<Permisos> lista_permisos_disponibles = new ArrayList<>();
            List<Permisos> lista_permisos_seleccionados = new ArrayList<>();
            permisos = new DualListModel<>(lista_permisos_disponibles, lista_permisos_seleccionados);

        } catch (Exception e) {

            System.out.println("Error en init: " + e.toString());
            e.printStackTrace();
        }

    }

    public void obtenerTodosUsuarios() {
        try {

            lis_usuarios = usuarioEJB.findAllOrdenados();

        } catch (Exception e) {
            System.out.println("Excepción en crudUsuariosController: " + e.toString());
        }
    }

    public void agregarUsuario() {
        try {
            usuario = new Usuario();
            usuario.setEstado("A");

            lis_roles = rolEJB.findAllActive();
        } catch (Exception e) {
            System.out.println("Error agregando usuario: " + e.toString());
        }
    }

    public void setearRol() {
        try {
            rol = rolEJB.find(codigoRol);
            usuario.setCodRol(rol);
        } catch (Exception e) {
            System.out.println("Error al buscar rol!" + e.toString());
        }
    }

    public void guardarUsuario() {
        try {

            String clave = BCrypt.hashpw(usuario.getCedula() + "123*", BCrypt.gensalt());
            usuario.setClave(clave);
            Usuario usu = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            usuario.setCodCreador(usu.getId());
            usuarioEJB.create(usuario);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Registro Exitoso!"));
            init();
            RequestContext.getCurrentInstance().execute("PF('modalNuevoUsuario').hide();");

        } catch (Exception e) {
            System.out.println("Error al guardarUsuario:" + e.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al registrar...."));
        }
    }

    public void guardarEdicionUsuario() {
        try {
            usuarioEJB.edit(usuario);
            codigoRol = null;
            if (!codigoRolAntiguo.equals(usuario.getCodRol())) {
                eliminarPermisosPorCambioDeRol();
                usuario = null;
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Edicion Exitoso!"));
            init();
            RequestContext.getCurrentInstance().execute("PF('windowsEditar').hide();");

        } catch (Exception e) {
            System.out.println("Error al guardar Edicion: " + e.toString() + "Error: " + e.getMessage());
            e.getStackTrace();
        }
    }

    public void cargarPermisos(Usuario usu) {
        try {
            usuario = usu;
            System.out.println("Datos del usuario: " + usu.getId() + " --- " + usu.getNombre() + " ----- rol: " + usu.getCodRol().getId());
            lis_modulos = moduloEJB.findByCodigoRolUsuario(usu.getCodRol().getId());
            modulo = null;
            if (lis_modulos.size() > 0) {
                codigoModulo = lis_modulos.get(0).getCodigo();
                seleccionarModulo();
            }

        } catch (Exception e) {

            System.out.println("Error en cargarPermisos(Ususairo usu)" + e.toString());
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", e.toString()));
        }
    }

    public void seleccionarModulo() {
        try {
            if (codigoModulo != null) {
                modulo = moduloEJB.findbyIdActivos(codigoModulo);

                List<Permisos> lista_permisos_disponibles = new ArrayList<>();;
                List<Permisos> lista_permisos_seleccionados = new ArrayList<>();

                lista_permisos_disponibles = permisoEJB.findPermisosByCodigoModulo(codigoModulo);

                if (lista_permisos_disponibles.isEmpty()) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Modulo sin Permisos!"));
                }

                lista_permisos_seleccionados = permisoEJB.findPermisosByUsuarioModulo(codigoModulo, usuario.getId());

                int i = 0, j = 0;
                for (i = 0; i < lista_permisos_disponibles.size(); i++) {
                    System.out.println("Lista: " + lista_permisos_disponibles.get(i) + " --- " + lista_permisos_disponibles.get(i).getNombre());
                    for (j = 0; j < lista_permisos_seleccionados.size(); j++) {
                        if (lista_permisos_disponibles.get(i).getId() == lista_permisos_seleccionados.get(j).getId()) {
                            lista_permisos_disponibles.remove(i);
                        }
                    }
                }
                permisos = new DualListModel<>(lista_permisos_disponibles, lista_permisos_seleccionados);
            } else {
                List<Permisos> lista_permisos_disponibles = new ArrayList<>();
                List<Permisos> lista_permisos_seleccionados = new ArrayList<>();
                permisos = new DualListModel<>(lista_permisos_disponibles, lista_permisos_seleccionados);
            }

        } catch (Exception e) {

            System.out.println("Error en seleccionarModulo() " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void eliminarPermisosPorCambioDeRol() {
        try {

            permisoEJB.eliminarPermisosPorUsuario(usuario.getId());
        } catch (Exception e) {
            System.out.println("Error en eliminarPermisos: " + e.toString() + " --- " + e.getMessage());
            e.getStackTrace();
        }
    }

    public void onTransfer(TransferEvent event) {
        try {
            if (event.isAdd()) {
                for (Object item : event.getItems()) {
                    Permisos permiso = permisoEJB.find(Integer.parseInt((String) item));
                    DetUsuarioPermisos det_usu_permiso = new DetUsuarioPermisos();
                    det_usu_permiso.setCodPermiso(permiso);
                    det_usu_permiso.setCodUsuario(usuario);
                    detalleUsuarioPermisosEJB.create(det_usu_permiso); //permisoEJB.create(permiso);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Permiso Añadido"));
                }
            } else {
                if (event.isRemove()) {
                    for (Object item : event.getItems()) {
                        Permisos permiso = permisoEJB.find(Integer.parseInt((String) item));
                        permisoEJB.eliminarPermisosPorUsuarioyPermiso(usuario, permiso);
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Permiso Eliminado"));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.toString() + " ---- " + e.getMessage());
            e.getStackTrace();
        }
    }

}
