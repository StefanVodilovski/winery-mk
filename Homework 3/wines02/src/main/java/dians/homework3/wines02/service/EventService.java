package dians.homework3.wines02.service;

import dians.homework3.wines02.dto.EventDto;
import dians.homework3.wines02.dto.WineDto;

import java.util.List;

public interface EventService {
    List<EventDto> getAll();

    EventDto findById(Long eventId);
}
