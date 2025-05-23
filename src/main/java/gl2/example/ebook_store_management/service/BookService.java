package gl2.example.ebook_store_management.service;

import gl2.example.ebook_store_management.entity.Book;
import gl2.example.ebook_store_management.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }
}