package dians.homework3.wines02.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WineDto2 {
    private Long Id;
    private String name;
    private Integer price;
    private Integer stock;
    private double litrage;
    private String photoUrl;
}
