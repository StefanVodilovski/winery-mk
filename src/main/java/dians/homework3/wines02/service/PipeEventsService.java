package dians.homework3.wines02.service;

import dians.homework3.wines02.dto.EventDto;
import dians.homework3.wines02.dto.WineryDto;

import java.util.List;

public interface PipeEventsService {
    List<EventDto> filter(String search, String winery);
}
