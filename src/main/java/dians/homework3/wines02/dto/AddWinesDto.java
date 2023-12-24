package dians.homework3.wines02.dto;

import dians.homework3.wines02.model.Cart;
import dians.homework3.wines02.model.Order;
import dians.homework3.wines02.model.Wine;
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
public class AddWinesDto {
    private Long Id;
    private Integer quantity;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
