package dians.homework3.wines02.dto;

import dians.homework3.wines02.model.UserEntity;
import dians.homework3.wines02.model.Winery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {
    private Long Id;
    private String name;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String photoUrl;
    private String description;
    private String geolocation;
    private List<Winery> wineries = new ArrayList<>();
    private UserEntity createdBy;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
