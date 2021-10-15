package com.bp.reto.inventario.service;

import com.bp.reto.inventario.entity.Bien;
import com.bp.reto.inventario.entity.Categoria;
import com.bp.reto.inventario.repository.BienRepository;
import com.bp.reto.inventario.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class BienService {

    private BienRepository bienRepository;
    CategoriaRepository categoriaRepository;

    public BienService(BienRepository bienRepository, CategoriaRepository categoriaRepository) {
        this.bienRepository = bienRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<Bien> getAllBienes() {
        return bienRepository.findAll();
    }

    public Bien saveBien(Bien bien) {
        return bienRepository.save(bien);
    }


    public Optional<List<Bien>> listByCategoria(Integer ideCategoria) {
        return bienRepository.findByIdCategoriaOrderByNombreAsc(ideCategoria);
    }

    public String getByCategoria(Integer ideCategoria) {
        long cantidadBienesDisp = bienRepository.countByIdCategoriaAndEstado(ideCategoria,"Activo");
        long cantidadBienesBaja = bienRepository.countByIdCategoriaAndEstado(ideCategoria,"Inactivo");
        String response ="";

            response="Existen "+cantidadBienesDisp +" Bienes Disponibles y "+cantidadBienesBaja+" dados de baja en la categoría seleccionada";

        return response;
    }


    @Transactional
    public List<Bien> saveLotesBienes(List<Bien> list) {


        return (List<Bien>) bienRepository.saveAll(list);
    }

    public Bien delete(Integer idBien) {
        return bienRepository.findById(idBien).
                map(bie -> {
                    bie.setEstado("Inactivo");

                            return bienRepository.save(bie);
                        }
                ).get();

    }

    @Transactional
    public List<Bien> deleteLotesBienes(List<Integer> list) {
        List<Bien> response = new ArrayList<Bien>();
        for (Integer id : list){
            response.add(
                    bienRepository.findById(id).
                    map(bie -> {
                        bie.setEstado("Inactivo");
                        return bienRepository.save(bie);
                    }).get());
        }
        return response;
    }

    public String bienesByCategorias() {
        String response ="";
        List<Categoria> listCategoria = categoriaRepository.findAll();
        long cantidadBienesDisp;

        for (Categoria cat : listCategoria){
            cantidadBienesDisp = bienRepository.countByIdCategoriaAndEstado(cat.getIdCategoria(),"Activo");
            response+="Existen "+cantidadBienesDisp +" Bienes Disponibles  en la categoría "+cat.getNombre() +"\n";

        }

        return response;
    }


}
