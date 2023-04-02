package com.javatechie.reactive.service;

import com.javatechie.reactive.dto.ProductDTO;
import com.javatechie.reactive.repository.ProductRepository;
import com.javatechie.reactive.utils.DTOEntityConverter;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Flux<ProductDTO> getProducts(){
        return productRepository.findAll().map(DTOEntityConverter::productEntityToDTO);
    }

    public Mono<ProductDTO> getProduct(String id){
        return productRepository.findById(id).map(DTOEntityConverter::productEntityToDTO);
    }

    public Flux<ProductDTO> getProductInRange(double min, double max){
        return productRepository.findByPriceBetween(Range.closed(min,max));
    }

    public Mono<ProductDTO> saveProduct(Mono<ProductDTO> productDtoMono){
        System.out.println("service method called ...");
      return  productDtoMono.map(DTOEntityConverter::productDTOToEntity)
                .flatMap(productRepository::insert)
                .map(DTOEntityConverter::productEntityToDTO);
    }

    public Mono<ProductDTO> updateProduct(Mono<ProductDTO> productDtoMono, String id){
       return productRepository.findById(id)
                .flatMap( p -> productDtoMono.map(DTOEntityConverter::productDTOToEntity)
                .doOnNext( e -> e.setId(id)))
                .flatMap(productRepository::save)
                .map(DTOEntityConverter::productEntityToDTO);

    }

    public Mono<Void> deleteProduct(String id){
        return productRepository.deleteById(id);
    }
}
