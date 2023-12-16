package dians.homework3.wines02.dto;


import dians.homework3.wines02.model.Event;
import dians.homework3.wines02.model.Region;
import dians.homework3.wines02.model.Wine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private double xCordinate;
    private double yCordinate;
    private Region region;
    private List<Event> events = new ArrayList<>();
}
