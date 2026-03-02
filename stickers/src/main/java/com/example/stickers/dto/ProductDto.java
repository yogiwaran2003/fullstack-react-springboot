package com.example.stickers.dto;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ProductDto {

    private Long productId;
    private String name;
    private String description;
    private Double price;
    private Integer popularity;
    private String imageUrl;
    private Instant createdAt;
}
