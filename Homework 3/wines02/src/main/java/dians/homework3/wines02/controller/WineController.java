package dians.homework3.wines02.controller;

import dians.homework3.wines02.dto.AddWinesDto;
import dians.homework3.wines02.dto.CartDto;
import dians.homework3.wines02.dto.WineDto;
import dians.homework3.wines02.model.AddWines;
import dians.homework3.wines02.model.UserEntity;
import dians.homework3.wines02.model.Wine;
import dians.homework3.wines02.service.AddWinesService;
import dians.homework3.wines02.service.PipeWinesService;
import dians.homework3.wines02.service.UserService;
import dians.homework3.wines02.service.WineService;
import org.hibernate.Hibernate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static dians.homework3.wines02.mapper.WineMapper.mapToWine;

@CrossOrigin("http://localhost:3001")
@RestController
@RequestMapping("/wines/")
public class WineController {
    private final WineService wineService;
    private final AddWinesService addWinesService;
    private final UserService userService;
    private final PipeWinesService pipeWinesService;

    public WineController(WineService wineService, AddWinesService addWinesService, UserService userService, PipeWinesService pipeWinesService) {
        this.wineService = wineService;
        this.addWinesService = addWinesService;
        this.userService = userService;
        this.pipeWinesService = pipeWinesService;
    }

    @GetMapping("all")
    public List<WineDto> getAllWines() {
        return this.wineService.getAll();
    }

    @GetMapping("filter")
    public List<WineDto> filterWines(@RequestParam(required = false) String searchQuery,
                                     @RequestParam(required = false) String priceFilter,
                                     @RequestParam(required = false) String region,
                                     @RequestParam(required = false) String winery,
                                     @RequestParam(required = false) String litrage) {
        return pipeWinesService.filter(searchQuery, priceFilter, region, winery, litrage);
    }

    @GetMapping("{wineId}")
    public WineDto getWineById(@PathVariable("wineId") Long wineId) {
        return wineService.findById(wineId);
    }

    @PostMapping("add/cart/item/{wineId}/{userId}")
    public ResponseEntity<String> putToCart(@PathVariable("wineId") Long wineId,
                            @PathVariable("userId") Long userId,
                            @RequestParam("quantity") String quantity) {
        addWinesService.createAddWine(mapToWine(wineService.findById(wineId)),quantity,userService.findById(userId).getCart());
        return ResponseEntity.ok("Added to cart");
    }
}