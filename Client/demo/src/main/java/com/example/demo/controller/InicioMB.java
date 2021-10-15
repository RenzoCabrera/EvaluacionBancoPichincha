package com.example.demo.controller;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Component
@ViewScoped
public class InicioMB implements Serializable {

    Logger logger = LoggerFactory.getLogger(InicioMB.class);

    private String holaMundo = "";
    private MenuModel model = new DefaultMenuModel();

    @PostConstruct
    public void init(){
        //Hacemos un logeo para revisar que no se este llamando dos veces el
        //Postconstruct
        logger.info("Entra al postconstruct");
        //Inicializamos la variable que mostraremos en la pagina
        holaMundo = "Da clic aqui!";
        String com;
        model = new DefaultMenuModel();
        DefaultMenuItem item = new DefaultMenuItem();
        item.setValue("Categorias");
        item.setTitle("modulo categorías");
         com="#{frmCategorias.redireccion}";
        item.setCommand(com);

        item.setUpdate("opcionesMenu");
        item.setAjax(false);

        model.addElement(item);


        item = new DefaultMenuItem();
        item.setValue("Bienes");
        item.setTitle("modulo Bienes");
         com="#{frmBienes.redireccion}";
        item.setCommand(com);

        item.setUpdate("opcionesMenu");
        item.setAjax(false);
        //item.setIcon(modulos.getIcono());

        model.addElement(item);

    }

    /**
     * Metodo que sera llamado desde un boton de la vista
     * Para mostrar que ya esta funcionando el primefaces
     */
    public void saludar(){
        holaMundo = "Hola Mundo!!";
    }

    //<editor-fold desc="Accesores" defaultstate="collapsed">
    public String getHolaMundo() {
        return holaMundo;
    }

    public void setHolaMundo(String holaMundo) {
        this.holaMundo = holaMundo;
    }

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }
}