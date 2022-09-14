package com.cspassion.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cspassion.blog.entities.Category;
import com.cspassion.blog.exceptions.ResourceNotFoundException;
import com.cspassion.blog.payloads.CategoryDto;
import com.cspassion.blog.repositories.CategoryRepo;
import com.cspassion.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category=this.modelMapper.map(categoryDto, Category.class);
		category.setCategorySlug(category.getCategoryTitle().replace(" ", "-").toLowerCase());
		Category addedCategory=this.categoryRepo.save(category);
		return this.modelMapper.map(addedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundException("Category ", "Category Id", categoryId));
		
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategorySlug(categoryDto.getCategoryTitle().replace(" ", "-").toLowerCase());
		/*}else {			
			cat.setCategorySlug(categoryDto.getCategorySlug());
		}*/
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		cat.setCategoryPostCount(categoryDto.getCategoryPostCount());
		
		Category updatedCategory=this.categoryRepo.save(cat);
		
		return this.modelMapper.map(updatedCategory, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundException("Category ", "Category Id", categoryId));

		this.categoryRepo.delete(cat);
		
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundException("Category ", "Category Id", categoryId));

		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {
		List<Category> lstCat = this.categoryRepo.findAll();
		List<CategoryDto> lstCatDto = lstCat.stream().map((cat)->this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return lstCatDto;
	}
	
}
 