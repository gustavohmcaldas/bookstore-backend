package com.gustavohmcaldas.bookstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gustavohmcaldas.bookstore.domain.Book;
import com.gustavohmcaldas.bookstore.domain.Category;
import com.gustavohmcaldas.bookstore.repository.BookRepository;
import com.gustavohmcaldas.bookstore.service.exceptions.ObjectNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

	private final BookRepository bookRepository;
	
	private final CategoryService categoryService;
	
	public Book findById(Long id) {
		return bookRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Object not found. Id: " + id + ", Type: " + Book.class.getName()));
	}

	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	public List<Book> findAll(Long catId) {
		categoryService.findById(catId);
		
		return bookRepository.findAllByCategory(catId);
	}

	public Book update(Long id, Book obj) {
		Book newObj = findById(id);
		updateData(newObj, obj);
		
		return bookRepository.save(newObj);
	}

	private void updateData(Book newObj, Book obj) {
		newObj.setTitle(obj.getTitle());
		newObj.setAuthor(obj.getAuthor());
		newObj.setDescription(obj.getDescription());
	}

	public Book create(Long catId, Book obj) {
		obj.setId(null);
		Category cat = categoryService.findById(catId);
		obj.setCategory(cat);
		return bookRepository.save(obj);
	}

	public void delete(Long id) {
		Book obj = findById(id);
		bookRepository.delete(obj);
	}
}
