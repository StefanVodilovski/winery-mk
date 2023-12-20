package dians.homework3.wines02.service;

import dians.homework3.wines02.dto.*;

import javax.transaction.Transactional;
import java.util.List;

public interface EventService {
    List<EventDto2> getAll();

    @Transactional
    EventDto findById(Long eventId);

    List<EventCommentDto> findCommentsById(Long eventId);

    EventDto findAll(Long eventId);
}
