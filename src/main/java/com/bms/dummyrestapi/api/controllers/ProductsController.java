package com.bms.dummyrestapi.api.controllers;

import com.bms.dummyrestapi.business.abstracts.ProductService;
import com.bms.dummyrestapi.core.utilities.DataResult;
import com.bms.dummyrestapi.core.utilities.ErrorDataResult;
import com.bms.dummyrestapi.core.utilities.Result;
import com.bms.dummyrestapi.entities.concretes.product.Product;
import com.bms.dummyrestapi.entities.dtos.product.CategoryDto;
import com.bms.dummyrestapi.entities.dtos.product.ImagesDto;
import com.bms.dummyrestapi.entities.dtos.product.ProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public DataResult<List<ProductDto>> getAll(@RequestParam(name = "search") Optional<String> productName,
                                               @RequestParam(name = "page") Optional<Integer> pageNo,
                                               @RequestParam(name = "size") Optional<Integer> pageSize) {
        return productService.getAll(productName, pageNo, pageSize);
    }

    @GetMapping("/{id}")
    public DataResult<ProductDto> getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @GetMapping("/{id}/images")
    public DataResult<List<ImagesDto>> getProductImagesById(@PathVariable int id) {
        return productService.getProductImagesById(id);
    }

    @GetMapping("/{id}/category")
    public DataResult<CategoryDto> getProductCategoryById(@PathVariable int id) {
        return productService.getProductCategoryById(id);
    }

    @PostMapping
    public Result addProduct(@Valid @RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PutMapping("/{id}")
    public Result updateProduct(@PathVariable int id, @Valid @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public Result deleteProduct(@PathVariable int id) {
        return productService.deleteProduct(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ErrorDataResult<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
                .collect(
                        java.util.stream.Collectors.toMap(
                                fieldError -> fieldError.getField(),
                                fieldError -> fieldError.getDefaultMessage()
                        )
                );
        ErrorDataResult<Object> errorDataResult = new ErrorDataResult<Object>(errors, "Validation errors", "400");
        return errorDataResult;
    }
}
