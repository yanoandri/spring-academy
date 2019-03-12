package com.example.academy.controller.category;

import com.example.academy.model.custom.response.MetaResponse;
import com.example.academy.model.custom.response.Response;
import com.example.academy.model.entity.Category;
import com.example.academy.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(@Qualifier("CategoryServiceImplement") CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/show")
    public ResponseEntity login(){
        Response response = new Response();
        List<Category> categories = categoryService.showCategory();
        response.setStatus(Response.MESSAGE_OK);
        response.setData(categories);
        response.setMeta(new MetaResponse(HttpStatus.OK.value(), "success", ""));
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public ResponseEntity addCategory(@RequestBody  Category category){
        Response response = new Response();
        Category newCategory = categoryService.addCategory(category);
        response.setStatus(Response.MESSAGE_OK);
        response.setData(newCategory);
        response.setMeta(new MetaResponse(HttpStatus.OK.value(), "Category has been created", ""));
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping(value = "/edit/{id}")
    public ResponseEntity editCategory(@PathVariable("id") Long id, @RequestBody Category category){
        Response response = new Response();
        Category newCategory = categoryService.editCategory(id,category);
        response.setStatus(Response.MESSAGE_OK);
        response.setData(newCategory);
        response.setMeta(new MetaResponse(HttpStatus.OK.value(), "Category has been edited", ""));
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping(value = "/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable("id") Long id){
        Response response = new Response();
        int isSuccess = categoryService.deleteCategory(id);
        response.setStatus(Response.MESSAGE_OK);
        response.setMeta(new MetaResponse(isSuccess, "Category has been deleted", ""));
        return new ResponseEntity(response, HttpStatus.OK);
    }

}
