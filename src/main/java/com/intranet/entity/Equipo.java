/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intranet.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Walter Reyes
 */
@Entity
@Table(name = "equipo", schema = "private")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Equipo.findAll", query = "SELECT e FROM Equipo e")
    , @NamedQuery(name = "Equipo.findByCodigo", query = "SELECT e FROM Equipo e WHERE e.codigo = :codigo")
    , @NamedQuery(name = "Equipo.findByCodificacion", query = "SELECT e FROM Equipo e WHERE e.codificacion = :codificacion")
    , @NamedQuery(name = "Equipo.findByDetalle", query = "SELECT e FROM Equipo e WHERE e.detalle = :detalle")
    , @NamedQuery(name = "Equipo.findBySerie", query = "SELECT e FROM Equipo e WHERE e.serie = :serie")
    , @NamedQuery(name = "Equipo.findByMac", query = "SELECT e FROM Equipo e WHERE e.mac = :mac")
    , @NamedQuery(name = "Equipo.findByFechaRegistro", query = "SELECT e FROM Equipo e WHERE e.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Equipo.findByVidaUtil", query = "SELECT e FROM Equipo e WHERE e.vidaUtil = :vidaUtil")
    , @NamedQuery(name = "Equipo.findByFechaHoy", query = "SELECT e FROM Equipo e WHERE e.fechaHoy = :fechaHoy")
    , @NamedQuery(name = "Equipo.findByEstado", query = "SELECT e FROM Equipo e WHERE e.estado = :estado")
    , @NamedQuery(name = "Equipo.findByInforme", query = "SELECT e FROM Equipo e WHERE e.informe = :informe")})
public class Equipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Size(max = 150)
    @Column(name = "codificacion")
    private String codificacion;
    @Size(max = 350)
    @Column(name = "detalle")
    private String detalle;
    @Size(max = 150)
    @Column(name = "serie")
    private String serie;
    @Size(max = 100)
    @Column(name = "mac")
    private String mac;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "vida_util")
    private Double vidaUtil;
    @Column(name = "fecha_hoy")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoy;
    @Size(max = 1)
    @Column(name = "estado")
    private String estado;
    @Size(max = 1)
    @Column(name = "informe")
    private String informe;
    @JoinColumn(name = "cod_color", referencedColumnName = "codigo")
    @ManyToOne
    private Color codColor;
    @JoinColumn(name = "cod_marca", referencedColumnName = "codigo")
    @ManyToOne
    private Marca codMarca;
    @JoinColumn(name = "cod_modelo", referencedColumnName = "codigo")
    @ManyToOne
    private Modelo codModelo;
    @JoinColumn(name = "cod_sector", referencedColumnName = "codigo")
    @ManyToOne
    private Sector codSector;
    @JoinColumn(name = "cod_tipo_equipo", referencedColumnName = "codigo")
    @ManyToOne
    private TipoEquipo codTipoEquipo;
    
    @JoinColumn(name = "cod_usuario", referencedColumnName = "id")
    @ManyToOne
    private Usuario codUsuario;

    @JoinColumn(name = "cod_empresa", referencedColumnName = "codigo")
    @ManyToOne
    private Empresa codEmpresa;



    public Empresa getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(Empresa codEmpresa) {
        this.codEmpresa = codEmpresa;
    }
    
    public Usuario getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(Usuario codUsuario) {
        this.codUsuario = codUsuario;
    }
    
    public Equipo() {
    }

    public Equipo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getCodificacion() {
        return codificacion;
    }

    public void setCodificacion(String codificacion) {
        this.codificacion = codificacion;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Double getVidaUtil() {
        return vidaUtil;
    }

    public void setVidaUtil(Double vidaUtil) {
        this.vidaUtil = vidaUtil;
    }

    public Date getFechaHoy() {
        return fechaHoy;
    }

    public void setFechaHoy(Date fechaHoy) {
        this.fechaHoy = fechaHoy;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getInforme() {
        return informe;
    }

    public void setInforme(String informe) {
        this.informe = informe;
    }

    public Color getCodColor() {
        return codColor;
    }

    public void setCodColor(Color codColor) {
        this.codColor = codColor;
    }

    public Marca getCodMarca() {
        return codMarca;
    }

    public void setCodMarca(Marca codMarca) {
        this.codMarca = codMarca;
    }

    public Modelo getCodModelo() {
        return codModelo;
    }

    public void setCodModelo(Modelo codModelo) {
        this.codModelo = codModelo;
    }

    public Sector getCodSector() {
        return codSector;
    }

    public void setCodSector(Sector codSector) {
        this.codSector = codSector;
    }

    public TipoEquipo getCodTipoEquipo() {
        return codTipoEquipo;
    }

    public void setCodTipoEquipo(TipoEquipo codTipoEquipo) {
        this.codTipoEquipo = codTipoEquipo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.codigo);
        hash = 89 * hash + Objects.hashCode(this.codificacion);
        hash = 89 * hash + Objects.hashCode(this.detalle);
        hash = 89 * hash + Objects.hashCode(this.serie);
        hash = 89 * hash + Objects.hashCode(this.mac);
        hash = 89 * hash + Objects.hashCode(this.fechaRegistro);
        hash = 89 * hash + Objects.hashCode(this.vidaUtil);
        hash = 89 * hash + Objects.hashCode(this.fechaHoy);
        hash = 89 * hash + Objects.hashCode(this.estado);
        hash = 89 * hash + Objects.hashCode(this.informe);
        hash = 89 * hash + Objects.hashCode(this.codColor);
        hash = 89 * hash + Objects.hashCode(this.codMarca);
        hash = 89 * hash + Objects.hashCode(this.codModelo);
        hash = 89 * hash + Objects.hashCode(this.codSector);
        hash = 89 * hash + Objects.hashCode(this.codTipoEquipo);
        hash = 89 * hash + Objects.hashCode(this.codUsuario);
        hash = 89 * hash + Objects.hashCode(this.codEmpresa);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Equipo other = (Equipo) obj;
        if (!Objects.equals(this.codificacion, other.codificacion)) {
            return false;
        }
        if (!Objects.equals(this.detalle, other.detalle)) {
            return false;
        }
        if (!Objects.equals(this.serie, other.serie)) {
            return false;
        }
        if (!Objects.equals(this.mac, other.mac)) {
            return false;
        }
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        if (!Objects.equals(this.fechaRegistro, other.fechaRegistro)) {
            return false;
        }
        if (!Objects.equals(this.vidaUtil, other.vidaUtil)) {
            return false;
        }
        if (!Objects.equals(this.fechaHoy, other.fechaHoy)) {
            return false;
        }
        if (!Objects.equals(this.informe, other.informe)) {
            return false;
        }
        if (!Objects.equals(this.codColor, other.codColor)) {
            return false;
        }
        if (!Objects.equals(this.codMarca, other.codMarca)) {
            return false;
        }
        if (!Objects.equals(this.codModelo, other.codModelo)) {
            return false;
        }
        if (!Objects.equals(this.codSector, other.codSector)) {
            return false;
        }
        if (!Objects.equals(this.codTipoEquipo, other.codTipoEquipo)) {
            return false;
        }
        if (!Objects.equals(this.codUsuario, other.codUsuario)) {
            return false;
        }
        if (!Objects.equals(this.codEmpresa, other.codEmpresa)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Equipo{" + "codigo=" + codigo + ", codificacion=" + codificacion + ", detalle=" + detalle + ", serie=" + serie + ", mac=" + mac + ", fechaRegistro=" + fechaRegistro + ", vidaUtil=" + vidaUtil + ", fechaHoy=" + fechaHoy + ", estado=" + estado + ", informe=" + informe + ", codColor=" + codColor + ", codMarca=" + codMarca + ", codModelo=" + codModelo + ", codSector=" + codSector + ", codTipoEquipo=" + codTipoEquipo + ", codUsuario=" + codUsuario + ", codEmpresa=" + codEmpresa + '}';
    }

   
    
}
