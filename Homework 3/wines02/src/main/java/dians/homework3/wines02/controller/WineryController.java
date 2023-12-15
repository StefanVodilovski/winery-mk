package dians.homework3.wines02.controller;

import dians.homework3.wines02.dto.WineryDto;
import dians.homework3.wines02.service.PipeWineriesService;
import dians.homework3.wines02.service.WineryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wineries/")
public class WineryController {
    private final WineryService wineryService;
    private final PipeWineriesService pipeWineriesService;

    public WineryController(WineryService wineryService, PipeWineriesService pipeWineriesService) {
        this.wineryService = wineryService;
        this.pipeWineriesService = pipeWineriesService;
    }

    @GetMapping("all")
    public List<WineryDto> getAllWines() {
        return this.wineryService.getAll();
    }

    @GetMapping("filter")
    public List<WineryDto> filterWines(@RequestParam(required = false) String searchQuery,
                                     @RequestParam(required = false) String region) {
        return pipeWineriesService.filter(searchQuery, region);
    }

    @GetMapping("find/{wineryId}")
    public WineryDto getWineById(@PathVariable("wineryId") Long wineryId) {
        return wineryService.findById(wineryId);
    }
}
