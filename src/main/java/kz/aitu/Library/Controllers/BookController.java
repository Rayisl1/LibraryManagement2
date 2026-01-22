package kz.aitu.Library.Controllers;

import kz.aitu.Library.entities.Book;
import kz.aitu.Library.entities.DataBaseControl;
import kz.aitu.Library.entities.LibraryMember;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {

    // Массовое добавление книг
    @PostMapping("/addBooks")
    public String addBooks(@RequestBody List<Book> books) {
        for (Book book : books) {
            DataBaseControl.addBook(book.getId(), book.getTitle(), book.getAuthor(), book.getYear());
        }
        return "Все книги ( " + books.size() + " шт.) успешно добавлены!";
    }

    // Delete Book
    @DeleteMapping("/deleteBook")
    public String deleteBook(@RequestBody Book book) {
        DataBaseControl.deleteBook(book.getTitle());
        return "Book deleted";
    }

    @PostMapping("/updateBookId")
    public String updateBookId(@RequestBody Map<String, Object> bookData) {
        String title = (String) bookData.get("title");
        Integer newBookId = (Integer) bookData.get("newBookId");

        // Вызов метода для обновления ID книги
        DataBaseControl.updateBookId(title, newBookId);
        return "Book ID updated";
    }



    // Print Book Information
    @GetMapping("/printTablesInfoBook")
    public String printTablesInfoBook() {
        DataBaseControl.printTablesInfoBook();
        return "Book information printed";
    }

    // Вывод всех книг в Postman
    @GetMapping("/getBooks")
    public List<Book> getBooks() {
        return DataBaseControl.getAllBooks();
    }

//    // Вывод всех участников в Postman
//    @GetMapping("/getMembers")
//    public List<LibraryMember> getMembers() {
//        return DataBaseControl.getAllMembers();
//    }


}
