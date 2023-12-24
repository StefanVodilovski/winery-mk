package dians.homework3.wines02.controller;


import dians.homework3.wines02.dto.WineDto;
import dians.homework3.wines02.model.AddWines;
import dians.homework3.wines02.model.Cart;
import dians.homework3.wines02.model.UserEntity;
import dians.homework3.wines02.security.SecurityUtil;
import dians.homework3.wines02.service.*;
import org.springframework.http.HttpStatus;
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
    private final CartService cartService;

    public WineController(WineService wineService, AddWinesService addWinesService, UserService userService, PipeWinesService pipeWinesService, CartService cartService) {
        this.wineService = wineService;
        this.addWinesService = addWinesService;
        this.userService = userService;
        this.pipeWinesService = pipeWinesService;
        this.cartService = cartService;
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

    @GetMapping("add/cart/item/{wineId}")
    public ResponseEntity<AddWines> putToCart(@PathVariable("wineId") Long wineId,
                                              @RequestParam("quantity") String quantity) {
        UserEntity user = userService.findByEmail(SecurityUtil.getSessionUser());
        WineDto wine = wineService.findById(wineId);
        cartService.saveCart(user, wine, Integer.parseInt(quantity));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}