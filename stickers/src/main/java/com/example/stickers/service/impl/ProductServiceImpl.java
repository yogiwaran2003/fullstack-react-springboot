package com.example.stickers.service.impl;

import com.example.stickers.dto.ProductDto;
import com.example.stickers.entity.Product;
import com.example.stickers.repository.ProductRepository;
import com.example.stickers.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {
    private final ProductRepository productRepository;

    @Cacheable("products")
    @Override
    public List<ProductDto> getProducts() {
        return productRepository.findAll().stream().map(this::transformToDTO).collect(Collectors.toList());
    }

    private ProductDto transformToDTO(Product product){
        ProductDto productDto= new ProductDto();
        BeanUtils.copyProperties(product,productDto);
        productDto.setProductId(product.getId());
        productDto.setPrice(product.getPrice() != null ? product.getPrice().doubleValue() : null);
        return productDto;
    }



}
