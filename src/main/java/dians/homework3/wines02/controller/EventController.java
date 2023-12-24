package dians.homework3.wines02.controller;

import dians.homework3.wines02.dto.EventCommentDto;
import dians.homework3.wines02.dto.EventDto;
import dians.homework3.wines02.dto.EventDto2;
import dians.homework3.wines02.dto.WineryDto;
import dians.homework3.wines02.service.EventService;
import dians.homework3.wines02.service.PipeEventsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3001")
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
    public List<EventDto2> getAllWines() {
        return eventService.getAll();
    }

    @GetMapping("filter")
    public List<EventDto> filterWines(@RequestParam(required = false) String searchQuery,
                                       @RequestParam(required = false) String winery) {
        return pipeEventsService.filter(searchQuery, winery);
    }

    @GetMapping("{eventId}")
    public EventDto getEventById(@PathVariable("eventId") Long eventId) {
        return eventService.findAll(eventId);
    }

    @GetMapping("{eventId}/comments")
    public List<EventCommentDto> getEventCommentsById(@PathVariable("eventId") Long eventId) {
        return eventService.findCommentsById(eventId);
    }
}
