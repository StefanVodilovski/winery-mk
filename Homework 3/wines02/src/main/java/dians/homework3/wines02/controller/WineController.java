package dians.homework3.wines02.controller;

import dians.homework3.wines02.dto.WineDto;
import dians.homework3.wines02.filters.*;
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
    @GetMapping("filter")
    public List<WineDto> filterWines(@RequestParam(name="price", required = false) String priceValue,
                                     @RequestParam(name="region", required = false) String regionValue,
                                     @RequestParam(name="winery", required = false) String wineryValue,
                                     @RequestParam(name="litrage", required = false) String litrageValue) {
        return pipeWinesService.filter(priceValue, regionValue, wineryValue, litrageValue);
    }

}