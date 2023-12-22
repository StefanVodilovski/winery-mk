package dians.homework3.wines02.controller;


import dians.homework3.wines02.dto.AddWinesDto;
import dians.homework3.wines02.dto.UserDto;
import dians.homework3.wines02.dto.WineDto;
import dians.homework3.wines02.model.AddWines;
import dians.homework3.wines02.model.UserEntity;
import dians.homework3.wines02.security.SecurityUtil;
import dians.homework3.wines02.security.UserAuthProvider;
import dians.homework3.wines02.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static dians.homework3.wines02.mapper.UserMapper.mapToUserDto;

@CrossOrigin("http://localhost:3001")
@RestController
@RequestMapping("/wines/")
public class WineController {
    private final WineService wineService;
    private final UserService userService;
    private final PipeWinesService pipeWinesService;
    private final CartService cartService;
    private final UserAuthProvider authProvider;

    public WineController(WineService wineService, UserService userService, PipeWinesService pipeWinesService, CartService cartService, UserAuthProvider authProvider) {
        this.wineService = wineService;
        this.userService = userService;
        this.pipeWinesService = pipeWinesService;
        this.cartService = cartService;
        this.authProvider = authProvider;
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
    public ResponseEntity<String> putToCart(@PathVariable("wineId") Long wineId,
                                                 @RequestParam("quantity") String quantity,
                                            @RequestHeader(value = "Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        Authentication authentication = authProvider.validateToken(token);
        if(authentication.isAuthenticated()) {
            String username = (String) authentication.getPrincipal();
            UserEntity user = userService.findByUsername(username);

            if (user != null) {
                WineDto wine = wineService.findById(wineId);
                cartService.saveCart(user, wine, Integer.parseInt(quantity));
                return ResponseEntity.ok("Successfully added to cart.");
            } else {
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }
}