package kz.aitu.Library.Controllers;

import kz.aitu.Library.repository.BookRepository;
import kz.aitu.Library.repository.MagazineRepository;
import kz.aitu.Library.repository.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LibraryWebController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/library")
    public String showLibrary(@RequestParam(value = "view", defaultValue = "home") String view, Model model) {
        model.addAttribute("view", view);

        if (view.equals("books")) model.addAttribute("books", BookRepository.getAllBooks());
        if (view.equals("members")) model.addAttribute("members", MemberRepository.getAllMembers());
        if (view.equals("magazines")) model.addAttribute("magazines", MagazineRepository.getAllMagazines());

        return "index";
    }
}