package com.enzomb.anypiecestore.services;

import com.enzomb.anypiecestore.domain.category.Category;
import com.enzomb.anypiecestore.domain.category.exceptions.CategoryNotFoundException;
import com.enzomb.anypiecestore.domain.product.Product;
import com.enzomb.anypiecestore.domain.product.ProductDTO;
import com.enzomb.anypiecestore.domain.product.exceptions.ProductNotFoundException;
import com.enzomb.anypiecestore.repositories.ProductRepository;
import com.enzomb.anypiecestore.services.aws.AwsSnsService;
import com.enzomb.anypiecestore.services.aws.MessageDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private CategoryService categoryService;
    private ProductRepository repository;
    private AwsSnsService snsService;

    public ProductService(CategoryService categoryService, ProductRepository productRepository, AwsSnsService snsService) {
        this.categoryService = categoryService;
        this.repository = productRepository;
        this.snsService = snsService;
    }

    public Product insert(ProductDTO productData) {
        this.categoryService.getById(productData.categoryId()).orElseThrow(CategoryNotFoundException::new);
        Product newProduct = new Product(productData);

        this.repository.save(newProduct);

        this.snsService.publish(new MessageDTO(newProduct.toString()));

        return newProduct;
    }

    public Product update(String id, ProductDTO productData) {
        Product product = this.repository.findById(id).orElseThrow(ProductNotFoundException::new);

        if(productData.categoryId() != null) {

            this.categoryService.getById(productData.categoryId()).orElseThrow(CategoryNotFoundException::new);


            product.setCategory(productData.categoryId());
        }

        if(!productData.title().isEmpty()) product.setTitle(productData.title());

        if(!productData.description().isEmpty()) product.setDescription(productData.description());

        if(!(productData.price() == null)) product.setPrice(productData.price());

        //O spring data mongo db percebe que este produto já existe e atualiza dados!!!
        this.repository.save(product);

        this.snsService.publish(new MessageDTO(product.toString()));

        return product;
    }

    public Product delete(String id) {
        Product product = this.repository.findById(id).orElseThrow(ProductNotFoundException::new);

        this.repository.delete(product);

        this.snsService.publish(new MessageDTO(product.deleteCategoryAWS()));

        return product;
    }

    public List<Product> getAll() {
        return this.repository.findAll();
    }
}
