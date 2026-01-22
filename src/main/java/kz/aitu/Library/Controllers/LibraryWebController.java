package kz.aitu.Library.Controllers;

import kz.aitu.Library.entities.DataBaseControl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LibraryWebController {

    @GetMapping("/")
    public String home() {
        return "index"; // Главная страница теперь по адресу http://localhost:8080/
    }

    @GetMapping("/library")
    public String showLibrary(@RequestParam(value = "view", defaultValue = "home") String view, Model model) {
        // Передаем параметр 'view', чтобы HTML знал, какую таблицу показать
        model.addAttribute("view", view);

        // Загружаем данные только если они нужны (оптимизация)
        if (view.equals("books")) model.addAttribute("books", DataBaseControl.getAllBooks());
        if (view.equals("members")) model.addAttribute("members", DataBaseControl.getAllMembers());
        if (view.equals("magazines")) model.addAttribute("magazines", DataBaseControl.getAllMagazines());

        return "index";
    }
}