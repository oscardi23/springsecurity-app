package com.odiaz.security.services.impl;

import com.odiaz.security.dtos.ProductItem;
import com.odiaz.security.entities.ProductEntity;
import com.odiaz.security.exception.ProductIsNotActiveException;
import com.odiaz.security.exception.ProductNoFoundException;
import com.odiaz.security.repositories.ProductRepository;
import com.odiaz.security.services.ProductService;
import com.odiaz.security.services.mappers.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;


   // metodo para listar los productos
    @Override
    public List<ProductItem> list() {

        return productRepository.findAllActiveProducts().stream().map((e -> ProductMapper.fromEntityTOdto
                (e, new ProductItem()))).toList();
    }


    // metodo para crear un producto
    @Override
    public void create(ProductItem item) {

        ProductEntity entity = ProductMapper.fromDtoToEntity(item, new ProductEntity());
        productRepository.save(entity);


    }

    // metodo para actualizar un producto

    @Override
    public void update(Integer id, ProductItem item) {

        Optional<ProductEntity> entityOpt = productRepository.findById(id);

        if (entityOpt.isEmpty()) {
            throw new ProductNoFoundException("Product not found");

        }
        if (Boolean.FALSE.equals(entityOpt.get().isActive())) {

                throw new ProductIsNotActiveException("Product is not active");
            }

            ProductEntity entity = entityOpt.get();
            ProductMapper.fromDtoToEntity(item, entity);
            productRepository.save(entity);


        }

        // metodo para eliminar un producto

        @Override
        public void delete(Integer id){


            ProductEntity productFound = productRepository.findById(id).orElseThrow(() -> new ProductNoFoundException("Product not found"));
            productFound.setActive(false);
            productRepository.save(productFound);


        }

}
