package dians.homework3.wines02.dto;

import dians.homework3.wines02.model.AddWines;
import dians.homework3.wines02.model.UserEntity;
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
public class CartDto {
    private Long Id;
    private Integer totalAmount;
    private UserEntity createdBy = null;
    private List<AddWines> cartWines = new ArrayList<>();
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
