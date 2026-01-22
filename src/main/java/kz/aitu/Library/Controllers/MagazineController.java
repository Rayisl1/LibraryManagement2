package kz.aitu.Library.Controllers;

import kz.aitu.Library.entities.DataBaseControl;
import kz.aitu.Library.entities.Magazine;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class MagazineController {

    // 1. Добавить список журналов
    @PostMapping("/addMagazines")
    public String addMagazines(@RequestBody List<Magazine> magazines) {
        for (Magazine mag : magazines) {
            DataBaseControl.addMagazine(mag.getId(), mag.getTitle(), mag.getYear(), mag.getIssue());
        }
        return "Журналы добавлены!";
    }

    // 2. Удалить журнал по ID
    @DeleteMapping("/deleteMagazine")
    public String deleteMagazine(@RequestBody Magazine mag) {
        boolean deleted = DataBaseControl.deleteMagazine(mag.getId());
        return deleted ? "Журнал удален" : "ID не найден";
    }

    // 3. Обновить название журнала
    @PutMapping("/updateMagazine")
    public String updateMagazine(@RequestBody Magazine mag) {
        boolean updated = DataBaseControl.updateMagazineTitle(mag.getId(), mag.getTitle());
        return updated ? "Название обновлено" : "Ошибка обновления";
    }

    @GetMapping("/getMagazines")
    public List<Magazine> getMagazines() {
        return DataBaseControl.getAllMagazines();
    }
}