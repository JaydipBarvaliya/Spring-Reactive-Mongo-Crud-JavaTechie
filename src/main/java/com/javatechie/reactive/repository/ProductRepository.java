package com.javatechie.reactive.repository;

import com.javatechie.reactive.dto.ProductDTO;
import com.javatechie.reactive.entity.Product;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product,String> {
    Flux<ProductDTO> findByPriceBetween(Range<Double> priceRange);
}
