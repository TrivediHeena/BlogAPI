package com.cspassion.blog.payloads;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

	private Integer categoryId;
	@NotEmpty
	@Size(min=2,message="Category title must be min 2 characters long")
	private String categoryTitle;
	//@NotEmpty(message = "Please enter proper slug in small letters")
	private String categorySlug;//=categoryTitle.replaceAll(" ", "-").toLowerCase();
	@NotEmpty
	private String categoryDescription;
	
	private Integer categoryPostCount;

}
