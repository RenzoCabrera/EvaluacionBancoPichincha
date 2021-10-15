package com.bp.reto.inventario.repository;

import com.bp.reto.inventario.entity.Categoria;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoriaRepository extends CrudRepository<Categoria, Integer> {

    List<Categoria> findAll();
}
