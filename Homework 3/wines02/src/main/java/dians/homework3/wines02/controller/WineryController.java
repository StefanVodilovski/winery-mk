package dians.homework3.wines02.controller;

import dians.homework3.wines02.dto.WineryDto;
import dians.homework3.wines02.service.WineryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wineries/")
public class WineryController {
    private final WineryService wineryService;

    public WineryController(WineryService wineryService) {
        this.wineryService = wineryService;
    }

    @GetMapping("all")
    public List<WineryDto> getAllWineries() {
        return wineryService.getAll();
    }
}
