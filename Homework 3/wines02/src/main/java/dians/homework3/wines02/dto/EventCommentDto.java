package dians.homework3.wines02.dto;

import dians.homework3.wines02.model.Event;
import dians.homework3.wines02.model.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventCommentDto {
    private Long Id;
    private String content;
    private UserDto createdBy;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
