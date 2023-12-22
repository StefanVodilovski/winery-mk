package dians.homework3.wines02.filters.EventPipes;

import dians.homework3.wines02.dto.EventDto;
import dians.homework3.wines02.model.Event;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WineryFilterEvent implements Filter<String> {
    @Override
    public List<Event> execute(String winery, List<Event> events) {
        if(winery != null) {
            return events.stream()
                    .filter(event -> event.getWineries().stream()
                            .anyMatch(eventWine -> eventWine.getName().equals(winery)))
                    .toList();
        }
            return events;
    }
}
