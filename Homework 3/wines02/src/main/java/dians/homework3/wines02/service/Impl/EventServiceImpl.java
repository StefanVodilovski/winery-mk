package dians.homework3.wines02.service.Impl;

import dians.homework3.wines02.dto.EventCommentDto;
import dians.homework3.wines02.dto.EventDto;
import dians.homework3.wines02.dto.EventDto2;
import dians.homework3.wines02.mapper.EventCommentMapper;
import dians.homework3.wines02.mapper.EventMapper;
import dians.homework3.wines02.model.Event;
import dians.homework3.wines02.repository.EventRepository;
import dians.homework3.wines02.service.EventService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static dians.homework3.wines02.mapper.EventMapper.mapToEventDto;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<EventDto2> getAll() {
        return eventRepository.findAll().stream().map(EventMapper::mapToEventDto2).collect(Collectors.toList());
    }

    @Override
    public EventDto findById(Long eventId) {
        Optional<Event> event = eventRepository.findById(eventId);
        return event.map(EventMapper::mapToEventDto).orElse(null);
    }

    @Override
    @Transactional
    public List<EventCommentDto> findCommentsById(Long eventId) {
        Optional<Event> event = eventRepository.findById(eventId);
        return event.map(value -> value.getComments().stream().map(EventCommentMapper::mapToEventCommentDto).collect(Collectors.toList())).orElseGet(ArrayList::new);
    }

    @Override
    public EventDto findAll(Long eventId) {
        Optional<Event> eventWithWineries = eventRepository.findByIdWithWineries(eventId);
        Optional<Event> eventWithComments = eventRepository.findByIdWithComments(eventId);

        if(eventWithWineries.isPresent() && eventWithComments.isPresent()) {
            Event mergedEvent = eventWithWineries.get();
            mergedEvent.setComments(eventWithComments.get().getComments());
            return mapToEventDto(mergedEvent);
        }
        return new EventDto();
    }
}
