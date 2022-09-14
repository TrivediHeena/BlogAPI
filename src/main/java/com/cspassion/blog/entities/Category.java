package com.cspassion.blog.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="category")
@NoArgsConstructor
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;
	
	@Column(name="cateTitle",length=100,nullable=false)
	private String categoryTitle;
	@Column(name="cateSlug",length=100,nullable=false)
	private String categorySlug;
	@Column(name="cateDescr")
	private String categoryDescription;
	@Column(name="catePostCount",columnDefinition = "integer default 0",insertable = false)
	private Integer categoryPostCount;
	
	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Post> posts=new ArrayList<>();
}
