package dians.homework3.wines02.mapper;

import dians.homework3.wines02.dto.AddWinesDto;
import dians.homework3.wines02.model.AddWines;

public class AddWinesMapper {
    public static AddWines mapToAddWines(AddWinesDto addWinesDto) {
        return AddWines.builder()
                .Id(addWinesDto.getId())
                .wine(addWinesDto.getWine())
                .order(addWinesDto.getOrder())
                .cart(addWinesDto.getCart())
                .quantity(addWinesDto.getQuantity())
                .createdOn(addWinesDto.getCreatedOn())
                .updatedOn(addWinesDto.getUpdatedOn())
                .build();
    }

    public static AddWinesDto mapToAddWinesDto(AddWines addWines) {
        return AddWinesDto.builder()
                .Id(addWines.getId())
                .wine(addWines.getWine())
                .order(addWines.getOrder())
                .cart(addWines.getCart())
                .quantity(addWines.getQuantity())
                .createdOn(addWines.getCreatedOn())
                .updatedOn(addWines.getUpdatedOn())
                .build();
    }
}
