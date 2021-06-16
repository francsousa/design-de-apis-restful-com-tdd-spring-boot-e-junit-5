package br.com.francisco.libraryapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.francisco.libraryapi.model.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

	boolean existsByIsbn(String isbn);
}
