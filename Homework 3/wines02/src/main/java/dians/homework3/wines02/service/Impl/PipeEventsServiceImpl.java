package dians.homework3.wines02.service.Impl;

import dians.homework3.wines02.dto.EventDto;
import dians.homework3.wines02.filters.EventPipes.PipeEvents;
import dians.homework3.wines02.filters.EventPipes.SearchFilterEvent;
import dians.homework3.wines02.filters.EventPipes.WineryFilterEvent;
import dians.homework3.wines02.mapper.EventMapper;
import dians.homework3.wines02.model.Event;
import dians.homework3.wines02.repository.EventRepository;
import dians.homework3.wines02.service.PipeEventsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PipeEventsServiceImpl implements PipeEventsService {
    private final EventRepository eventRepository;
    private final PipeEvents<String> pipeEvents;
    private final SearchFilterEvent searchFilterEvent;
    private final WineryFilterEvent wineryRepository;

    public PipeEventsServiceImpl(EventRepository eventRepository) {
        this.pipeEvents = new PipeEvents<>();
        this.searchFilterEvent = new SearchFilterEvent();
        this.wineryRepository = new WineryFilterEvent();
        pipeEvents.addFilter(searchFilterEvent);
        pipeEvents.addFilter(wineryRepository);
        this.eventRepository = eventRepository;
    }


    @Override
    public List<EventDto> filter(String search, String winery) {
        List<String> stringList = new ArrayList<>();
        stringList.add(search);
        stringList.add(winery);
        List<Event> events = eventRepository.findAll().stream().collect(Collectors.toList());
        return pipeEvents.runFilters(stringList, events).stream().map(EventMapper::mapToEventDto).collect(Collectors.toList());
    }
}
