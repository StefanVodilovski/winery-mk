package dians.homework3.wines02.mapper;

import dians.homework3.wines02.dto.WineDto;
import dians.homework3.wines02.model.Wine;

public class WineMapper {
    public static Wine mapToWine(WineDto wineDto) {
        return Wine.builder()
                .Id(wineDto.getId())
                .stock(wineDto.getStock())
                .litrage(wineDto.getLitrage())
                .name(wineDto.getName())
                .photoUrl(wineDto.getPhotoUrl())
                .price(wineDto.getPrice())
                .winery(wineDto.getWinery())
                .region(wineDto.getRegion())
                .build();
    }

    public static WineDto mapToWineDto(Wine wine) {
        return WineDto.builder()
                .Id(wine.getId())
                .stock(wine.getStock())
                .litrage(wine.getLitrage())
                .name(wine.getName())
                .photoUrl(wine.getPhotoUrl())
                .price(wine.getPrice())
                .winery(wine.getWinery())
                .region(wine.getRegion())
                .build();
    }
}
