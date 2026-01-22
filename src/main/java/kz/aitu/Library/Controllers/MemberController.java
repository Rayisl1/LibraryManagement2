package kz.aitu.Library.Controllers;

import kz.aitu.Library.entities.DataBaseControl;
import kz.aitu.Library.entities.LibraryMember;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class MemberController {

    // 1. Получить всех
    @GetMapping("/getMembers")
    public List<LibraryMember> getMembers() {
        return DataBaseControl.getAllMembers();
    }

    // 2. Добавить список (Массив из Postman)
    @PostMapping("/addLibraryMembers")
    public String addMembers(@RequestBody List<LibraryMember> members) {
        int count = 0;
        for (LibraryMember m : members) {
            if (DataBaseControl.addLibraryMember(m.getId(), m.getName())) count++;
        }
        return "Успешно добавлено участников: " + count;
    }

    // 3. Удалить по ID
    @DeleteMapping("/deleteLibraryMember")
    public String deleteMember(@RequestBody LibraryMember member) {
        boolean deleted = DataBaseControl.deleteLibraryMember(member.getId());
        return deleted ? "Удален успешно" : "ID не найден";
    }

    // 4. Обновить имя по ID
    @PutMapping("/updateLibraryMember")
    public String updateMember(@RequestBody LibraryMember member) {
        boolean updated = DataBaseControl.updateLibraryMemberId(member.getName(), member.getId());
        return updated ? "Обновлено" : "Ошибка обновления";
    }
}