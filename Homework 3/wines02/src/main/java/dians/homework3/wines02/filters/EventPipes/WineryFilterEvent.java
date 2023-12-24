package dians.homework3.wines02.filters.EventPipes;

import dians.homework3.wines02.dto.EventDto;
import dians.homework3.wines02.model.Event;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WineryFilterEvent implements Filter<String> {
    @Override
    public List<Event> execute(String wineryId, List<Event> events) {
        if(wineryId != null) {
            return events.stream()
                    .filter(event -> event.getWineries().stream()
                            .anyMatch(eventWine -> eventWine.getId().equals(Long.parseLong(wineryId))))
                    .toList();
        }
            return events;
    }
}
