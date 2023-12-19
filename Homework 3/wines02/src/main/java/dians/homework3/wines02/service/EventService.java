package dians.homework3.wines02.service;

import dians.homework3.wines02.dto.EventCommentDto;
import dians.homework3.wines02.dto.EventDto;
import dians.homework3.wines02.dto.WineDto;
import dians.homework3.wines02.dto.WineryDto;

import javax.transaction.Transactional;
import java.util.List;

public interface EventService {
    List<EventDto> getAll();

    @Transactional
    EventDto findById(Long eventId);

    List<EventCommentDto> findCommentsById(Long eventId);

    EventDto findAll(Long eventId);
}
