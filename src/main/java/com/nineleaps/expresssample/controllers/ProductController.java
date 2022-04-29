package com.nineleaps.expresssample.controllers;

import com.nineleaps.expresssample.entities.Product;
import com.nineleaps.expresssample.services.IProductService;
import com.nineleaps.expresssample.services.ProductService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/product")
public class ProductController {
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    private IProductService productService;

    @Autowired
    public ProductController(IProductService productService){
        this.productService = productService;
    }
    @PostMapping()
    public Product createProduct(@Valid @RequestBody Product product){
        log.debug("Received request to create a product");
        return productService.createProduct(product);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable long id){
        log.debug("Received request to get the product");
        return productService.getProduct(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable long id, @Valid @RequestBody Product product){
        log.debug("Received request to update the product");
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable long id){
        log.debug("Received request to get the product");
        productService.deleteProduct(id);
    }
}
