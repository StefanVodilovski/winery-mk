package dians.homework3.wines02.filters.EventPipes;

import dians.homework3.wines02.dto.EventDto;
import dians.homework3.wines02.dto.WineDto;

import java.util.List;

public interface Filter<T> {
    List<EventDto> execute(T input, List<EventDto> events);
}
