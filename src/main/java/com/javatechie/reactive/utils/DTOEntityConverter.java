package com.javatechie.reactive.utils;

import com.javatechie.reactive.dto.ProductDTO;
import com.javatechie.reactive.entity.Product;
import org.springframework.beans.BeanUtils;

public class DTOEntityConverter {

    public static ProductDTO productEntityToDTO(Product product) {
        ProductDTO productDto = new ProductDTO();
        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }

    public static Product productDTOToEntity(ProductDTO productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return product;
    }
}
