package com.springboot.gym.gymsystem.services;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.gym.gymsystem.models.dtos.ProductDto;
import com.springboot.gym.gymsystem.models.entities.ProductEntity;
import com.springboot.gym.gymsystem.models.mappers.ProductMapper;
import com.springboot.gym.gymsystem.repositories.ProductRepository;
import com.springboot.gym.gymsystem.services.interfaces.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    //CREAT
    @Override
    @Transactional
    public ProductDto creatProduct(ProductDto productDto) {
       ProductEntity productEntity = ProductMapper.dtoToEntity(productDto);
       productRepository.save(productEntity);
       return ProductMapper.entityToDto(productEntity);
    }

    //GETONE
    @Override
    @Transactional(readOnly = true)
    public ProductDto getProduct(long id) {
      return productRepository.findById(id)
      .map(ProductMapper::entityToDto)
      //Exepcion a personalizar -----------------------------------------
      .orElseThrow(() -> new NoSuchElementException("Class not found with id: " + id));
    }

    //GETALL
    @Transactional(readOnly = true) 
    @Override
    public Page<ProductDto> getAllProducts(int pageNumber, int sizePage, String orderBy, String sortDir) {
       Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? Sort.Direction.DESC : Sort.Direction.ASC;
       Pageable pageable = PageRequest.of(pageNumber, sizePage, Sort.by(direction, orderBy));
       return productRepository.findAll(pageable).map(ProductMapper::entityToDto);
    }

    //UPDATE
    @Override
    @Transactional
    public ProductDto updateProductDto(long id, ProductDto productDto) {
      ProductEntity productEntity = productRepository.findById(id)
      //Exepcion a personalizar -----------------------------------------
      .orElseThrow(() -> new NoSuchElementException("Class not found with id: " + id));


      productEntity.setId(productDto.getId());
      productEntity.setName(productDto.getName());
      productEntity.setPrice(productDto.getPrice());
      
      ProductEntity productEntity2 = productRepository.save(productEntity);
      return ProductMapper.entityToDto(productEntity2);
    }

    //DELETE
    @Override
    public void deletProduct(long id) {
       ProductEntity productEntity = productRepository.findById(id)
        //Exepcion a personalizar -----------------------------------------
        .orElseThrow(() -> new NoSuchElementException("Class not found with id: " + id));
       productRepository.delete(productEntity);
    }

}
