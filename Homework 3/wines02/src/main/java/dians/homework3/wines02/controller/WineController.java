package dians.homework3.wines02.controller;

import dians.homework3.wines02.dto.WineDto;
import dians.homework3.wines02.filters.*;
import dians.homework3.wines02.model.Wine;
import dians.homework3.wines02.service.PipeWinesService;
import dians.homework3.wines02.service.WineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wines/")
public class WineController {
    private final WineService wineService;
    private final PipeWinesService pipeWinesService;

    public WineController(WineService wineService, PipeWinesService pipeWinesService) {
        this.wineService = wineService;
        this.pipeWinesService = pipeWinesService;
    }

    @GetMapping("all")
    public List<WineDto> getAllWines() {
        return this.wineService.getAll();
    }

//    //Se filtrira do max cena
    @PostMapping("filter")
    public List<WineDto> filterWines(@RequestParam String searchQuery,
                                     @RequestParam String priceValue,
                                     @RequestParam String regionValue,
                                     @RequestParam String wineryValue,
                                     @RequestParam String litrageValue) {
        return pipeWinesService.filter(searchQuery, priceValue, regionValue, wineryValue, litrageValue);
    }

}