package com.bms.dummyrestapi.entities.concretes.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;

    @NotNull(message = "Product name cannot be null")
    @NotEmpty(message = "Product name cannot be empty")
    @NotBlank(message = "Product name cannot be blank")
    @Column(name = "product_name")
    private String title;

    @NotNull(message = "Product description cannot be null")
    @NotEmpty(message = "Product description cannot be empty")
    @NotBlank(message = "Product description cannot be blank")
    @Column(name = "product_description")
    private String description;

    @Min(value = 0, message = "Product price cannot be less than 0")
    @Column(name = "product_price")
    private double price;

    @Min(value = 1, message = "Product stock cannot be less than 1")
    @Column(name = "product_discount_percentage")
    private double discountPercentage;

    @Min(value = 0, message = "Product stock cannot be less than 0")
    @Max(value = 5, message = "Product stock cannot be more than 5")
    @Column(name = "product_rating")
    private double rating;

    @Min(value = 0, message = "Product stock cannot be less than 0")
    @Column(name = "product_stock")
    private int stock;

    @NotNull(message = "Product brand cannot be null")
    @NotEmpty(message = "Product brand cannot be empty")
    @NotBlank(message = "Product brand cannot be blank")
    @Column(name = "product_brand")
    private String brand;

    @NotNull(message = "Product thumbnail cannot be null")
    @NotEmpty(message = "Product thumbnail cannot be empty")
    @NotBlank(message = "Product thumbnail cannot be blank")
    @Column(name = "product_thumbnail")
    private String thumbnail;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_images",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private List<Images> images;
}