package dians.homework3.wines02.controller;

import dians.homework3.wines02.model.Wine;
import dians.homework3.wines02.service.WineService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GeneralController {
    private final WineService wineService;

    public GeneralController(WineService wineService) {
        this.wineService = wineService;
    }

    @GetMapping("/")
    public String getHomePage() {
        return "home";
    }

    @GetMapping("/wines")
    public String getWinePage(Model model) {
        List<Wine> wines = wineService.getAll();
        model.addAttribute("wines",wines);
        return "listWines";
    }

    @GetMapping("/wineries")
    public String getWineriesPage() {
        return "listWineries";
    }
    @GetMapping("/events")
    public String getEventsPage() {
        return "listEvents";
    }
    @GetMapping("/map")
    public String getMapPage() {
        return "map";
    }
    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }
    @GetMapping("/register")
    public String getRegisterPage() {
        return "register";
    }

}
