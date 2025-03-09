package com.springboot.gym.gymsystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.gym.gymsystem.exceptions.ApiErrorException;
import com.springboot.gym.gymsystem.exceptions.NotFoundApiException;
import com.springboot.gym.gymsystem.models.dtos.ProductDto;
import com.springboot.gym.gymsystem.models.entities.ProductEntity;
import com.springboot.gym.gymsystem.models.mappers.ProductMapper;
import com.springboot.gym.gymsystem.repositories.ProductRepository;
import com.springboot.gym.gymsystem.services.interfaces.ProductService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

   @Autowired
   private ProductRepository productRepository;

   // CREAT
   @Override
   @Transactional
   public ProductDto createProduct(ProductDto productDto) {
      ProductEntity productEntity = ProductMapper.dtoToEntity(productDto);
      try {
         productRepository.save(productEntity);
         log.info("A Product was created");
         return ProductMapper.entityToDto(productEntity);
      } catch (Exception e) {
         log.error("An error occurred while creating a new Product", e);
         throw ApiErrorException.builder()
               .error(e.getMessage())
               .message("Ocurrió un error al crear un producto")
               .build();
      }
   }

   // GETONE
   @Override
   @Transactional(readOnly = true)
   public ProductDto getProduct(long id) {
      return productRepository.findById(id)
            .map(ProductMapper::entityToDto)
            .orElseThrow(() -> new NotFoundApiException(
                  "404 Not Found - Product with ID " + id + " not found",
                  "No se encontro el producto con el Id " + id));
   }

   // GETALL
   @Transactional(readOnly = true)
   @Override
   public Page<ProductDto> getAllProducts(int pageNumber, int sizePage, String orderBy, String sortDir) {
      Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? Sort.Direction.DESC : Sort.Direction.ASC;
      Pageable pageable = PageRequest.of(pageNumber, sizePage, Sort.by(direction, orderBy));
      return productRepository.findAll(pageable).map(ProductMapper::entityToDto);
   }

   // UPDATE
   @Override
   @Transactional
   public ProductDto updateProductDto(long id, ProductDto productDto) {
      ProductEntity productEntity = productRepository.findById(id)
            .orElseThrow(() -> new NotFoundApiException(
                  "404 Not Found - Product with ID " + id + " not found",
                  "No se encontro el producto con el Id " + id));

      productEntity.setId(id);
      productEntity.setName(productDto.getName());
      productEntity.setPrice(productDto.getPrice());

      try {
         ProductEntity productEntity2 = productRepository.save(productEntity);
         log.info("A Product was updated");
         return ProductMapper.entityToDto(productEntity2);
      } catch (Exception e) {
         log.error("An error occurred while updating a Product", e);
         throw ApiErrorException.builder()
               .error(e.getMessage())
               .message("Ocurrió un error al actualizar un producto")
               .build();
      }
   }

   // DELETE
   @Override
   public void deletProduct(long id) {
      ProductEntity productEntity = productRepository.findById(id)
            .orElseThrow(() -> new NotFoundApiException(
                  "404 Not Found - Product with ID " + id + " not found",
                  "No se encontro el producto con el Id " + id));
      try {
         productRepository.delete(productEntity);
         log.info("A Product was deleted");
      } catch (Exception e) {
         log.error("An error occurred while deleting a Product", e);
         throw ApiErrorException.builder()
               .error(e.getMessage())
               .message("Ocurrió un error al borrar un producto")
               .build();
      }
   }

}
