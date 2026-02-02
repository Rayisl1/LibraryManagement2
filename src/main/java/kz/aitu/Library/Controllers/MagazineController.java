package kz.aitu.Library.Controllers;

import kz.aitu.Library.repository.MagazineRepository;
import kz.aitu.Library.entities.inventory.Magazine;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class MagazineController {

    @PostMapping("/addMagazines")
    public String addMagazines(@RequestBody List<Magazine> magazines) {
        for (Magazine mag : magazines) {
            MagazineRepository.addMagazine(mag.getId(), mag.getTitle(), mag.getYear(), mag.getIssue());
        }
        return "Журналы добавлены!";
    }

    @DeleteMapping("/deleteMagazine")
    public String deleteMagazine(@RequestBody Magazine mag) {
        boolean deleted = MagazineRepository.deleteMagazine(mag.getId());
        return deleted ? "Журнал удален" : "ID не найден";
    }

    @PutMapping("/updateMagazine")
    public String updateMagazine(@RequestBody Magazine mag) {
        boolean updated = MagazineRepository.updateMagazineTitle(mag.getId(), mag.getTitle());
        return updated ? "Название обновлено" : "Ошибка обновления";
    }

    @GetMapping("/getMagazines")
    public List<Magazine> getMagazines() {
        return MagazineRepository.getAllMagazines();
    }

    @GetMapping("/getMagazine/{id}")
    public Magazine getMagazineById(@PathVariable String id) {
        return MagazineRepository.findMagazineById(id);
    }
}