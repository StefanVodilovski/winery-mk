package org.example.searchenginems.filters.EventPipes;

import org.example.searchenginems.model.Event;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SearchFilterEvent implements Filter<String> {
    @Override
    public List<Event> execute(String searchInput, List<Event> events) {
        if(searchInput != null) {
            String finalSearchQuery = searchInput.toLowerCase();
            return events.stream().filter(event -> event.getName().contains(finalSearchQuery)).collect(Collectors.toList());
        }
        return events;
    }
}
