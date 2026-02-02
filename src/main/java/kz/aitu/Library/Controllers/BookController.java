package kz.aitu.Library.Controllers;

import kz.aitu.Library.entities.inventory.Book;
import kz.aitu.Library.repository.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {

    @PostMapping("/addBooks")
    public String addBooks(@RequestBody List<Book> books) {
        for (Book book : books) {
            BookRepository.addBook(book.getId(), book.getTitle(), book.getAuthor(), book.getYear());
        }
        return "Все книги ( " + books.size() + " шт.) успешно добавлены!";
    }

    @DeleteMapping("/deleteBook")
    public String deleteBook(@RequestBody Book book) {
        BookRepository.deleteBook(book.getTitle());
        return "Book deleted";
    }

    @PostMapping("/updateBookId")
    public String updateBookId(@RequestBody Map<String, Object> bookData) {
        String title = (String) bookData.get("title");
        Integer newBookId = (Integer) bookData.get("newBookId");
        BookRepository.updateBookId(title, newBookId);
        return "Book ID updated";
    }

    @GetMapping("/getBooks")
    public List<Book> getBooks() {
        return BookRepository.getAllBooks();
    }

    @GetMapping("/getBook/{id}")
    public Book getBookById(@PathVariable String id) {
        return BookRepository.findBookById(id);
    }
}