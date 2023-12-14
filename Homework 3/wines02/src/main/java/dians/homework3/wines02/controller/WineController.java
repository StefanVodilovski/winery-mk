package dians.homework3.wines02.controller;

import dians.homework3.wines02.model.Wine;
import dians.homework3.wines02.service.WineService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wines/")
public class WineController {
    private final WineService wineService;

    public WineController(WineService wineService) {
        this.wineService = wineService;
    }

    @GetMapping("all")
    public List<Wine> getAllWines() {
        return wineService.getAll();
    }
}
