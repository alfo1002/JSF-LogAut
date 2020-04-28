/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intranet.entity;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Walter Reyes
 */
@Entity
@Table(name = "det_rol_modulo", schema = "private")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetRolModulo.findAll", query = "SELECT d FROM DetRolModulo d")
    , @NamedQuery(name = "DetRolModulo.findById", query = "SELECT d FROM DetRolModulo d WHERE d.id = :id")
    , @NamedQuery(name = "DetRolModulo.findByEstado", query = "SELECT d FROM DetRolModulo d WHERE d.estado = :estado")})
public class DetRolModulo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 1)
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "cod_modulo", referencedColumnName = "codigo")
    @ManyToOne
    private Modulo codModulo;
    @JoinColumn(name = "cod_rol", referencedColumnName = "id")
    @ManyToOne
    private Rol codRol;

    public DetRolModulo() {
    }

    public DetRolModulo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Rol getCodRol() {
        return codRol;
    }

    public void setCodRol(Rol codRol) {
        this.codRol = codRol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetRolModulo)) {
            return false;
        }
        DetRolModulo other = (DetRolModulo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.factura.entity.DetRolModulo[ id=" + id + " ]";
    }
    
}
