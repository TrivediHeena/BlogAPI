package com.cspassion.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cspassion.blog.payloads.ApiResponse;
import com.cspassion.blog.payloads.CategoryDto;
import com.cspassion.blog.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto createCate=this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createCate,HttpStatus.CREATED);
	}
	
	@PutMapping("/{cateId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer cateId){
		CategoryDto updateCate=this.categoryService.updateCategory(categoryDto, cateId);
		return new ResponseEntity<CategoryDto>(updateCate,HttpStatus.OK);
	}
	
	@DeleteMapping("/{cateId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer cateId){
		this.categoryService.deleteCategory(cateId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully!", false),HttpStatus.OK);
	}
	
	@GetMapping("/{cateId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer cateId){
		CategoryDto category = this.categoryService.getCategory(cateId);
		return new ResponseEntity<CategoryDto>(category,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getCategories(){
		List<CategoryDto> category=this.categoryService.getCategories();
		return ResponseEntity.ok(category);
	}
}
