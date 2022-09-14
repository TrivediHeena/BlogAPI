package com.cspassion.blog.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;
	@NotEmpty
	@Size(min=3,message = "Name must be more than 3 chars!!")
	private String name;
	@Email(message = "Plz. enter valid mail id!!")
	private String email;
	@NotEmpty
	@Size(min=3,max=10,message = "Plz. enter valid password")
	//@Pattern(regexp = "")
	private String password;
	@NotNull
	private String about;
}
