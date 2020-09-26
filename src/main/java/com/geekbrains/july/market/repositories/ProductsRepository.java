package com.geekbrains.july.market.repositories;

import com.geekbrains.july.market.entities.Product;
import com.geekbrains.july.market.entities.dtos.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    List<ProductDto> findAllBy();

    @Override
    Optional<Product> findById(Long id);
}