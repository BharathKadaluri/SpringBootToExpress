package com.nineleaps.expresssample.services;

import com.nineleaps.expresssample.entities.Product;
import com.nineleaps.expresssample.exceptions.NotFoundException;
import com.nineleaps.expresssample.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {

    private ProductRepository repository;

    @Override
    public Product createProduct(Product product) {
        return repository.save(product);
    }

    @Override
    public void deleteProduct(long id) {
        Product product = getProduct(id);
        repository.delete(product);

    }

    @Override
    public Product getProduct(long id) {
        Product product = repository.findById(id);
        if(null == product){
            throw new NotFoundException("Product not found");
        }
        return product;
    }

    @Override
    public Product updateProduct(long id, Product product) {
        Product findproduct = getProduct(id);
        BeanUtils.copyProperties(product, findproduct);
        findproduct.setId(id);
        return repository.save(findproduct);
    }
}
