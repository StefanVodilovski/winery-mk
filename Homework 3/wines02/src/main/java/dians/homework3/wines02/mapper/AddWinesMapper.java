package dians.homework3.wines02.mapper;

import dians.homework3.wines02.dto.AddWinesDto;
import dians.homework3.wines02.model.AddWines;

import static dians.homework3.wines02.mapper.WineMapper.mapToWine;
import static dians.homework3.wines02.mapper.WineMapper.mapToWineDto;

public class AddWinesMapper {
    public static AddWines mapToAddWines(AddWinesDto addWinesDto) {
        return AddWines.builder()
                .Id(addWinesDto.getId())
                .quantity(addWinesDto.getQuantity())
                .createdOn(addWinesDto.getCreatedOn())
                .updatedOn(addWinesDto.getUpdatedOn())
                .wine(mapToWine(addWinesDto.getWine()))
                .build();
    }

    public static AddWinesDto mapToAddWinesDto(AddWines addWines) {
        return AddWinesDto.builder()
                .Id(addWines.getId())
                .quantity(addWines.getQuantity())
                .createdOn(addWines.getCreatedOn())
                .updatedOn(addWines.getUpdatedOn())
                .wine(mapToWineDto(addWines.getWine()))
                .build();
    }
}
