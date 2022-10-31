package com.bms.dummyrestapi.entities.dtos.product;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String title;

    private String description;

    private double price;

    private double discountPercentage;

    private double rating;

    private int stock;

    private String brand;

    private String thumbnail;

    private CategoryDto category;

    private List<ImagesDto> images;
}
