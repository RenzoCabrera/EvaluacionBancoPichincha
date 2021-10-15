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
import java.util.Arrays;
import java.util.List;

@Component
@ViewScoped
public class FrmCategorias implements Serializable {
    Logger logger = LoggerFactory.getLogger(FrmCategorias.class);
    private static final String REST_URI = "http://localhost:8095/inventario/api";

    private CategoriaService categoriaClient;
    private Response resp;
    private List<Categoria> listCategorias;

    private String nombreCategoria;
    private Integer idCategoria;

    private Boolean isDisabledGrabar;
    private Boolean isVisibleEditar;
    private Categoria objCategorias;
    private Boolean isVisibleGrabar;

    private List<Bien> listadoBienes;
    private BienService bienesClient;
    private String datosXcategoria;


    public void redireccion(){
        cargaInicio();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../pages/frmCategorias.xhtml");

        } catch (Exception e) {
            // TODO: handle exception
        }
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

    public BienService cargaServicioBien(){
        bienesClient= JAXRSClientFactory.create(
                REST_URI,
                BienService.class,
                Arrays.asList(
                        new JacksonJaxbJsonProvider()
                ));
        return bienesClient;
    }

    public void cargaInicio(){
        logger.info(("FrmCategorias init() called\n: "));

        buscaCategorias();

        logger.info(("FrmCategorias init() succesfully\n: "));

    }

    public void buscaCategorias() {
        resp= cargaServicioCat().getAll();

        if (resp.getStatus() == Response.Status.OK.getStatusCode()) {
            listCategorias = resp.readEntity(new GenericType<List<Categoria>>() {
            });

        }
    }

    public void seteaNuevaCategoria(){

        setIsDisabledGrabar(false);
        setIsVisibleGrabar(true);
        setIsVisibleEditar(false);
    }

    public void creaCategoria(){
        logger.debug("creaCategoria begin");
        try {
            Categoria param = new Categoria();
            param.setNombre(getNombreCategoria());
            param.setEstado("Activo");
            Response resp = cargaServicioCat().saveCategoria(param);
            //logger.log(Level.INFO, "status:{0}", resp.getStatusInfo().getReasonPhrase());
            if (resp.getStatus() == Response.Status.CREATED.getStatusCode()) {
                setObjCategorias(resp.readEntity(Categoria.class));
            }

            APIMessages.handleMessage(0, "Creación de Categoría","Categoría Ingresada correctamente");
            buscaCategorias();
            setIsDisabledGrabar(true);
            setNombreCategoria(null);
            //setObjBitacoras(beanBitacora.buscaBitacora(Long.valueOf(getRespuestas().getCodigo().toString())));


        } catch (Exception e) {
            logger.error("creaCategoria-->error:"+e.getMessage());
            APIMessages.handleMessage(1, "Error Creación de Categoría ",e.getMessage());
        }
        logger.debug("creaCategoria end");
    }


    public void modificaCategoria(){
        logger.debug("modificaCategoria begin");
        try {
            Categoria param = new Categoria();
            param.setNombre(getNombreCategoria());
            param.setEstado("Activo");
            param.setIdCategoria(getIdCategoria());
            System.out.println("Presenta Id: "+getIdCategoria() +" Cambio nombre : "+getNombreCategoria());
            Response resp = cargaServicioCat().updateCategoria(param, getIdCategoria());
            //logger.log(Level.INFO, "status:{0}", resp.getStatusInfo().getReasonPhrase());
            if (resp.getStatus() == Response.Status.OK.getStatusCode()) {
                setObjCategorias(resp.readEntity(Categoria.class));
        }
            APIMessages.handleMessage(0, "Modificación de Categoria ","Categoria modificada correctamente");
            setNombreCategoria("");
            buscaCategorias();

        } catch (Exception e) {
            logger.error("modificaCategoria-->error:"+e.getMessage());
            APIMessages.handleMessage(1, "Error Modificación de Categoría ",e.getMessage());
        }
        logger.debug("modificaCategoria end");
    }

    public void cargaBienes(Integer idCat){

        try {
            resp= cargaServicioBien().listBienesByCategoria(idCat);

            if (resp.getStatus() == Response.Status.OK.getStatusCode()) {
                listadoBienes = resp.readEntity(new GenericType<List<Bien>>() {
                });
            }

            resp= cargaServicioBien().getDatosByCategoria(idCat);
            if (resp.getStatus() == Response.Status.OK.getStatusCode()) {
                datosXcategoria = resp.readEntity(new GenericType<String>() {
                });
            }

        } catch (Exception e) {
            APIMessages.handleMessage(1, "Error Administración de Actividad ",e.getMessage());
        }
    }


    public CategoriaService getCategoriaClient() {
        return categoriaClient;
    }

    public void setCategoriaClient(CategoriaService categoriaClient) {
        this.categoriaClient = categoriaClient;
    }

    public Response getResp() {
        return resp;
    }

    public void setResp(Response resp) {
        this.resp = resp;
    }

    public List<Categoria> getListCategorias() {
        return listCategorias;
    }

    public void setListCategorias(List<Categoria> listCategorias) {
        this.listCategorias = listCategorias;
    }


    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public Boolean getISDisabledGrabar() {
        return isDisabledGrabar;
    }

    public void setIsDisabledGrabar(Boolean isDisabledGrabar) {
        isDisabledGrabar = isDisabledGrabar;
    }

    public Boolean getIsVisibleEditar() {
        return isVisibleEditar;
    }

    public void setIsVisibleEditar(Boolean isVisibleEditar) {
        isVisibleEditar = isVisibleEditar;
    }

    public Categoria getObjCategorias() {
        return objCategorias;
    }

    public void setObjCategorias(Categoria objCategorias) {
        this.objCategorias = objCategorias;
    }

    public Boolean getIsVisibleGrabar() {
        return isVisibleGrabar;
    }

    public void setIsVisibleGrabar(Boolean isVisibleGrabar) {
        isVisibleGrabar = isVisibleGrabar;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public List<Bien> getListadoBienes() {
        return listadoBienes;
    }

    public void setListadoBienes(List<Bien> listadoBienes) {
        this.listadoBienes = listadoBienes;
    }

    public BienService getBienesClient() {
        return bienesClient;
    }

    public void setBienesClient(BienService bienesClient) {
        this.bienesClient = bienesClient;
    }

    public String getDatosXcategoria() {
        return datosXcategoria;
    }

    public void setDatosXcategoria(String datosXcategoria) {
        this.datosXcategoria = datosXcategoria;
    }
}
