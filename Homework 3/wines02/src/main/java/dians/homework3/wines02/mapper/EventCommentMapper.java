package dians.homework3.wines02.mapper;

import dians.homework3.wines02.dto.EventCommentDto;
import dians.homework3.wines02.model.EventComment;

public class EventCommentMapper {
    public EventComment mapToEventComment(EventCommentDto eventCommentDto) {
        return EventComment.builder()
                .Id(eventCommentDto.getId())
                .content(eventCommentDto.getContent())
                .updatedOn(eventCommentDto.getUpdatedOn())
                .createdOn(eventCommentDto.getCreatedOn())
                .build();
    }

    public static EventCommentDto mapToEventCommentDto(EventComment eventComment) {
        return EventCommentDto.builder()
                .Id(eventComment.getId())
                .content(eventComment.getContent())
                .updatedOn(eventComment.getUpdatedOn())
                .createdBy(UserMapper.mapToUserDto(eventComment.getCreatedBy()))
                .createdOn(eventComment.getCreatedOn())
                .build();
    }
}
