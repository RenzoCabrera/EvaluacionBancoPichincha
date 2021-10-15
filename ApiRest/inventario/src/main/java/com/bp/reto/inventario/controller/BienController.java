package com.bp.reto.inventario.controller;

import com.bp.reto.inventario.entity.Bien;
import com.bp.reto.inventario.entity.Categoria;
import com.bp.reto.inventario.service.BienService;
import com.bp.reto.inventario.service.CategoríaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bienes")
public class BienController {

    private BienService bienService;


    public BienController(BienService bienService) {
        this.bienService = bienService;
    }

    @GetMapping("/all")
    @ApiOperation(value = "Recuperar el listado de bienes con el detalle de estos (activos, dados de baja)")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<Bien>> getAll() {
        return new ResponseEntity<>(bienService.getAllBienes(), HttpStatus.OK);
    }

    @PostMapping("/save")
    @ApiOperation(value = "Crear un nuevo bien y asignarlo a una categoría  ")
    @ApiResponse(code = 201, message = "CREATED")
    public ResponseEntity<Bien> saveBien(@RequestBody Bien bien) {
        return new ResponseEntity<>(bienService.saveBien(bien), HttpStatus.CREATED);
    }




    @PutMapping("/delete/{idBien}")
    @ApiOperation(value = "Elimina un Bien ")
    @ApiResponse(code = 200, message = "OK")
    ResponseEntity <Bien> delete (@PathVariable("idBien") Integer idBien){
        return new ResponseEntity<>(bienService.delete(idBien), HttpStatus.OK);
    }


    @PostMapping("/saveLote")
    @ApiOperation(value = "Ingresa Lote de nuevos bienes  ")
    @ApiResponse(code = 201, message = "CREATED")
    public ResponseEntity<List<Bien>> saveLoteBien(@RequestBody List<Bien> listBien) {
        return new ResponseEntity<>(bienService.saveLotesBienes(listBien), HttpStatus.CREATED);
    }


    @PutMapping("/deleteLotes/")
    @ApiOperation(value = "Elimina Bienes por lotes ")
    @ApiResponse(code = 200, message = "OK")
    ResponseEntity <List<Bien>> deleteLotes (@RequestBody List<Integer> listIdBienes){
        return new ResponseEntity<>(bienService.deleteLotesBienes(listIdBienes), HttpStatus.OK);
    }


    @GetMapping("/byCategoria/{idCategoria}")
    @ApiOperation(value = "Dada una categoría recuperar cuantos bienes están disponibles y cuantos se han dado de baja ")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity <String> getDatosByCategoria (@PathVariable("idCategoria") Integer idCategoria) {
        return new ResponseEntity<>(bienService.getByCategoria(idCategoria), HttpStatus.OK);
    }


    @GetMapping("/disponiblesByCategorias")
    @ApiOperation(value = "Recuperar cuantos bienes disponibles existe en cada categoría ")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity <String> getDisponiblesByCatecorias () {
        return new ResponseEntity<>(bienService.bienesByCategorias(), HttpStatus.OK);
    }

    @GetMapping("/listByCategoria/{idCategoria}")
    @ApiOperation(value = "Recuperar la lista de bienes por categoría ")
    @ApiResponse(code = 200, message = "OK")
    ResponseEntity <List<Bien>> listBienesByCategoria (@PathVariable("idCategoria") Integer idCategoria) {
        return bienService.listByCategoria(idCategoria).map(
                p -> new ResponseEntity<>(p, HttpStatus.OK)
        ).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }




}
