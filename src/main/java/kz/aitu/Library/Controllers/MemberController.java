package kz.aitu.Library.Controllers;

import kz.aitu.Library.repository.MemberRepository;
import kz.aitu.Library.entities.librarymembers.LibraryMember;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class MemberController {

    @GetMapping("/getMembers")
    public List<LibraryMember> getMembers() {
        return MemberRepository.getAllMembers();
    }

    @PostMapping("/addLibraryMembers")
    public String addMembers(@RequestBody List<LibraryMember> members) {
        int count = 0;
        for (LibraryMember m : members) {
            if (MemberRepository.addLibraryMember(m.getId(), m.getName())) count++;
        }
        return "Успешно добавлено участников: " + count;
    }

    @DeleteMapping("/deleteLibraryMember")
    public String deleteMember(@RequestBody LibraryMember member) {
        boolean deleted = MemberRepository.deleteLibraryMember(member.getId());
        return deleted ? "Удален успешно" : "ID не найден";
    }

    @PutMapping("/updateLibraryMember")
    public String updateMember(@RequestBody LibraryMember member) {
        boolean updated = MemberRepository.updateLibraryMemberId(member.getName(), member.getId());
        return updated ? "Обновлено" : "Ошибка обновления";
    }

    @GetMapping("/getMember/{id}")
    public LibraryMember getMemberById(@PathVariable int id) {
        return MemberRepository.findMemberById(id);
    }
}