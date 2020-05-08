/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intranet.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Walter Reyes
 */
@Entity
@Table(name = "tipo_equipo", schema = "private")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoEquipo.findAll", query = "SELECT t FROM TipoEquipo t")
    , @NamedQuery(name = "TipoEquipo.findByCodigo", query = "SELECT t FROM TipoEquipo t WHERE t.codigo = :codigo")
    , @NamedQuery(name = "TipoEquipo.findByNombre", query = "SELECT t FROM TipoEquipo t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "TipoEquipo.findByDescripcion", query = "SELECT t FROM TipoEquipo t WHERE t.descripcion = :descripcion")
    , @NamedQuery(name = "TipoEquipo.findByEstado", query = "SELECT t FROM TipoEquipo t WHERE t.estado = :estado")})
public class TipoEquipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Size(max = 150)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 150)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 1)
    @Column(name = "estado")
    private String estado;
    @Size(max = 20)
    @Column(name = "acronimo")
    private String acronimo;
    
    @OneToMany(mappedBy = "codTipoEquipo")
    private List<Equipo> equipoList;



    public String getAcronimo() {
        return acronimo;
    }

    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }

    public TipoEquipo() {
    }

    public TipoEquipo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<Equipo> getEquipoList() {
        return equipoList;
    }

    public void setEquipoList(List<Equipo> equipoList) {
        this.equipoList = equipoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoEquipo)) {
            return false;
        }
        TipoEquipo other = (TipoEquipo) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.intranet.entity.TipoEquipo[ codigo=" + codigo + " ]";
    }
    
}
