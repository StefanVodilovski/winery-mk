package dians.homework3.wines02.controller;

import dians.homework3.wines02.dto.*;
import dians.homework3.wines02.filter_models.EventFilterRequest;
import dians.homework3.wines02.filter_models.WineryFilterRequest;
import dians.homework3.wines02.mapper.EventMapper;
import dians.homework3.wines02.mapper.WineryMapper;
import dians.homework3.wines02.model.*;
import dians.homework3.wines02.security.SecurityUtil;
import dians.homework3.wines02.security.UserAuthProvider;
import dians.homework3.wines02.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static dians.homework3.wines02.mapper.AddWinesMapper.mapToAddWines;
import static dians.homework3.wines02.mapper.UserMapper.mapToUserDto;

@CrossOrigin("http://localhost:3001")
@RestController
@RequestMapping("/events/")
public class EventController {
    private final EventService eventService;
    private final PipeEventsService pipeEventsService;
    private final UserService userService;
    private final UserAuthProvider authProvider;

    public EventController(EventService eventService, PipeEventsService pipeEventsService, UserService userService, UserAuthProvider authProvider) {
        this.eventService = eventService;
        this.pipeEventsService = pipeEventsService;
        this.userService = userService;
        this.authProvider = authProvider;
    }

    @GetMapping("all")
    public List<EventDto2> getAllWines() {
        return eventService.getAll();
    }

    @GetMapping("filter")
    public List<EventDto> filterWines(@RequestParam(required = false) String searchQuery,
                                       @RequestParam(required = false) String winery) {
        RestTemplate restTemplate = new RestTemplate();

        StringBuilder apiUrlBuilder = new StringBuilder("http://localhost:8081/api/filter/events?");

        if (searchQuery != null) {
            apiUrlBuilder.append("searchQuery=").append(searchQuery).append("&");
        }

        if (winery != null) {
            apiUrlBuilder.append("winery=").append(winery);
        }

        String apiUrl = apiUrlBuilder.toString();

        // Create a WineFilterRequest object to hold the parameters
        EventFilterRequest filterRequest = new EventFilterRequest(searchQuery, winery);

        // Make the HTTP request and retrieve the response
        ResponseEntity<Long[]> responseEntity = restTemplate.getForEntity(apiUrl, Long[].class, filterRequest);

        // Convert Long[] to List<Long>
        List<Long> eventIds = Arrays.asList(responseEntity.getBody());

        List<Event> events = eventService.findAllById(eventIds);
        // Extract the array of WineDto from the response
        List<EventDto> eventDtos = events.stream().map(EventMapper::mapToEventDto).collect(Collectors.toList());

        return eventDtos;
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
    public ResponseEntity<EventDto> createEvent(@RequestParam(required = false) String name,
                                @RequestParam(required = false) LocalDateTime dateStart,
                                @RequestParam(required = false) LocalDateTime dateEnd,
                                @RequestParam(required = false) String description,
                                @RequestParam(required = false) MultipartFile eventImage,
                                @RequestParam(required = false) Double lat,
                                @RequestParam(required = false) Double lng,
                                @RequestHeader(value = "Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        Authentication authentication = authProvider.validateToken(token);
        if(authentication != null && authentication.isAuthenticated()) {
            Event event = new Event();
            event.setName(name);
            event.setStartDateTime(dateStart);
            event.setEndDateTime(dateEnd);
            event.setDescription(description);
            UserDto userDto = (UserDto) authentication.getPrincipal();

            UserEntity user = userService.findByUsername(userDto.getUsername());

            if (user != null) {
                event.setCreatedBy(user);
                event.setXCordinate(lat != null ? lat : 0.0);
                event.setYCordinate(lng != null ? lng : 0.0);
                eventService.save(event);
                return ResponseEntity.ok(EventMapper.mapToEventDto(event));
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.notFound().build();
    }


}
