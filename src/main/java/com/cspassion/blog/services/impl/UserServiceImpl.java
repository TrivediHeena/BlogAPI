package com.cspassion.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cspassion.blog.entities.User;
import com.cspassion.blog.exceptions.ResourceNotFoundException;
import com.cspassion.blog.payloads.UserDto;
import com.cspassion.blog.repositories.UserRepo;
import com.cspassion.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user=this.dtoToUser(userDto);
		User savedUser=this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user=this.userRepo.findById(userId).
				orElseThrow(()->new ResourceNotFoundException("User"," Id ",userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUSer=this.userRepo.save(user);		
		return this.userToDto(updatedUSer);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user=this.userRepo.findById(userId).
				orElseThrow(()->new ResourceNotFoundException("User"," Id ",userId));
		
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getUsers() {
		List<User> users=this.userRepo.findAll();
		List<UserDto> usersDto=users.stream().map(user->userToDto(user)).collect(Collectors.toList());
		return usersDto;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
		this.userRepo.delete(user);
		
	}

	private User dtoToUser(UserDto userDto) {
		BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
		User user=new User();
		//User user=this.modelMapper.map(userDto,User.class);
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		//user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		/*PropertyMap<UserDto, User> props=new PropertyMap<UserDto, User>() {

			@Override
			protected void configure() {
				// TODO Auto-generated method stub
				map().setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
			}
			
		};
		this.modelMapper.addMappings(props);*/ 
		return user;
	}
	
	private UserDto userToDto(User user) {
		BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
		UserDto userDto=new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		//userDto.setPassword(user.getPassword());
		userDto.setAbout(user.getAbout());
		return userDto;
	}
}
