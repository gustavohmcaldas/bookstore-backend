package com.gustavohmcaldas.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gustavohmcaldas.bookstore.domain.Category;
import com.gustavohmcaldas.bookstore.dto.CategoryDTO;
import com.gustavohmcaldas.bookstore.repository.CategoryRepository;
import com.gustavohmcaldas.bookstore.service.exceptions.ObjectNotFoundException;
import com.gustavohmcaldas.bookstore.service.exceptions.DataIntegrityViolationException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

	private final CategoryRepository categoryRepository;
	
	public Category findById(Long id) {
		return categoryRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Object not found: Id: " + id + ", Type: " + Category.class.getName()));
	}

	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	public Category create(Category obj) {
		obj.setId(null);
		return categoryRepository.save(obj);
	}

	public Category update(Long id, CategoryDTO objDto) {
		Category obj = findById(id);
		obj.setName(objDto.getName());
		obj.setDescription(objDto.getDescription());
		return categoryRepository.save(obj);
	}

	public void delete(Long id) {
		findById(id);
		try {
			categoryRepository.deleteById(id);			
		}
		catch (Exception e) {
			throw new DataIntegrityViolationException("Category cannot be deleted. There are books associated.");
		}
	}

}
