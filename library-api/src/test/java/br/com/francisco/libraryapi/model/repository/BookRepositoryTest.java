package br.com.francisco.libraryapi.model.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.francisco.libraryapi.model.entity.Book;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class BookRepositoryTest {
	
	@Autowired
	TestEntityManager entityManager;
	
	@Autowired
	BookRepository repository;
	
	@Test
	@DisplayName("Deve retornar verdadeiro quando existir um livro na base com o isbn informado")
	public void returnTrueWhenIsbnExists() {
		// cenário
		String isbn = "123";
		Book book = createNewBook(isbn);
		entityManager.persist(book);
		
		// execução
		boolean exists = repository.existsByIsbn(isbn);
		
		// verificação
		assertThat(exists).isTrue();
	}

	@Test
	@DisplayName("Deve retornar falso quando não existir um livro na base com o isbn informado")
	public void returnFalseWhenIsbnDoesntExists() {
		// cenário
		String isbn = "123";
	
		// execução
		boolean exists = repository.existsByIsbn(isbn);
		
		// verificação
		assertThat(exists).isFalse();
	}
	
	@Test
	@DisplayName("Deve obter um livro por id")
	public void findByIdTest() {
		// cenário
		Book book = createNewBook("123");
		entityManager.persist(book);
		
		// execução
		Optional<Book> foundBook = repository.findById(book.getId());
		
		// verificação
		assertThat(foundBook.isPresent()).isTrue();
	}
	
	private Book createNewBook(String isbn) {
		return Book.builder().title("Aventuras").author("Fulano").isbn(isbn).build();
	}
	
}
