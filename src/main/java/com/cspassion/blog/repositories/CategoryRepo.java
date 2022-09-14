package com.cspassion.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cspassion.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
