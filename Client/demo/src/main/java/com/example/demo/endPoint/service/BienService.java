package com.example.demo.endPoint.service;

import com.example.demo.endPoint.entity.Bien;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/bienes")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public interface BienService {

    @GET
    @Path("/all")
    Response getAll();

    @POST
    @Path("/save")
    Response saveBien(Bien param);

    @PUT
    @Path("/delete/{idBien}")
    Response updateBien(@PathParam("idBien")Integer idBien);

    @GET
    @Path("/listByCategoria/{idCategoria}")
    Response listBienesByCategoria(@PathParam("idCategoria")Integer idCategoria);

    @GET
    @Path("/byCategoria/{idCategoria}")
    Response getDatosByCategoria(@PathParam("idCategoria")Integer idCategoria);

    @GET
    @Path("/disponiblesByCategorias")
    Response getDisponiblesByCatecorias();

    @PUT
    @Path("/deleteLotes/")
    Response deleteLotes(List<Integer> listIdBienes);

    @POST
    @Path("/saveLote")
    Response saveLoteBien(List<Bien> listBien);







}
