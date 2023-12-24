package dians.homework3.wines02.mapper;

import dians.homework3.wines02.dto.AddWinesDto;
import dians.homework3.wines02.model.AddWines;

public class AddWinesMapper {
    public static AddWines mapToAddWines(AddWinesDto addWinesDto) {
        return AddWines.builder()
                .Id(addWinesDto.getId())
                .quantity(addWinesDto.getQuantity())
                .createdOn(addWinesDto.getCreatedOn())
                .updatedOn(addWinesDto.getUpdatedOn())
                .build();
    }

    public static AddWinesDto mapToAddWinesDto(AddWines addWines) {
        return AddWinesDto.builder()
                .Id(addWines.getId())
                .quantity(addWines.getQuantity())
                .createdOn(addWines.getCreatedOn())
                .updatedOn(addWines.getUpdatedOn())
                .build();
    }
}
