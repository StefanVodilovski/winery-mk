package org.example.searchenginems.controller;


import org.example.searchenginems.model.Event;
import org.example.searchenginems.model.Wine;
import org.example.searchenginems.model.Winery;
import org.example.searchenginems.service.PipeEventsService;
import org.example.searchenginems.service.PipeWineriesService;
import org.example.searchenginems.service.PipeWinesService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class SearchController {
    private final PipeEventsService pipeEventsService;
    private final PipeWinesService pipeWinesService;
    private final PipeWineriesService pipeWineriesService;

    public SearchController(PipeEventsService pipeEventsService, PipeWinesService pipeWinesService, PipeWineriesService pipeWineriesService) {
        this.pipeEventsService = pipeEventsService;
        this.pipeWinesService = pipeWinesService;
        this.pipeWineriesService = pipeWineriesService;
    }

    @GetMapping(value = "filter/wines", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Long> filterWines(@RequestParam(required = false) String searchQuery,
                                  @RequestParam(required = false) String priceFilter,
                                  @RequestParam(required = false) String region,
                                  @RequestParam(required = false) String winery,
                                  @RequestParam(required = false) String litrage) {
        return pipeWinesService.filter(searchQuery, priceFilter, region, winery, litrage);
    }

    @GetMapping(value = "filter/wineries", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Long> filterWineries(@RequestParam(required = false) String searchQuery,
                                       @RequestParam(required = false) String region) {
        return pipeWineriesService.filter(searchQuery, region);
    }

    @GetMapping(value = "filter/events", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Long> filterEvents(@RequestParam(required = false) String searchQuery,
                                    @RequestParam(required = false) String winery) {
        return pipeEventsService.filter(searchQuery, winery);
    }
}
