package dians.homework3.wines02.service;

import dians.homework3.wines02.dto.*;
import dians.homework3.wines02.model.Event;

import javax.transaction.Transactional;
import java.util.List;

public interface EventService {
    List<EventDto2> getAll();

    @Transactional
    EventDto findById(Long eventId);

    List<EventCommentDto> findCommentsById(Long eventId);

    EventDto findAll(Long eventId);

    void save(Event event);

    List<Event> findAllById(List<Long> eventsId);
}
