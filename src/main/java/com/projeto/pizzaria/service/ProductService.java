package com.projeto.pizzaria.service;


import com.projeto.pizzaria.DTO.ProductDTO;
import com.projeto.pizzaria.entities.Product;
import com.projeto.pizzaria.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;
    private ProductDTO productDTO;

    //find al products
    public List<Product> findAllProducts(){
        return repository.findAll();
    }


    // find product by id
    public Product findByid(UUID id){
        return repository.findById(id).orElseThrow(()-> new RuntimeException("Product not found"));
    }


    // criar um novo produto
    @Transactional
    public Product saveProduct(Product product){
        if (product.getName().length()<3){
            throw new RuntimeException("product name is very short, try a large number of characters.");
        }
        return repository.save(product);
    }

    //update product
    @Transactional
    public Product updateProduct(UUID id ,Product product){
        Product existingProduct = repository.findById(id).orElseThrow(()-> new RuntimeException("Product not found"));
        existingProduct.setName(productDTO.getName());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setPrice(productDTO.getPrice());
        return repository.save(existingProduct);
    }
    //update partial product
    @Transactional
    public Product PatchProduct(UUID id ,Product product){
        Product existingProduct = repository.findById(id).orElseThrow(()-> new RuntimeException("Product not found"));
        if (product.getName() != null) {
            existingProduct.setName(product.getName());
        }
        if (product.getDescription() != null) {
            existingProduct.setDescription(product.getDescription());
        }

        if (product.getPrice() != null) {
            existingProduct.setPrice(product.getPrice());
        }
        return repository.save(existingProduct);
    }

    //deletar um produto
    public void deleteProduct(UUID id){
        Product existingProduct = repository.findById(id).orElseThrow(()-> new RuntimeException("Product not found"));
        repository.delete(existingProduct);
    }


}
