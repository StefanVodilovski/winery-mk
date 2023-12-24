package dians.homework3.wines02.dto;

import dians.homework3.wines02.model.Region;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WineryDto2 {
    private Long Id;
    private String name;
    private String description;
    private String photoUrl;
    private double xCordinate;
    private double yCordinate;
    private Region region;
}
