package com.example.springapi.product;


import com.example.springapi.product.dto.ProductRequestDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // GET /product
    @GetMapping
    public ResponseEntity<List<Product>> index() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    //GET /product/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Product> read(@PathVariable UUID id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    // POST /product
    // @Valid ativa a validação que colocamos no DTO (Substitui o validateSchema do Joi)
    @PostMapping
    public ResponseEntity<Product> create(@RequestBody @Valid ProductRequestDTO dto) {
        Product createdProduct = productService.createProduct(dto);
        return ResponseEntity.ok(createdProduct);
    }

    // PUT /product/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable UUID id, @RequestBody @Valid ProductRequestDTO dto) {
        Product updatedProduct = productService.updatedProduct(id, dto);
        return ResponseEntity.ok(updatedProduct);
    }

    // DELETE /product/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable UUID id) {
        productService.removeProduct(id);
        return ResponseEntity.noContent().build();
    }


}
