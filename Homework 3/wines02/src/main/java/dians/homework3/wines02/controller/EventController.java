package dians.homework3.wines02.controller;

import dians.homework3.wines02.dto.EventDto;
import dians.homework3.wines02.service.EventService;
import dians.homework3.wines02.service.PipeEventsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events/")
public class EventController {
    private final EventService eventService;
    private final PipeEventsService pipeEventsService;

    public EventController(EventService eventService, PipeEventsService pipeEventsService) {
        this.eventService = eventService;
        this.pipeEventsService = pipeEventsService;
    }

    @GetMapping("all")
    public List<EventDto> getAllWines() {
        return this.eventService.getAll();
    }

    @GetMapping("filter")
    public List<EventDto> filterWines(@RequestParam(required = false) String searchQuery,
                                     @RequestParam(required = false) String winery) {
        return pipeEventsService.filter(searchQuery, winery);
    }

    @GetMapping("{eventId}")
    public EventDto getWineById(@PathVariable("eventId") Long eventId) {
        return eventService.findById(eventId);
    }
}
