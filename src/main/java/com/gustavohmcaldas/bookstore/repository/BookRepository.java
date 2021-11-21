package com.gustavohmcaldas.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gustavohmcaldas.bookstore.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

	@Query("SELECT obj FROM Book obj WHERE obj.category.id = :catId ORDER BY title")
	List<Book> findAllByCategory(@Param(value = "catId") Long catId);

}
