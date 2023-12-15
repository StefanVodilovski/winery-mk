package dians.homework3.wines02.mapper;

import dians.homework3.wines02.dto.EventDto;
import dians.homework3.wines02.model.Event;

public class EventMapper {
    public static Event mapToEvent(EventDto eventDto) {
        return Event.builder()
                .Id(eventDto.getId())
                .name(eventDto.getName())
                .description(eventDto.getDescription())
                .createdBy(eventDto.getCreatedBy())
                .createdOn(eventDto.getCreatedOn())
                .endDateTime(eventDto.getEndDateTime())
                .geolocation(eventDto.getGeolocation())
                .photoUrl(eventDto.getPhotoUrl())
                .startDateTime(eventDto.getStartDateTime())
                .updatedOn(eventDto.getUpdatedOn())
                .wineries(eventDto.getWineries())
                .comments(eventDto.getComments())
                .build();
    }

    public static EventDto mapToEventDto(Event event) {
        return EventDto.builder()
                .Id(event.getId())
                .name(event.getName())
                .description(event.getDescription())
                .createdBy(event.getCreatedBy())
                .createdOn(event.getCreatedOn())
                .endDateTime(event.getEndDateTime())
                .geolocation(event.getGeolocation())
                .photoUrl(event.getPhotoUrl())
                .startDateTime(event.getStartDateTime())
                .updatedOn(event.getUpdatedOn())
                .wineries(event.getWineries())
                .comments(event.getComments())
                .build();
    }
}
