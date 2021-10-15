package com.example.demo.endPoint.service;
import com.example.demo.endPoint.entity.Categoria;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/categorias")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public interface CategoriaService {

    @GET
    @Path("/all")
    Response getAll();

    @POST
    @Path("/save")
    Response saveCategoria(Categoria param);

    @PUT
    @Path("/update/{idCategoria}")
    @Consumes(MediaType.APPLICATION_JSON)
    Response updateCategoria(Categoria param,@PathParam("idCategoria")Integer idCategoria);


}
