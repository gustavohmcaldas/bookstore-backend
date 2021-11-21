package com.gustavohmcaldas.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gustavohmcaldas.bookstore.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
