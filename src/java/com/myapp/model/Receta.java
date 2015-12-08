/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.model;

import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.RDFNode;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Karen
 */
@Table
public class Receta implements Serializable {

    @Id
    @Column
    private Literal nombreReceta;
    @Column
    private int cantidadIngredientes;
    @Column
    private RDFNode ingredientes;
    @Column
    private RDFNode modoPreparacion;
    @Column
    private int dificultad;
    @Column
    private int tiempo;
    @Column
    private int calorias;

    public Receta() {
    }

    public Receta(Literal nombreReceta, int catidadIngredientes, RDFNode ingredientes, RDFNode modoPreparacion, int dificultad, int tiempo, int calorias) {
        this.nombreReceta = nombreReceta;
        this.cantidadIngredientes = catidadIngredientes;
        this.ingredientes = ingredientes;
        this.modoPreparacion = modoPreparacion;
        this.dificultad = dificultad;
        this.tiempo = tiempo;
        this.calorias = calorias;
    }

    public Literal getNombreReceta() {
        return nombreReceta;
    }

    public void setNombreReceta(Literal nombreReceta) {
        this.nombreReceta = nombreReceta;
    }

    /**
     * @return the catidadIngredientes
     */
    public int getCantidadIngredientes() {
        return cantidadIngredientes;
    }

    /**
     * @param catidadIngredientes the catidadIngredientes to set
     */
    public void setCantidadIngredientes(int cantidadIngredientes) {
        this.cantidadIngredientes = cantidadIngredientes;
    }

    /**
     * @return the ingredientes
     */
    public RDFNode getIngredientes() {
        return ingredientes;
    }

    /**
     * @param ingredientes the ingredientes to set
     */
    public void setIngredientes(RDFNode ingredientes) {
        this.ingredientes = ingredientes;
    }

    /**
     * @return the modoPreparacion
     */
    public RDFNode getModoPreparacion() {
        return modoPreparacion;
    }

    /**
     * @param modoPreparacion the modoPreparacion to set
     */
    public void setModoPreparacion(RDFNode modoPreparacion) {
        this.modoPreparacion = modoPreparacion;
    }

    /**
     * @return the dificultad
     */
    public int getDificultad() {
        return dificultad;
    }

    /**
     * @param dificultad the dificultad to set
     */
    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }

    /**
     * @return the tiempo
     */
    public int getTiempo() {
        return tiempo;
    }

    /**
     * @param tiempo the tiempo to set
     */
    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    /**
     * @return the calorias
     */
    public int getCalorias() {
        return calorias;
    }

    /**
     * @param calorias the calorias to set
     */
    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

}
