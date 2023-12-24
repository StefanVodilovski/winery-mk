package dians.homework3.wines02.filters.EventPipes;

import dians.homework3.wines02.dto.EventDto;
import dians.homework3.wines02.model.Event;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PipeEvents<T> {
    private List<Filter<T>> filters = new ArrayList<>();

    public void addFilter(Filter<T> filter){
        filters.add(filter);}

    public List<Event> runFilters(List<T> input, List<Event> events) {
        List<Event> event = events;
        int counter = -1;
        for (Filter<T> filter: filters) {
            counter += 1;
            event = filter.execute(input.get(counter),event);
        }
        return event;
    }
}
