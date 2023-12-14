package dians.homework3.wines02.dto;

import dians.homework3.wines02.model.Region;
import dians.homework3.wines02.model.Winery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WineDto {
    private Long Id;
    private String name;
    private Integer price;
    private Integer stock;
    private double litrage;
    private String photoUrl;
    private Winery winery;
    private Region region;
}
