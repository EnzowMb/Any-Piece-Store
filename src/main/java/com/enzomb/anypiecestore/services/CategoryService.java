package com.enzomb.anypiecestore.services;

import com.enzomb.anypiecestore.domain.category.Category;
import com.enzomb.anypiecestore.domain.category.CategoryDTO;
import com.enzomb.anypiecestore.domain.category.exceptions.CategoryNotFoundException;
import com.enzomb.anypiecestore.repositories.CategoryRepository;
import com.enzomb.anypiecestore.services.aws.AwsSnsService;
import com.enzomb.anypiecestore.services.aws.MessageDTO;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepository repository;

    private AwsSnsService snsService;

    public CategoryService(CategoryRepository repository, AwsSnsService snsService) {
        this.repository = repository;
        this.snsService = snsService;
    }

    public Category insert(CategoryDTO categoryData) {
        Category newCategory = new Category(categoryData);
        this.repository.save(newCategory);

        this.snsService.publish(new MessageDTO(newCategory.toString()));

        return newCategory;
    }

    public Category update(String id, CategoryDTO categoryData) {
        Category category = this.repository.findById(id).orElseThrow(CategoryNotFoundException::new);

        if(!categoryData.title().isEmpty()) category.setTitle(categoryData.title());

        if(!categoryData.description().isEmpty()) category.setDescription(categoryData.description());

        //O spring data mongo db percebe que esta categoria já existe e atualiza dados!!!
        this.repository.save(category);

        this.snsService.publish(new MessageDTO(category.toString()));

        return category;
    }

    public Category delete(String id) {
        Category category = this.repository.findById(id).orElseThrow(CategoryNotFoundException::new);

        this.repository.delete(category);

        this.snsService.publish(new MessageDTO(category.deleteCategoryAWS()));

        return category;
    }

    public List<Category> getAll() {
        return this.repository.findAll();
    }

    public Optional<Category> getById(String id) {
        return this.repository.findById(id);
    }
}
