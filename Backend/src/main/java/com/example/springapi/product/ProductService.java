package com.example.springapi.product;

import com.example.springapi.product.dto.ProductRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(UUID id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado."));
    }

    public Product createProduct(ProductRequestDTO dto) {
        if(productRepository.existsByName(dto.name())) {
            throw new RuntimeException("Nome do produto já existe na plataforma.");
        }

        Product newProduct = new Product();
        newProduct.setName(dto.name());
        newProduct.setDescription(dto.description());
        newProduct.setPrice(dto.price());
        newProduct.setStock(dto.stock());

        return productRepository.save(newProduct);
    }

    public Product updatedProduct(UUID id, ProductRequestDTO dto){
        Product existingProduct = getProductById(id);

        if(productRepository.existsByName(dto.name())) {
            throw new RuntimeException("Nome do produto já existe na plataforma.");
        }

        existingProduct.setName(dto.name());
        existingProduct.setDescription(dto.description());
        existingProduct.setPrice(dto.price());
        existingProduct.setStock(dto.stock());

        return productRepository.save(existingProduct);
    }

    public void removeProduct(UUID id){
        Product product = getProductById(id);
        productRepository.delete(product);
    }
}
