package com.cspassion.blog.payloads;

import java.util.Date;

import com.cspassion.blog.entities.Category;
import com.cspassion.blog.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {

	private Integer id;
	private String title;
	private String content;
	private String imageName;	
	private Date createdDate;
	private CategoryDto category;
	private UserDto user;
}
