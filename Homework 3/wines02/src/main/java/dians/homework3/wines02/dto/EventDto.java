package dians.homework3.wines02.dto;

import dians.homework3.wines02.model.EventComment;
import dians.homework3.wines02.model.UserEntity;
import dians.homework3.wines02.model.Winery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Transactional
public class EventDto {
    private Long Id;
    private String name;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
//    private byte[] photo;
    private String description;
    private double xCordinate;
    private double yCordinate;
    private List<WineryDto2> wineries;
    private UserDto createdBy;
    private List<EventCommentDto> comments;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
