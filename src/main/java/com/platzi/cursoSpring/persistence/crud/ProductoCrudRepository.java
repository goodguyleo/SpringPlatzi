package com.platzi.cursoSpring.persistence.crud;

import com.platzi.cursoSpring.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {

    List<Producto> findByIdCategoriaOrdOrderByNombreAsc(int idCategoria);
    Optional<List<Producto>>findByCantidadStockLessThanAAndEstado(int cantidadStock, boolean estado);
}
