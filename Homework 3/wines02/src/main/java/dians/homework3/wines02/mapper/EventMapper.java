package dians.homework3.wines02.mapper;

import dians.homework3.wines02.dto.EventDto;
import dians.homework3.wines02.dto.EventDto2;
import dians.homework3.wines02.model.Event;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

public class EventMapper {
    public static Event mapToEvent(EventDto eventDto) {
        return Event.builder()
                .Id(eventDto.getId())
                .name(eventDto.getName())
                .description(eventDto.getDescription())
                .createdOn(eventDto.getCreatedOn())
                .endDateTime(eventDto.getEndDateTime())
                .photo(eventDto.getPhoto())
                .startDateTime(eventDto.getStartDateTime())
                .updatedOn(eventDto.getUpdatedOn())
                .xCordinate(eventDto.getXCordinate())
                .yCordinate(eventDto.getYCordinate())
                .build();
    }

    public static EventDto mapToEventDto(Event event) {
        return EventDto.builder()
                .Id(event.getId())
                .name(event.getName())
                .description(event.getDescription())
                .createdBy(UserMapper.mapToUserDto(event.getCreatedBy()))
                .createdOn(event.getCreatedOn())
                .endDateTime(event.getEndDateTime())
                .photo(event.getPhoto())
                .startDateTime(event.getStartDateTime())
                .updatedOn(event.getUpdatedOn())
                .wineries(event.getWineries().stream().map(WineryMapper::mapToWineryDto2).collect(Collectors.toList()))
                .comments(event.getComments().stream().map(EventCommentMapper::mapToEventCommentDto).collect(Collectors.toList()))
                .xCordinate(event.getXCordinate())
                .yCordinate(event.getYCordinate())
                .build();
    }

    public static EventDto2 mapToEventDto2(Event event) {
        return EventDto2.builder()
                .name(event.getName())
                .photo(event.getPhoto())
                .build();
    }
}
