package com.gustavohmcaldas.bookstore.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Book implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "Title is required.")
	@Length(min = 3, max = 50, message = "Title must be between 3 and 50 characters.")
	@Schema(description = "This is the book's title", example = "How to Stop Worrying and Start Living", required = true)
	private String title;

	@NotEmpty(message = "Author is required.")
	@Length(min = 3, max = 50, message = "Author must be between 3 and 50 characters.")
	@Schema(description = "This is the book's author", example = "Dale Carnegie", required = true)
	private String author;

	@NotEmpty(message = "Description is required.")
	@Length(min = 10, max = 1000, message = "Description must be between 10 and 5000 characters.")
	@Schema(description = "This is the book's description", example = "The book 'How to stop worrying & start living' suggest many ways to conquer worry and lead a wonderful life.", required = true)
	private String description;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "category_id")
	private Category category;

	public Book(Long id, String title, String author, String description, Category category) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.description = description;
		this.category = category;
	}

}
