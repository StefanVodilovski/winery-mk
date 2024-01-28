package org.example.searchenginems.service;


import org.example.searchenginems.model.Event;

import java.util.List;

public interface PipeEventsService {
    List<Event> filter(String search, String winery);
}
