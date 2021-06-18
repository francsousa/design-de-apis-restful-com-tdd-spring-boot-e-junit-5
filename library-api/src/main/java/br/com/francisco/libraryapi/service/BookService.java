package br.com.francisco.libraryapi.service;

import java.util.Optional;

import br.com.francisco.libraryapi.model.entity.Book;

public interface BookService {

	Book save(Book any);

	Optional<Book> getById(Long id);

	void delete(Book book);

	Book update(Book book);
}
