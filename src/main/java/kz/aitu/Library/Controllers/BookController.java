package kz.aitu.Library.Controllers;

import kz.aitu.Library.entities.Book;
import kz.aitu.Library.entities.DataBaseControl;
import kz.aitu.Library.entities.LibraryMember;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class BookController {

    // Add Book
    @PostMapping("/addBook")
    public String addBook(@RequestBody Book book) {
        DataBaseControl.addBook(book.getId(), book.getTitle(), book.getAuthor(), book.getYear());
        return "Book added";
    }

    // Delete Book
    @PostMapping("/deleteBook")
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

    // Add Library Member
    @PostMapping("/addLibraryMember")
    public String addLibraryMember(@RequestBody Map<String, String> member) {
        Integer id = Integer.parseInt(member.get("id"));
        String name = member.get("name");
        DataBaseControl.addLibraryMember(id, name);
        return "Library Member added";
    }

    // Delete Library Member
    @PostMapping("/deleteLibraryMember")
    public String deleteLibraryMember(@RequestBody Map<String, Integer> member) {
        Integer id = member.get("id");
        DataBaseControl.deleteLibraryMember(id);
        return "Library member deleted";
    }

    // Update Library Member ID
    @PostMapping("/updateLibraryMemberId")
    public String updateLibraryMemberId(@RequestBody Map<String, Object> memberData) {
        String name = (String) memberData.get("name");
        Integer newId = (Integer) memberData.get("newId");

        // Вызов метода для обновления ID члена библиотеки по имени
        DataBaseControl.updateLibraryMemberId(name, newId);
        return "Library member ID updated";
    }


    // Print Library Member Information
    @GetMapping("/printTablesInfoMember")
    public String printTablesInfoMember() {
        DataBaseControl.printTablesInfoMember();
        return "Library member information printed";
    }


}
