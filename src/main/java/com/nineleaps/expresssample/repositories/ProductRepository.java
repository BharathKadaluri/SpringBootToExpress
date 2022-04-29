package com.nineleaps.expresssample.repositories;

import com.nineleaps.expresssample.entities.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    List<Product> findByName(String name);

    Product findById(long id);
}
