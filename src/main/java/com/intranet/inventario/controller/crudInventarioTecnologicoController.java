/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intranet.inventario.controller;

import com.intranet.entity.Color;
import com.intranet.entity.Empresa;
import com.intranet.entity.Equipo;
import com.intranet.entity.Marca;
import com.intranet.entity.Modelo;
import com.intranet.entity.Sector;
import com.intranet.entity.TipoEquipo;
import com.intranet.entity.Usuario;
import com.intranet.mantenimiento.controller.crudEmpresasController;
import com.intranet.mantenimiento.controller.crudMarcasController;
import com.intranet.mantenimiento.controller.crudModelosController;
import com.intranet.mantenimiento.controller.crudSectoresController;
import com.intranet.mantenimiento.controller.crudTipoEquipoController;
import com.intranet.mantenimiento.controller.crudUsuariosController;
import com.intranet.mantenimiento.ejb.ColorFacadeLocal;
import com.intranet.mantenimiento.ejb.EmpresaFacadeLocal;
import com.intranet.mantenimiento.ejb.EquipoFacadeLocal;
import com.intranet.mantenimiento.ejb.MarcaFacadeLocal;
import com.intranet.mantenimiento.ejb.ModeloFacadeLocal;
import com.intranet.mantenimiento.ejb.SectorFacadeLocal;
import com.intranet.mantenimiento.ejb.TipoEquipoFacadeLocal;
import com.intranet.mantenimiento.ejb.UsuarioFacadeLocal;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Walter Reyes
 */
@Named(value = "crudInventarioTecnologicoController")
@ViewScoped
public class crudInventarioTecnologicoController implements Serializable {

    @EJB
    private EquipoFacadeLocal equipoEJB;

    @EJB
    private EmpresaFacadeLocal empresaEJB;

    @EJB
    private TipoEquipoFacadeLocal tipoEquipoEJB;

    @EJB
    private MarcaFacadeLocal marcaEJB;

    @EJB
    private ModeloFacadeLocal modeloEJB;

    @EJB
    private ColorFacadeLocal colorEJB;

    @EJB
    private UsuarioFacadeLocal usuarioEJB;

    @EJB
    private SectorFacadeLocal sectorEJB;

    @Inject
    private crudEmpresasController crudEmpresasC;

    @Inject
    private crudMarcasController crudMarcasC;

    @Inject
    private crudModelosController crudModelosC;

    @Inject
    private crudUsuariosController crudUsuariosC;
    
    @Inject
    private crudSectoresController crudSectoresC;
    
    @Inject
    private crudTipoEquipoController crudTipoEquipoC;

    private List<Equipo> lis_equipos;
    private List<Empresa> lis_empresa;
    private List<TipoEquipo> lis_tipo_equipo;
    private List<Marca> lis_marcas;
    private List<Modelo> lis_modelos;
    private List<Color> lis_color;
    private List<Usuario> lis_usuario;
    private List<Sector> lis_sector;

    private Integer codEmpresa = 0;
    private Integer codTipoEquipo = 0;
    private Integer codMarca = 0;
    private Integer codModelo;
    private Integer codColor;
    private Integer codUsuario;
    private Integer codSector;

    private Empresa empresa;
    private Equipo equipo;
    private TipoEquipo tipoEquipo;
    private Marca marca = null;
    private Modelo modelo;
    private Color color;
    private Usuario usuario;
    private Sector sector;

    private Empresa empresaContador;

    public crudTipoEquipoController getCrudTipoEquipoC() {
        return crudTipoEquipoC;
    }

    public void setCrudTipoEquipoC(crudTipoEquipoController crudTipoEquipoC) {
        this.crudTipoEquipoC = crudTipoEquipoC;
    }
    
    public crudSectoresController getCrudSectoresC() {
        return crudSectoresC;
    }

    public void setCrudSectoresC(crudSectoresController crudSectoresC) {
        this.crudSectoresC = crudSectoresC;
    }
    
    public crudUsuariosController getCrudUsuariosC() {
        return crudUsuariosC;
    }

    public void setCrudUsuariosC(crudUsuariosController crudUsuariosC) {
        this.crudUsuariosC = crudUsuariosC;
    }

    public crudModelosController getCrudModelosC() {
        return crudModelosC;
    }

    public void setCrudModelosC(crudModelosController crudModelosC) {
        this.crudModelosC = crudModelosC;
    }

    public crudMarcasController getCrudMarcasC() {
        return crudMarcasC;
    }

    public void setCrudMarcasC(crudMarcasController crudMarcasC) {
        this.crudMarcasC = crudMarcasC;
    }

    public crudEmpresasController getCrudEmpresasC() {
        return crudEmpresasC;
    }

    public void setCrudEmpresasC(crudEmpresasController crudEmpresasC) {
        this.crudEmpresasC = crudEmpresasC;
    }

    public Empresa getEmpresaContador() {
        return empresaContador;
    }

    public void setEmpresaContador(Empresa empresaContador) {
        this.empresaContador = empresaContador;
    }

    public List<Sector> getLis_sector() {
        return lis_sector;
    }

    public void setLis_sector(List<Sector> lis_sector) {
        this.lis_sector = lis_sector;
    }

    public Integer getCodSector() {
        return codSector;
    }

    public void setCodSector(Integer codSector) {
        this.codSector = codSector;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Integer getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(Integer codUsuario) {
        this.codUsuario = codUsuario;
    }

    public List<Usuario> getLis_usuario() {
        return lis_usuario;
    }

    public void setLis_usuario(List<Usuario> lis_usuario) {
        this.lis_usuario = lis_usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Color> getLis_color() {
        return lis_color;
    }

    public void setLis_color(List<Color> lis_color) {
        this.lis_color = lis_color;
    }

    public Integer getCodColor() {
        return codColor;
    }

    public void setCodColor(Integer codColor) {
        this.codColor = codColor;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public List<Modelo> getLis_modelos() {
        return lis_modelos;
    }

    public void setLis_modelos(List<Modelo> lis_modelos) {
        this.lis_modelos = lis_modelos;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public Integer getCodModelo() {
        return codModelo;
    }

    public void setCodModelo(Integer codModelo) {
        this.codModelo = codModelo;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public List<Marca> getLis_marcas() {
        return lis_marcas;
    }

    public void setLis_marcas(List<Marca> lis_marcas) {
        this.lis_marcas = lis_marcas;
    }

    public Integer getCodMarca() {
        return codMarca;
    }

    public void setCodMarca(Integer codMarca) {
        this.codMarca = codMarca;
    }

    public TipoEquipo getTipoEquipo() {
        return tipoEquipo;
    }

    public void setTipoEquipo(TipoEquipo tipoEquipo) {
        this.tipoEquipo = tipoEquipo;
    }

    public List<TipoEquipo> getLis_tipo_equipo() {
        return lis_tipo_equipo;
    }

    public void setLis_tipo_equipo(List<TipoEquipo> lis_tipo_equipo) {
        this.lis_tipo_equipo = lis_tipo_equipo;
    }

    public Integer getCodTipoEquipo() {
        return codTipoEquipo;
    }

    public void setCodTipoEquipo(Integer codTipoEquipo) {
        this.codTipoEquipo = codTipoEquipo;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Integer getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(Integer codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public List<Empresa> getLis_empresa() {
        return lis_empresa;
    }

    public void setLis_empresa(List<Empresa> lis_empresa) {
        this.lis_empresa = lis_empresa;
    }

    public List<Equipo> getLis_equipos() {
        return lis_equipos;
    }

    public void setLis_equipos(List<Equipo> lis_equipos) {
        this.lis_equipos = lis_equipos;
    }

    @PostConstruct
    public void init() {

        obtenerTodosEquipos();

    }

    public void obtenerTodosEquipos() {
        try {

            //Para DataTable Principal
            lis_equipos = equipoEJB.findAll();

            //Para Crear Equipo
            lis_empresa = empresaEJB.findAllActivas();
            lis_tipo_equipo = tipoEquipoEJB.findAllActivos();
            lis_marcas = marcaEJB.findAllActivos();
            lis_color = colorEJB.findAllActivos();
            lis_usuario = usuarioEJB.findAllOrdenados();
            lis_sector = sectorEJB.findAllActivos();

        } catch (Exception e) {
            System.out.println("Excepción en obtenerTodosEquipos: " + e.toString());
        }
    }

    public void agregarEquipo() {
        try {
            equipo = new Equipo();
            equipo.setEstado("A");
            limpiarCodigos();

        } catch (Exception e) {
        }
    }

    public void guardarEquipo() {
        try {
            if (equipo != null) {

                Date d = new Date();
                equipo.setFechaRegistro(d);
                equipoEJB.create(equipo);
                equipo = null;

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Exitoso", ""));
                init();
                RequestContext.getCurrentInstance().execute("PF('modalNuevo').hide();");
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al registrar Equipo!", ""));
            System.out.println("Error al guardar equipo: " + e.toString());
            e.printStackTrace();
        }

    }

    public crudInventarioTecnologicoController() {
    }

    public void setearEmpresa() {
        try {
            empresa = empresaEJB.find(codEmpresa);
            equipo.setCodEmpresa(empresa);

            /*if (codTipoEquipo > 0) {
                equipo.setCodificacion(generarCodificador());
            }*/
        } catch (Exception e) {
            System.out.println("Error al buscar empresa!" + e.toString());
            e.printStackTrace();
        }
    }

    public void setearTipoEquipo() {
        try {
            tipoEquipo = tipoEquipoEJB.find(codTipoEquipo);
            equipo.setCodTipoEquipo(tipoEquipo);

            if (codEmpresa > 0) {

                equipo.setCodificacion(generarCodificador());

            }
        } catch (Exception e) {
            System.out.println("Error al buscar Tipo de Equipo!" + e.toString());
            e.printStackTrace();
        }
    }

    public void setearTipoMarca() {
        try {
            marca = marcaEJB.find(codMarca);
            equipo.setCodMarca(marca);
            lis_modelos = modeloEJB.findByCodigoMarca(marca);
        } catch (Exception e) {
            System.out.println("Error al buscar Marca! :" + e.toString());
            e.printStackTrace();
        }
    }

    public void setearTipoModelo() {
        try {
            modelo = modeloEJB.find(codModelo);
            equipo.setCodModelo(modelo);
        } catch (Exception e) {
            System.out.println("Error al buscar Modelo! :" + e.toString());
            e.printStackTrace();
        }
    }

    public void setearColor() {
        try {
            color = colorEJB.find(codColor);
            equipo.setCodColor(color);
        } catch (Exception e) {
            System.out.println("Error al buscar Color! :" + e.toString());
            e.printStackTrace();
        }
    }

    public void setearUsuario() {
        try {
            usuario = usuarioEJB.find(codUsuario);
            equipo.setCodUsuario(usuario);
        } catch (Exception e) {
            System.out.println("Error al buscar usuario! :" + e.toString());
            e.printStackTrace();
        }
    }

    public void setearSector() {
        try {
            sector = sectorEJB.find(codSector);
            equipo.setCodSector(sector);
        } catch (Exception e) {
            System.out.println("Error al buscar sector! :" + e.toString());
            e.printStackTrace();
        }
    }

    public String generarCodificador() {
        String cadena = "";
        try {
            empresaContador = empresaEJB.find(codEmpresa);
            String acr_emp = empresaContador.getAcronimo();
            int num = empresaContador.getContador() + 1;
            empresaContador.setContador(num);
            empresaEJB.edit(empresaContador);

            TipoEquipo teq = tipoEquipoEJB.find(codTipoEquipo);
            String acr_tequipo = teq.getAcronimo();

            int size = Integer.toString(num).length();

            for (int i = size; i <= 4; i++) {
                cadena = cadena + "0";
            }
            cadena = acr_emp + "-" + acr_tequipo + "-" + cadena + num;
        } catch (Exception e) {
            System.out.println("Error generando Codificador: " + e.toString() + " --- " + e.getMessage());
            e.printStackTrace();
        }
        return cadena;
    }

    public void guardarEdicionEquipo() {
        try {
            equipoEJB.edit(equipo);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Datos Actualizados...", ""));
            init();
            RequestContext.getCurrentInstance().execute("PF('windowsEditar').hide();");

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Actualizar Equipo...", ""));
            System.out.println("Error al guardar Edicion: " + e.toString() + "Error: " + e.getMessage());
            e.getStackTrace();
        }
    }

    public void addEmpresa() {
        try {
            crudEmpresasC = new crudEmpresasController();
        } catch (Exception e) {
        }
    }

    public void registrarEmpresa() {
        try {
            crudEmpresasC.guardaEmpresa(0);
            lis_empresa = empresaEJB.findAllActivas();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addMarca() {
        try {
            crudMarcasC = new crudMarcasController();
        } catch (Exception e) {
        }
    }

    public void registrarMarca() {
        try {
            crudMarcasC.guardaMarca(0); //exterior
            lis_marcas = marcaEJB.findAllActivos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addModelo() {
        try {
            if (marca != null) {
                crudModelosC = new crudModelosController();
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Seleccione una Marca!"));
            }
        } catch (Exception e) {
        }
    }

    public void registrarModelo() {
        try {
            crudModelosC.guardaModelo(0); //exterior
            lis_modelos = modeloEJB.findByCodigoMarca(marca);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void limpiarCodigos() {
        codEmpresa = 0;
        codTipoEquipo = 0;
        codMarca = 0;
        codModelo = 0;
        codColor = 0;
        codUsuario = 0;
        codSector = 0;
    }

    public void addUsuario() {
        try {

            crudUsuariosC = new crudUsuariosController();

        } catch (Exception e) {
        }
    }

    public void registrarUsuario() {
        try {
            crudUsuariosC.guardarUsuario(0); //exterior
            lis_usuario = usuarioEJB.findAllOrdenados();//dByCodigoMarca(marca);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void addSector() {
        try {
            crudSectoresC = new crudSectoresController();

        } catch (Exception e) {
        }
    }

    public void registrarSector() {
        try {
            crudSectoresC.guardaSector(0); //exterior
            lis_sector = sectorEJB.findAllActivos();//

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void addTipoEquipo() {
        try {
            crudTipoEquipoC = new crudTipoEquipoController();

        } catch (Exception e) {
        }
    }

    public void registrarTipoEquipo() {
        try {
            crudTipoEquipoC.guardaTipoEquipo(0);//exterior
            lis_tipo_equipo = tipoEquipoEJB.findAllActivos();//

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
