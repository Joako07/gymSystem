package com.springboot.gym.gymsystem.services.interfaces;

import org.springframework.data.domain.Page;

import com.springboot.gym.gymsystem.models.dtos.ProductDto;

public interface ProductService {

    public ProductDto createProduct (ProductDto productDto);

    public ProductDto getProduct (long id);

    public Page<ProductDto> getAllProducts (int pageNumber, int sizePage, String orderBy, String sortDir);

    public ProductDto updateProductDto(long id, ProductDto productDto);

    public void deletProduct(long id);

}
