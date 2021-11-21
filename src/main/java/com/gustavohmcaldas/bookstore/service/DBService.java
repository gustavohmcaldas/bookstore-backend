package com.gustavohmcaldas.bookstore.service;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.gustavohmcaldas.bookstore.domain.Book;
import com.gustavohmcaldas.bookstore.domain.Category;
import com.gustavohmcaldas.bookstore.repository.BookRepository;
import com.gustavohmcaldas.bookstore.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DBService {

	private final CategoryRepository categoryRepository;
	private final BookRepository bookRepository;
	
	public void dataBaseInstance() {
		Category selfHelp = new Category(null, "Self Help", 
				"Self-help, or self-improvement, is a self-guided improvement.");
		
		Category business = new Category(null, "Business", 
				"A business (also known as enterprise or firm) is an organization engaged in the trade of goods, services, or both to consumers..");
		
		Category nonFiction = new Category(null, "Nonfiction", 
				"Nonfiction is an account or representation of a subject which is presented as fact.");
		
		Book book1 = new Book(null, "Rich Dad, Poor Dad", "Robert T. Kiyosaki", 
				"Rich Dad Poor Dad is Robert's story of growing up with two dads.",
				business);
		
		Book book2 = new Book(null, "Think and Grow Rich", "Napoleon Hill", 
				"This is the original 1937 version of Napoleon Hill's Classic Book: Think and Grow Rich.", 
				business);
		
		Book book3 = new Book(null, "How to Win Friends and Influence People", "Dale Carnegie", 
				"You can go after the job you want...and get it! You can take the job you have...and improve it!.", 
				selfHelp);
		
		Book book4 = new Book(null, "The Richest Man in Babylon", "George S. Clason", 
				"Beloved by millions, this timeless classic holds the key to all you desire and everything you wish to accomplish.", 
				business);
		
		Book book5 = new Book(null, "Secrets of the Millionaire Mind", "T. Harv Eker", 
				"Have you ever wondered why some people seem to get rich easily, while others are destined for a life of financial struggle?", 
				business);
		
		Book book6 = new Book(null, "The 48 Laws of Power", "Robert Greene", 
				"In the book that People magazine proclaimed.", 
				nonFiction);
		
		Book book7 = new Book(null, "The Art of War", "Sun Tzu", 
				"Twenty-Five Hundred years ago, Sun Tzu wrote this classic book of military strategy based on Chinese warfare and military thought.", 
				nonFiction);
		
		Book book8 = new Book(null, "The Power of Habit", "Charles Duhigg", 
				"A young woman walks into a laboratory. Over the past two years, she has transformed almost every aspect of her life.", 
				selfHelp);
		
		Book book9 = new Book(null, "The Power of Now", "Eckhart Tolle", 
				"Eckhart Tolle's message is simple: living in the now is the truest path to happiness and enlightenment.", 
				selfHelp);
		
		Book book10 = new Book(null, "Who Moved My Cheese?", "Spencer Johnson", 
				"It is the amusing and enlightening story of four characters who live in a maze and look for cheese to nourish them and make them happy.", 
				selfHelp);		

		categoryRepository.saveAll(Arrays.asList(selfHelp, business, nonFiction));
		bookRepository.saveAll(Arrays.asList(book1, book2, book3, book4, book5, book6, book7, book8, book9, book10));
	}
}
