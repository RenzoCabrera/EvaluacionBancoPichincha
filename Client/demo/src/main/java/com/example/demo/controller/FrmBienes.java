package com.example.demo.controller;

import com.example.demo.config.APIMessages;
import com.example.demo.endPoint.entity.Bien;
import com.example.demo.endPoint.entity.Categoria;
import com.example.demo.endPoint.service.BienService;
import com.example.demo.endPoint.service.CategoriaService;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@ViewScoped
public class FrmBienes implements Serializable {
    Logger logger = LoggerFactory.getLogger(FrmBienes.class);
    private static final String REST_URI = "http://localhost:8095/inventario/api";

    private BienService bienesClient;
    private CategoriaService categoriaClient;
    private Response resp;
    private List<Bien> listBien ;

    private String nombreBien;
    private Bien objBien;
    private Integer idBien;

    private Integer selectedCategoria;
    private List<Categoria> listadoCategorias;

    private List<Bien> selectedBienes;

    private List<Bien> listBienAdd;


    public void redireccion(){
        cargaInicio();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../pages/frmBienes.xhtml");

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public BienService cargaServicioBien(){
        bienesClient= JAXRSClientFactory.create(
                REST_URI,
                BienService.class,
                Arrays.asList(
                        new JacksonJaxbJsonProvider()
                ));
        return bienesClient;
    }

    public CategoriaService cargaServicioCat(){
        categoriaClient= JAXRSClientFactory.create(
                REST_URI,
                CategoriaService.class,
                Arrays.asList(
                        new JacksonJaxbJsonProvider()
                ));
        return categoriaClient;
    }

    public void cargaInicio(){
        logger.info(("FrmCategorias init() called\n: "));

        buscaBienes();

        logger.info(("FrmBienes init() succesfully\n: "));

    }

    public void buscaBienes() {
        resp= cargaServicioBien().getAll();

        if (resp.getStatus() == Response.Status.OK.getStatusCode()) {
            listBien = resp.readEntity(new GenericType<List<Bien>>() {
            });

        }
    }

    public void seteaNuevoBien(){
        setNombreBien(null);
        setSelectedCategoria(null);
        setListBienAdd(new ArrayList<Bien>());
        resp= cargaServicioCat().getAll();

        if (resp.getStatus() == Response.Status.OK.getStatusCode()) {
            listadoCategorias = resp.readEntity(new GenericType<List<Categoria>>() {
            });

        }

    }

    public void creaBien(){
        logger.debug("creaBien begin");
        try {
            System.out.println("Selected: "+getSelectedCategoria());
            Bien param = new Bien();
            param.setNombre(getNombreBien());
            param.setEstado("Activo");
            param.setIdCategoria(getSelectedCategoria());
            Response resp = cargaServicioBien().saveBien(param);
            //logger.log(Level.INFO, "status:{0}", resp.getStatusInfo().getReasonPhrase());
            if (resp.getStatus() == Response.Status.CREATED.getStatusCode()) {
                setObjBien(resp.readEntity(Bien.class));
            }

            APIMessages.handleMessage(0, "Creación de Bien","Bien Ingresada correctamente");
            buscaBienes();
            setNombreBien(null);


        } catch (Exception e) {
            logger.error("creaBien-->error:"+e.getMessage());
            APIMessages.handleMessage(1, "Error Creación de Bien ",e.getMessage());
        }
        logger.debug("creaBien end");
    }


    public void eliminaBien(){
        logger.debug("eliminaBien begin");
        try {
            System.out.println("Presenta Id: "+getIdBien() +" Cambio nombre : "+getNombreBien());
            Response resp = cargaServicioBien().updateBien(getIdBien());
            //logger.log(Level.INFO, "status:{0}", resp.getStatusInfo().getReasonPhrase());
            if (resp.getStatus() == Response.Status.OK.getStatusCode()) {
                setObjBien(resp.readEntity(Bien.class));
            }
            APIMessages.handleMessage(0, "Eliminar Bien ","Bien dado de Baja correctamente");
            buscaBienes();

        } catch (Exception e) {
            logger.error("eliminaBien-->error:"+e.getMessage());
            APIMessages.handleMessage(1, "Error Eliminar Bien ",e.getMessage());
        }
        logger.debug("eliminaBien end");
    }

    public void eliminaBienesXLotes(){
        logger.debug("eliminaBienesXLotes begin");
        try {
            List<Integer> list= new ArrayList<Integer>();
            for(Bien b : getSelectedBienes()){
                list.add(b.getIdBien());
                System.out.println("Hola Renzo Joel");
                System.out.println("Presenta Id: "+b.getIdBien() +" Cambio nombre : "+b.getNombre());
            }

            Response resp = cargaServicioBien().deleteLotes(list);
            //logger.log(Level.INFO, "status:{0}", resp.getStatusInfo().getReasonPhrase());

            if (resp.getStatus() == Response.Status.OK.getStatusCode()) {
                listBien = resp.readEntity(new GenericType<List<Bien>>() {
                });

            }
            APIMessages.handleMessage(0, "Eliminar Bienes ","Bienes dados de Baja correctamente");
            buscaBienes();

        } catch (Exception e) {
            logger.error("eliminaBienesXLotes-->error:"+e.getMessage());
            APIMessages.handleMessage(1, "Error Eliminar Bienes Por Lotes ",e.getMessage());
        }
        logger.debug("eliminaBienesXLotes end");
    }


    public void agregarBien(){
        logger.debug("agregarBien begin");
        try {
            Bien obj = new Bien();
            obj.setIdCategoria(getSelectedCategoria());
            obj.setNombre(getNombreBien());
            obj.setEstado("Activo");
            getListBienAdd().add(obj);


            APIMessages.handleMessage(0, "Agregar Rol ","Se agregó el bien Correctamente");
            setNombreBien(null);
            setSelectedCategoria(null);


        } catch (Exception e) {
            logger.error("agregarBien-->error:"+e.getMessage());
            APIMessages.handleMessage(1, "Error Agregar Bien Por Lote",e.getMessage());
        }

        logger.debug("agregarUserRoles end");
    }

    public void creaBienXLotes(){
        logger.debug("creaBienXLotes begin");
        try {
            for (Bien b : getListBienAdd()){
                System.out.println("Presenta: "+b.getNombre());
            }
            Response resp = cargaServicioBien().saveLoteBien(getListBienAdd());
            //logger.log(Level.INFO, "status:{0}", resp.getStatusInfo().getReasonPhrase());
            if (resp.getStatus() == Response.Status.CREATED.getStatusCode()) {
                listBien = resp.readEntity(new GenericType<List<Bien>>() {
                });
            }

            APIMessages.handleMessage(0, "Creación de Bien Por Lotes","Bienes Ingresadaços correctamente");
            buscaBienes();
            setNombreBien(null);


        } catch (Exception e) {
            logger.error("creaBienXLotes-->error:"+e.getMessage());
            APIMessages.handleMessage(1, "Error Creación de Bien Por Lotes ",e.getMessage());
        }
        logger.debug("creaBien end");
    }



    public BienService getBienesClient() {
        return bienesClient;
    }

    public void setBienesClient(BienService bienesClient) {
        this.bienesClient = bienesClient;
    }

    public Response getResp() {
        return resp;
    }

    public void setResp(Response resp) {
        this.resp = resp;
    }

    public List<Bien> getListBien() {
        return listBien;
    }

    public void setListBien(List<Bien> listBien) {
        this.listBien = listBien;
    }

    public String getNombreBien() {
        return nombreBien;
    }

    public void setNombreBien(String nombreBien) {
        this.nombreBien = nombreBien;
    }

    public Bien getObjBien() {
        return objBien;
    }

    public void setObjBien(Bien objBien) {
        this.objBien = objBien;
    }

    public Integer getIdBien() {
        return idBien;
    }

    public void setIdBien(Integer idBien) {
        this.idBien = idBien;
    }

    public Integer getSelectedCategoria() {
        return selectedCategoria;
    }

    public void setSelectedCategoria(Integer selectedCategoria) {
        this.selectedCategoria = selectedCategoria;
    }

    public List<Categoria> getListadoCategorias() {
        return listadoCategorias;
    }

    public void setListadoCategorias(List<Categoria> listadoCategorias) {
        this.listadoCategorias = listadoCategorias;
    }

    public CategoriaService getCategoriaClient() {
        return categoriaClient;
    }

    public void setCategoriaClient(CategoriaService categoriaClient) {
        this.categoriaClient = categoriaClient;
    }

    public List<Bien> getSelectedBienes() {
        return selectedBienes;
    }

    public void setSelectedBienes(List<Bien> selectedBienes) {
        this.selectedBienes = selectedBienes;
    }

    public List<Bien> getListBienAdd() {
        return listBienAdd;
    }

    public void setListBienAdd(List<Bien> listBienAdd) {
        this.listBienAdd = listBienAdd;
    }
}
