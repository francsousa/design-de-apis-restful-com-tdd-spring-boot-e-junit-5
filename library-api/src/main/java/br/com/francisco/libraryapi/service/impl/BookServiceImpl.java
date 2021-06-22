package br.com.francisco.libraryapi.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.francisco.libraryapi.exception.BusinessException;
import br.com.francisco.libraryapi.model.entity.Book;
import br.com.francisco.libraryapi.model.repository.BookRepository;
import br.com.francisco.libraryapi.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	private BookRepository repository;
	
	public BookServiceImpl(BookRepository repository) {
		this.repository = repository;
	}

	@Override
	public Book save(Book book) {
		if (repository.existsByIsbn(book.getIsbn())) {
			throw new BusinessException("Isbn já cadastrado");
		}
		
		return repository.save(book);
	}

	@Override
	public Optional<Book> getById(Long id) {
		return this.repository.findById(id);
	}

	@Override
	public void delete(Book book) {
		if (book == null || book.getId() == null) {
			throw new IllegalArgumentException("Book id can't be null");
		}
		this.repository.delete(book);
	}

	@Override
	public Book update(Book book) {
		if (book == null || book.getId() == null) {
			throw new IllegalArgumentException("Book id can't be null");
		}
		return this.repository.save(book);
	}

	@Override
	public Page<Book> find(Book filter, Pageable pageRequest) {
		return null;
	}
}
