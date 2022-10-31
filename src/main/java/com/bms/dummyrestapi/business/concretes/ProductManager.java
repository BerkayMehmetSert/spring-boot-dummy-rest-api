package com.bms.dummyrestapi.business.concretes;

import com.bms.dummyrestapi.business.abstracts.ProductService;
import com.bms.dummyrestapi.core.utilities.DataResult;
import com.bms.dummyrestapi.core.utilities.Result;
import com.bms.dummyrestapi.core.utilities.SuccessDataResult;
import com.bms.dummyrestapi.core.utilities.SuccessResult;
import com.bms.dummyrestapi.dataAccess.ProductDao;
import com.bms.dummyrestapi.entities.concretes.product.Product;
import com.bms.dummyrestapi.entities.dtos.product.CategoryDto;
import com.bms.dummyrestapi.entities.dtos.product.ImagesDto;
import com.bms.dummyrestapi.entities.dtos.product.ProductDto;
import com.bms.dummyrestapi.exceptions.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductManager implements ProductService {
    private final ProductDao productDao;
    private final ModelMapper modelMapper;

    public ProductManager(ProductDao productDao, ModelMapper modelMapper) {
        this.productDao = productDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public DataResult<List<ProductDto>> getAll(Optional<String> productName, Optional<Integer> pageNo, Optional<Integer> pageSize) {
        List<Product> products = productDao.findAll();

        if (productName.isPresent()) {
            products = productDao.findByTitleIgnoreCase(productName.get());
        }
        if (pageNo.isPresent() && pageSize.isPresent()) {
            Pageable pageable = PageRequest.of((pageNo.get()) - 1, pageSize.get());
            products = productDao.findAll(pageable).getContent();
        }
        List<ProductDto> productDto = products.stream().map(product -> modelMapper
                        .map(product, ProductDto.class))
                .collect(Collectors.toList());

        if (productDto.isEmpty()) throw new NotFoundException("Product not found");

        return new SuccessDataResult<>(productDto, "Products listed successfully");
    }


    @Override
    public Result addProduct(Product product) {
        productDao.save(product);
        return new SuccessResult("Product added successfully");
    }

    @Override
    public Result updateProduct(int id, Product product) {
        Product productToUpdate = productDao.findById(id).orElseThrow(() -> new NotFoundException("Product " + id + " not found"));
        productToUpdate.setTitle(product.getTitle());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setCategory(product.getCategory());
        productToUpdate.setImages(product.getImages());
        productDao.save(productToUpdate);
        return new SuccessResult("Product " + id + " updated successfully");
    }

    @Override
    public Result deleteProduct(int id) {
        Product product = productDao.findById(id).orElseThrow(() -> new NotFoundException("Product " + id + " not found"));
        productDao.delete(product);
        return new SuccessResult("Product " + id + " deleted successfully");
    }

    @Override
    public DataResult<ProductDto> getProductById(int id) {
        Product product = productDao.findById(id).orElseThrow(() -> new NotFoundException("Product " + id + " not found"));
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        return new SuccessDataResult<ProductDto>(productDto, "Product listed successfully");
    }

    @Override
    public DataResult<List<ImagesDto>> getProductImagesById(int id) {
        Product product = productDao.findById(id).orElseThrow(() -> new NotFoundException("Product " + id + " not found"));
        List<ImagesDto> imagesDto = product.getImages().stream().map(image -> modelMapper
                        .map(image, ImagesDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ImagesDto>>(imagesDto, "Product images listed successfully");
    }

    @Override
    public DataResult<CategoryDto> getProductCategoryById(int id) {
        Product product = productDao.findById(id).orElseThrow(() -> new NotFoundException("Product " + id + " not found"));
        CategoryDto categoryDto = modelMapper.map(product.getCategory(), CategoryDto.class);
        return new SuccessDataResult<CategoryDto>(categoryDto, "Product category listed successfully");
    }

}
