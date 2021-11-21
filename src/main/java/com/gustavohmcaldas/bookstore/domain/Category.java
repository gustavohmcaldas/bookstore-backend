package com.gustavohmcaldas.bookstore.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "Name is required.")
	@Length(min = 3, max = 50, message = "Name must be between 3 and 50 characters.")
	@Schema(description = "This is the category's name", example = "Fiction", required = true)
	private String name;

	@NotEmpty(message = "Description is required.")
	@Length(min = 3, max = 200, message = "Description must be between 3 and 200 characters.")
	@Schema(description = "This is the category's description", example = "Fiction is the telling of stories which are not real. More specifically, fiction is an imaginative form of narrative, one of the four basic rhetorical modes.", required = true)
	private String description;

	@OneToMany(mappedBy = "category")
	private List<Book> books = new ArrayList<Book>();

	public Category(Long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

}
