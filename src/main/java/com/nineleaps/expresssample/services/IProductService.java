package com.nineleaps.expresssample.services;

import com.nineleaps.expresssample.entities.Product;

public interface IProductService {
    Product createProduct(Product product);
    void deleteProduct(long id);
    Product getProduct(long id);
    Product updateProduct(long id, Product product);
}
