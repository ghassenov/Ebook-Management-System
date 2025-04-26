package gl2.example.ebook_store_management.controller;

import gl2.example.ebook_store_management.entity.Book;
import gl2.example.ebook_store_management.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    // Thread-safe storage for "My Books" collection
    private List<Book> myBooksCollection = new ArrayList<>();

    /* ========== VIEW ENDPOINTS ========== */

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/available_books")
    public String availableBooksPage(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "availableBooks";
    }

    @GetMapping("/book_register")
    public String bookRegisterPage(Model model) {
        model.addAttribute("book", new Book());
        return "bookRegister";
    }

    @GetMapping("/my_books")
    public String myBooksPage(Model model) {
        model.addAttribute("myBooks", myBooksCollection);
        model.addAttribute("totalPrice", calculateTotalPrice());
        return "my_books";
    }


    @PostMapping("/save_book")
    public String saveBook(@ModelAttribute Book book, RedirectAttributes redirectAttributes) {
        bookService.saveBook(book);
        redirectAttributes.addFlashAttribute("message", "Book registered successfully!");
        return "redirect:/available_books";
    }

    @GetMapping("/add_to_mybooks/{id}")
    public String addToMyBooks(@PathVariable int id, RedirectAttributes redirectAttributes) {
        Book book = bookService.getBookById(id);
        if (book != null && !myBooksCollection.contains(book)) {
            myBooksCollection.add(book);
            redirectAttributes.addFlashAttribute("message", "Book added to your collection!");
        }
        return "redirect:/available_books";
    }

    @GetMapping("/remove_from_mybooks/{id}")
    public String removeFromMyBooks(@PathVariable int id) {
        myBooksCollection.removeIf(book -> book.getId() == id);
        return "redirect:/my_books";
    }

    @GetMapping("/book_edit/{id}")
    public String editBookPage(@PathVariable int id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "bookEdit"; // You'll need to create this template
    }

    @GetMapping("/book_delete/{id}")
    public String deleteBook(@PathVariable int id, RedirectAttributes redirectAttributes) {
        bookService.deleteBook(id);
        myBooksCollection.removeIf(book -> book.getId() == id);
        redirectAttributes.addFlashAttribute("message", "Book deleted successfully!");
        return "redirect:/available_books";
    }

    @PostMapping("/book_update/{id}")
    public String updateBook(@PathVariable int id, @ModelAttribute Book book, RedirectAttributes redirectAttributes) {
        book.setId(id); // Ensure ID is preserved
        bookService.saveBook(book);
        redirectAttributes.addFlashAttribute("message", "Book updated successfully!");
        return "redirect:/available_books";
    }


    @PostMapping("/api/books")
    @ResponseBody
    public Book saveBookApi(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @GetMapping("/api/books")
    @ResponseBody
    public List<Book> getAllBooksApi() {
        return bookService.getAllBooks();
    }

    @GetMapping("/api/books/{id}")
    @ResponseBody
    public Book getBookByIdApi(@PathVariable int id) {
        return bookService.getBookById(id);
    }

    private BigDecimal calculateTotalPrice() {
        return myBooksCollection.stream()
                .map(Book::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}