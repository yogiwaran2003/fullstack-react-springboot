package com.example.stickers.service;

import com.example.stickers.dto.ProductDto;

import java.util.List;

public interface IProductService {
    List<ProductDto> getProducts();
}
