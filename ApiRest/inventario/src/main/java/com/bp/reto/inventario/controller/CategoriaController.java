package com.bp.reto.inventario.controller;


import com.bp.reto.inventario.entity.Categoria;
import com.bp.reto.inventario.service.CategoríaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {


    private CategoríaService categoríaService;

    public CategoriaController(CategoríaService categoríaService) {
        this.categoríaService = categoríaService;
    }

    @GetMapping("/all")
    @ApiOperation(value = "Obtiene la Lista de Categorías")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<Categoria>> getAll() {
        return new ResponseEntity<>(categoríaService.getAllCategoria(), HttpStatus.OK);
    }

    @PostMapping("/save")
    @ApiOperation(value = "Crea la Categoría ")
    @ApiResponse(code = 201, message = "CREATED")
    public ResponseEntity<Categoria> saveCategoria(@RequestBody Categoria categoria) {
        return new ResponseEntity<>(categoríaService.saveCategoria(categoria), HttpStatus.CREATED);
    }

    @PutMapping("/update/{idCategoria}")
    @ApiOperation(value = "Edita la categoria")
    @ApiResponse(code = 200, message = "OK")
    ResponseEntity <Categoria> updateCategoria (@RequestBody Categoria newCategoria, @PathVariable("idCategoria") Integer idCategoria ){
        return new ResponseEntity<>(categoríaService.updateCategoria(newCategoria, idCategoria), HttpStatus.OK);
    }


}
