/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intranet.entity;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Walter Reyes
 */
@Entity
@Table(name = "det_usuario_permisos", schema = "private")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetUsuarioPermisos.findAll", query = "SELECT d FROM DetUsuarioPermisos d")
    , @NamedQuery(name = "DetUsuarioPermisos.findByCodigo", query = "SELECT d FROM DetUsuarioPermisos d WHERE d.codigo = :codigo")})
public class DetUsuarioPermisos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @JoinColumn(name="cod_usu", referencedColumnName = "id")
    @ManyToOne
    private Usuario codUsuario;
    @JoinColumn(name = "cod_per", referencedColumnName = "id")
    @ManyToOne
    private Permisos codPermiso;

    public Usuario getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(Usuario codUsuario) {
        this.codUsuario = codUsuario;
    }

    public Permisos getCodPermiso() {
        return codPermiso;
    }

    public void setCodPermiso(Permisos codPermiso) {
        this.codPermiso = codPermiso;
    }
    

    
    
    public DetUsuarioPermisos() {
    }

    public DetUsuarioPermisos(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "DetUsuarioPermisos{" + "codigo=" + codigo + ", codUsuario=" + codUsuario + ", codPermiso=" + codPermiso + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.codigo);
        hash = 67 * hash + Objects.hashCode(this.codUsuario);
        hash = 67 * hash + Objects.hashCode(this.codPermiso);
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
        final DetUsuarioPermisos other = (DetUsuarioPermisos) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        if (!Objects.equals(this.codUsuario, other.codUsuario)) {
            return false;
        }
        if (!Objects.equals(this.codPermiso, other.codPermiso)) {
            return false;
        }
        return true;
    }

    

    
    
}
