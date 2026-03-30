package com.odiaz.security.services.mappers;

import com.odiaz.security.dtos.ProductItem;
import com.odiaz.security.entities.ProductEntity;

public class ProductMapper {

    private ProductMapper() {
    }

   public static ProductEntity fromDtoToEntity(ProductItem item, ProductEntity entity) {
        entity.setName(item.getName());
        entity.setDescription(item.getDescription());
        entity.setPrice(item.getPrice());
        return entity;
    }

    public static ProductItem fromEntityTOdto(ProductEntity entity, ProductItem item ) {
        item.setId(entity.getId());
        item.setName(entity.getName());
        item.setDescription(entity.getDescription());
        item.setPrice(entity.getPrice());
        return item;

    }

}


