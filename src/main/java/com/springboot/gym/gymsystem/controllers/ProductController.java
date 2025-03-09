package com.springboot.gym.gymsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.gym.gymsystem.models.dtos.ProductDto;
import com.springboot.gym.gymsystem.services.interfaces.ProductService;
import com.springboot.gym.gymsystem.utilities.Constants;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
        return new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<ProductDto>> getAllProducts(
        @RequestParam(value = "pageNum", defaultValue = Constants.NUMERO_DE_PAGINAS_POR_DEFECTO, required = false) int pageNumber,
        @RequestParam(value = "pageSize", defaultValue = Constants.MEDIDA_DE_PAGINA_POR_DEFECTO, required = false) int sizePage,
        @RequestParam(value =  "sortBy", defaultValue = Constants.ORDENAR_POR_DEFECTO, required = false ) String orderBy,
        @RequestParam(value = "sortDir", defaultValue = "asc",required = false) String sortDir){
        return new ResponseEntity<>(productService.getAllProducts(pageNumber,sizePage,orderBy,sortDir), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<ProductDto> creatProduct(@Valid @RequestBody ProductDto productDto) {
        return new ResponseEntity<>(productService.createProduct(productDto), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id,@Valid @RequestBody ProductDto productDto) {     
        return new ResponseEntity<>(productService.updateProductDto(id, productDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletProduct(@PathVariable Long id){
        productService.deletProduct(id);
        return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);    
    }
}
