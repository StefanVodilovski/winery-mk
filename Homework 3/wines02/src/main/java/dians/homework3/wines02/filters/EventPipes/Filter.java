package dians.homework3.wines02.filters.EventPipes;

import dians.homework3.wines02.dto.EventDto;
import dians.homework3.wines02.dto.WineDto;
import dians.homework3.wines02.model.Event;

import java.util.List;

public interface Filter<T> {
    List<Event> execute(T input, List<Event> events);
}
