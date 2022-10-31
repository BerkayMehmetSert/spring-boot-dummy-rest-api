package com.bms.dummyrestapi.dataAccess;

import com.bms.dummyrestapi.entities.concretes.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Integer> {
    List<Product> findByTitleIgnoreCase(String s);
}
