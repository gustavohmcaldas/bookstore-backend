package com.gustavohmcaldas.bookstore.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gustavohmcaldas.bookstore.domain.Category;
import com.gustavohmcaldas.bookstore.dto.CategoryDTO;
import com.gustavohmcaldas.bookstore.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/categories")
@RequiredArgsConstructor
public class CategoryController {

	private final CategoryService categoryService;
	
	@GetMapping(value = "/{id}")
	@Operation(summary =  "Get category by id", tags = "category")
	@ApiResponses(value =  {
			@ApiResponse(responseCode = "200", description = "Successful Operation"),
			@ApiResponse(responseCode = "404", description = "Not Found")
	})
	public ResponseEntity<Category> findById(@PathVariable Long id) {
		Category obj = categoryService.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@Operation(summary = "List all categories", tags = "categories")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful Operation"),
			@ApiResponse(responseCode = "404", description = "Not Found")
	})
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> findAll() {
		List<Category> list = categoryService.findAll();
		List<CategoryDTO> dtoList = list.stream().map(obj -> new CategoryDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(dtoList);
	}
	
	@Operation(summary = "Insert a new category", tags = "category")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Successful Operation")
	})
	@PostMapping
	public ResponseEntity<Category> create(@Valid @RequestBody Category obj) {
		obj = categoryService.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@Operation(summary = "Update category", tags = "category")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Successful Operation"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "404", description = "Not Found")
	})
	@PutMapping(value = "/{id}")
	public ResponseEntity<CategoryDTO> update(@PathVariable Long id, @RequestBody CategoryDTO objDto) {
		Category newObj = categoryService.update(id, objDto);
		
		return ResponseEntity.ok().body(new CategoryDTO(newObj));
	}
	
	@Operation(summary = "Delete category", tags = "category")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Successful Operation"),
			@ApiResponse(responseCode = "404", description = "Not Found")
	})
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		categoryService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
