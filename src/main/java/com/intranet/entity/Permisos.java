/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intranet.entity;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Walter Reyes
 */
@Entity
@Table(name = "permisos", schema = "private")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Permisos.findAll", query = "SELECT p FROM Permisos p")
    , @NamedQuery(name = "Permisos.findById", query = "SELECT p FROM Permisos p WHERE p.id = :id")
    , @NamedQuery(name = "Permisos.findByNombre", query = "SELECT p FROM Permisos p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Permisos.findByDescripcion", query = "SELECT p FROM Permisos p WHERE p.descripcion = :descripcion")
    , @NamedQuery(name = "Permisos.findByIcon", query = "SELECT p FROM Permisos p WHERE p.icon = :icon")
    , @NamedQuery(name = "Permisos.findByUrl", query = "SELECT p FROM Permisos p WHERE p.url = :url")
    , @NamedQuery(name = "Permisos.findByEstado", query = "SELECT p FROM Permisos p WHERE p.estado = :estado")})
public class Permisos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 250)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 50)
    @Column(name = "icon")
    private String icon;
    @Size(max = 100)
    @Column(name = "url")
    private String url;
    @Size(max = 1)
    @Column(name = "estado")
    private String estado;
    @Size(max = 100)
    @Column(name = "img")
    private String img;
    @JoinColumn(name = "cod_modulo", referencedColumnName = "codigo")
    @ManyToOne
    private Modulo codModulo;
    @OneToMany(mappedBy = "codPermiso")
    private List<DetUsuarioPermisos> detUsuarioPermiso;
    
    public Permisos() {
    }

    public Permisos(Integer id) {
        this.id = id;
    }

    public List<DetUsuarioPermisos> getDetUsuarioPermiso() {
        return detUsuarioPermiso;
    }

    public void setDetUsuarioPermiso(List<DetUsuarioPermisos> detUsuarioPermiso) {
        this.detUsuarioPermiso = detUsuarioPermiso;
    }

    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Modulo getCodModulo() {
        return codModulo;
    }

    public void setCodModulo(Modulo codModulo) {
        this.codModulo = codModulo;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.id);
        hash = 31 * hash + Objects.hashCode(this.nombre);
        hash = 31 * hash + Objects.hashCode(this.descripcion);
        hash = 31 * hash + Objects.hashCode(this.icon);
        hash = 31 * hash + Objects.hashCode(this.url);
        hash = 31 * hash + Objects.hashCode(this.estado);
        hash = 31 * hash + Objects.hashCode(this.img);
        hash = 31 * hash + Objects.hashCode(this.codModulo);
        hash = 31 * hash + Objects.hashCode(this.detUsuarioPermiso);
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
        final Permisos other = (Permisos) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        if (!Objects.equals(this.icon, other.icon)) {
            return false;
        }
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        if (!Objects.equals(this.img, other.img)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.codModulo, other.codModulo)) {
            return false;
        }
        if (!Objects.equals(this.detUsuarioPermiso, other.detUsuarioPermiso)) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() {
        return "Permisos{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", icon=" + icon + ", url=" + url + ", estado=" + estado + ", codModulo=" + codModulo + ", detUsuarioPermiso=" + detUsuarioPermiso + '}';
    }
    
}
