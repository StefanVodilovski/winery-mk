package dians.homework3.wines02.service.Impl;

import dians.homework3.wines02.repository.EventRepository;
import dians.homework3.wines02.service.EventService;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {
    EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
}
