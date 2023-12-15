package dians.homework3.wines02.mapper;

import dians.homework3.wines02.dto.EventCommentDto;
import dians.homework3.wines02.model.EventComment;

public class EventCommentMapper {
    public EventComment mapToEventComment(EventCommentDto eventCommentDto) {
        return EventComment.builder()
                .Id(eventCommentDto.getId())
                .content(eventCommentDto.getContent())
                .updatedOn(eventCommentDto.getUpdatedOn())
                .createdBy(eventCommentDto.getCreatedBy())
                .createdOn(eventCommentDto.getCreatedOn())
                .event(eventCommentDto.getEvent())
                .build();
    }

    public EventCommentDto mapToEventCommentDto(EventComment eventComment) {
        return EventCommentDto.builder()
                .Id(eventComment.getId())
                .content(eventComment.getContent())
                .updatedOn(eventComment.getUpdatedOn())
                .createdBy(eventComment.getCreatedBy())
                .createdOn(eventComment.getCreatedOn())
                .event(eventComment.getEvent())
                .build();
    }
}
