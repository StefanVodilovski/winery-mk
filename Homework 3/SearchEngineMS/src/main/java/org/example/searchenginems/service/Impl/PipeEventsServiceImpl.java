package org.example.searchenginems.service.Impl;

import org.example.searchenginems.filters.EventPipes.PipeEvents;
import org.example.searchenginems.filters.EventPipes.SearchFilterEvent;
import org.example.searchenginems.filters.EventPipes.WineryFilterEvent;
import org.example.searchenginems.model.Event;
import org.example.searchenginems.repository.EventRepository;
import org.example.searchenginems.service.PipeEventsService;
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
    public List<Event> filter(String search, String winery) {
        List<String> stringList = new ArrayList<>();
        stringList.add(search);
        stringList.add(winery);
        List<Event> events = eventRepository.findAll().stream().collect(Collectors.toList());
        return new ArrayList<>(pipeEvents.runFilters(stringList, events));
    }
}
