package com.bp.reto.inventario.service;


import com.bp.reto.inventario.entity.Categoria;
import com.bp.reto.inventario.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoríaService {

    CategoriaRepository categoriaRepository;

    public CategoríaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> getAllCategoria() {
        return categoriaRepository.findAll();
    }

    public Categoria saveCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria updateCategoria(Categoria newCategoria, Integer idCategoria) {
        return categoriaRepository.findById(idCategoria).
                map(cat -> {
                    cat.setNombre(newCategoria.getNombre());
                    cat.setEstado(newCategoria.getEstado());

                            return categoriaRepository.save(cat);
                        }
                ).get();

    }

    public Optional<Categoria> getCategoria(Integer idCategoria) {
        return categoriaRepository.findById(idCategoria);
    }


}
