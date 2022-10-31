package com.bms.dummyrestapi.business.abstracts;

import com.bms.dummyrestapi.core.utilities.DataResult;
import com.bms.dummyrestapi.core.utilities.Result;
import com.bms.dummyrestapi.entities.concretes.product.Product;
import com.bms.dummyrestapi.entities.dtos.product.CategoryDto;
import com.bms.dummyrestapi.entities.dtos.product.ImagesDto;
import com.bms.dummyrestapi.entities.dtos.product.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    DataResult<List<ProductDto>> getAll(Optional<String> productName, Optional<Integer> pageNo, Optional<Integer> pageSize);

    DataResult<ProductDto> getProductById(int id);

    DataResult<List<ImagesDto>> getProductImagesById(int id);

    DataResult<CategoryDto> getProductCategoryById(int id);

    Result addProduct(Product product);

    Result updateProduct(int id, Product product);

    Result deleteProduct(int id);
}
