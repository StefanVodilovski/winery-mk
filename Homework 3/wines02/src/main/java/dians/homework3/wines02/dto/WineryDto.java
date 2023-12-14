package dians.homework3.wines02.dto;


import dians.homework3.wines02.model.Event;
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
public class WineryDto {
    private Long Id;
    private String name;
    private String description;
    private String photoUrl;
    private List<Event> events = new ArrayList<>();
}
