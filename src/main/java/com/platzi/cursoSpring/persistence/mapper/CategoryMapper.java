package com.platzi.cursoSpring.persistence.mapper;

import com.platzi.cursoSpring.domain.Category;
import com.platzi.cursoSpring.persistence.entity.Categoria;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mappings({
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "descripcion", target = "category"),
            @Mapping(source = "estado", target = "active"),
//            @Mapping(source = "categoria", target = "category")
    })
    Category toCategory(Categoria categoria);

    @InheritInverseConfiguration
    @Mapping(target = "estado", ignore = true)
    Categoria toCategoria(Category category);
}