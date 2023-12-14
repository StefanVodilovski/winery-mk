package dians.homework3.wines02.dto;

import dians.homework3.wines02.model.AddWines;
import dians.homework3.wines02.model.Status;
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
public class OrderDto {
    private Long Id;
    private String code;
    private Integer price;
    private Status status;
    private UserEntity createdBy;
    private List<AddWines> orderWines = new ArrayList<>();
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
