package com.odiaz.security.controller;


import com.odiaz.security.dtos.ProductItem;
import com.odiaz.security.services.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;


    // metodo para listar los productos
    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<ProductItem>> list() {
        List<ProductItem> products = productService.list();
        return ResponseEntity.ok(products);
    }

    // metodo para crear un producto
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid ProductItem item) {

        productService.create(item);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }
    // metodo para actualizar un producto
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Integer id, @RequestBody @Valid ProductItem item) {

        productService.update(id, item);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // metodo para eliminar un producto
    @DeleteMapping("/{id}")
   // @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Map<String, String>>  delete(@PathVariable("id") Integer id) {

        productService.delete(id);

        Map<String, String> response = new HashMap<>();

        response.put("message", "Product deleted successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
