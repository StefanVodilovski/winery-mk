package dians.homework3.wines02.controller;

import dians.homework3.wines02.dto.EventCommentDto;
import dians.homework3.wines02.dto.EventDto;
import dians.homework3.wines02.dto.EventDto2;
import dians.homework3.wines02.dto.WineryDto;
import dians.homework3.wines02.mapper.EventMapper;
import dians.homework3.wines02.model.AddWines;
import dians.homework3.wines02.model.Cart;
import dians.homework3.wines02.model.Event;
import dians.homework3.wines02.model.UserEntity;
import dians.homework3.wines02.security.SecurityUtil;
import dians.homework3.wines02.service.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static dians.homework3.wines02.mapper.AddWinesMapper.mapToAddWines;

@CrossOrigin("http://localhost:3001")
@RestController
@RequestMapping("/events/")
public class EventController {
    private final EventService eventService;
    private final PipeEventsService pipeEventsService;
    private final UserService userService;

    public EventController(EventService eventService, PipeEventsService pipeEventsService, UserService userService) {
        this.eventService = eventService;
        this.pipeEventsService = pipeEventsService;
        this.userService = userService;
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

    @PostMapping("create")
    public EventDto createEvent(@RequestParam(required = false) String name,
                                @RequestParam(required = false) LocalDateTime dateStart,
                                @RequestParam(required = false) LocalDateTime dateEnd,
                                @RequestParam(required = false) String description,
                                @RequestParam(required = false) MultipartFile eventImage,
                                @RequestParam(required = false) Double lat,
                                @RequestParam(required = false) Double lng) {
//        byte[] imageBytes = null;
//        if (eventImage != null && !eventImage.isEmpty()) {
//            try {
//                imageBytes = eventImage.getBytes();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        Event event = new Event();
        event.setName(name);
        event.setStartDateTime(dateStart);
        event.setEndDateTime(dateEnd);
        event.setDescription(description);
        event.setCreatedBy(userService.findByUsername(SecurityUtil.getSessionUser()));
//        event.setPhoto(imageBytes);
        event.setXCordinate(lat != null ? lat : 0.0);
        event.setYCordinate(lng != null ? lng : 0.0);
        eventService.save(event);
        return EventMapper.mapToEventDto(event);
    }


}
