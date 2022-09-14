package com.cspassion.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cspassion.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
