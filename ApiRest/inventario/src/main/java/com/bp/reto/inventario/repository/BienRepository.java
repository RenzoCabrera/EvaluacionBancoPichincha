package com.bp.reto.inventario.repository;

import com.bp.reto.inventario.entity.Bien;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BienRepository extends CrudRepository<Bien,Integer> {

    List<Bien> findAll();
    Optional<List<Bien>> findByIdCategoriaOrderByNombreAsc(int idCategoria);

    long countByIdCategoriaAndEstado(int idCategoria, String estado);



}
