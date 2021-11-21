package com.gustavohmcaldas.bookstore.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.gustavohmcaldas.bookstore.domain.Category;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotEmpty(message = "Name is required.")
	@Length(min = 3, max = 50, message = "Name must be between 3 and 50 characters.")
	private String name;

	@NotEmpty(message = "Description is required.")
	@Length(min = 3, max = 200, message = "Name must be between 3 and 200 characters.")
	private String description;
	
	public CategoryDTO(Category obj) {
		super();
		this.id = obj.getId();
		this.name = obj.getName();
		this.description = obj.getDescription();
	}
}
