package dians.homework3.wines02.filters.EventPipes;

import dians.homework3.wines02.dto.EventDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PipeEvents<T> {
    private List<Filter<T>> filters = new ArrayList<>();

    public void addFilter(Filter<T> filter){
        filters.add(filter);}

    public List<EventDto> runFilters(List<T> input, List<EventDto> events) {
        List<EventDto> eventDtos = events;
        int counter = -1;
        for (Filter<T> filter: filters) {
            counter += 1;
            eventDtos = filter.execute(input.get(counter),eventDtos);
        }
        return eventDtos;
    }
}
