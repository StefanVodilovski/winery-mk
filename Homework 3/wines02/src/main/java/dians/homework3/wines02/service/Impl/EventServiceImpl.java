package dians.homework3.wines02.service.Impl;

import dians.homework3.wines02.dto.EventDto;
import dians.homework3.wines02.dto.WineDto;
import dians.homework3.wines02.mapper.EventMapper;
import dians.homework3.wines02.model.Event;
import dians.homework3.wines02.model.Wine;
import dians.homework3.wines02.repository.EventRepository;
import dians.homework3.wines02.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static dians.homework3.wines02.mapper.EventMapper.mapToEventDto;
import static dians.homework3.wines02.mapper.WineMapper.mapToWineDto;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<EventDto> getAll() {
        return eventRepository.findAll().stream().map(EventMapper::mapToEventDto).collect(Collectors.toList());
    }

    @Override
    public EventDto findById(Long eventId) {
        Optional<Event> event = eventRepository.findById(eventId);
        if (event.isPresent()) {
            return mapToEventDto(event.get());
        }
        return new EventDto();
    }
}
