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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gustavohmcaldas.bookstore.domain.Book;
import com.gustavohmcaldas.bookstore.dto.BookDTO;
import com.gustavohmcaldas.bookstore.service.BookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/books")
@RequiredArgsConstructor
public class BookController {

	private final BookService bookService;
	
	@Operation(summary = "Get book by id", tags = {"book"})
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
	@GetMapping(value = "/{id}")
	public ResponseEntity<Book> findById(@PathVariable Long id) {
		Book obj = bookService.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@Operation(summary = "List all books per category", tags = {"books"})
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Successful Operation"),
	        @ApiResponse(responseCode = "404", description = "Not Found")
	})
	@GetMapping
	public ResponseEntity<List<BookDTO>> findAll(@RequestParam(value = "category", defaultValue = "0") Long catId) {
		List<Book> list = bookService.findAll(catId);
		List<BookDTO> dtoList = list.stream().map(obj -> new BookDTO(obj)).collect(Collectors.toList());
				
		return ResponseEntity.ok().body(dtoList);
	}
	
	@Operation(summary = "Insert a new book", tags = {"book"})
	@ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful Operation")
	})
	@PostMapping
	public ResponseEntity<Book> create(@RequestParam(value = "category", defaultValue = "0") Long catId, @Valid @RequestBody Book obj) {
		Book newObj = bookService.create(catId, obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/books/{id}").buildAndExpand(newObj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@Operation(summary = "Update book", tags = {"book"})
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "204", description = "Successful Operation"),
	        @ApiResponse(responseCode = "400", description = "Bad Request"),
	        @ApiResponse(responseCode = "404", description = "Not Found")
	})
	@PutMapping(value = "/{id}")
	public ResponseEntity<Book> update(@PathVariable Long id, @Valid @RequestBody Book obj) {
		Book newObj = bookService.update(id, obj);
		
		return ResponseEntity.ok().body(newObj);
	}
	
	@Operation(summary = "Remove book", tags = {"book"})
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "204", description = "Successful Operation"),
	        @ApiResponse(responseCode = "404", description = "Not Found")
	})
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		bookService.delete(id);
		
		return ResponseEntity.noContent().build();
	}
}
