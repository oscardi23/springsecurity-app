package com.odiaz.security.services;

import com.odiaz.security.dtos.ProductItem;

import java.util.List;

// avance
public interface ProductService
{

    List<ProductItem> list();

    void create(ProductItem item);

    void update(Integer id, ProductItem item);

    void delete(Integer id);
}
