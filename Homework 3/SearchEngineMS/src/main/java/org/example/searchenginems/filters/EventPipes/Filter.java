package org.example.searchenginems.filters.EventPipes;


import org.example.searchenginems.model.Event;

import java.util.List;

public interface Filter<T> {
    List<Event> execute(T input, List<Event> events);
}
