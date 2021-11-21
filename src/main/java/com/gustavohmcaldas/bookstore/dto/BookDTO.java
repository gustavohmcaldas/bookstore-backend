package com.gustavohmcaldas.bookstore.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.gustavohmcaldas.bookstore.domain.Book;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookDTO {
	
	private Long id;
	
	@NotEmpty(message = "Title is required.")
	@Length(min = 3, max = 50, message = "Title must be between 3 and 50 characters.")
	private String title;

	public BookDTO(Book obj) {
		super();
		this.id = obj.getId();
		this.title = obj.getTitle();
	}
	
}
