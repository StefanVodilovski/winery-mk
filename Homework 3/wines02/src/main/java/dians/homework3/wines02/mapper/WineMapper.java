package dians.homework3.wines02.mapper;

import dians.homework3.wines02.dto.WineDto;
import dians.homework3.wines02.dto.WineDto2;
import dians.homework3.wines02.model.Wine;

import static dians.homework3.wines02.mapper.WineryMapper.*;

public class WineMapper {
    public static Wine mapToWine(WineDto wineDto) {
        return Wine.builder()
                .Id(wineDto.getId())
                .stock(wineDto.getStock())
                .litrage(wineDto.getLitrage())
                .name(wineDto.getName())
                .photoUrl(wineDto.getPhotoUrl())
                .price(wineDto.getPrice())
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
                .winery(mapToWineryDto2(wine.getWinery()))
                .build();
    }

    public static WineDto2 mapToWineDto2(Wine wine) {
        return WineDto2.builder()
                .Id(wine.getId())
                .stock(wine.getStock())
                .litrage(wine.getLitrage())
                .name(wine.getName())
                .photoUrl(wine.getPhotoUrl())
                .price(wine.getPrice())
                .build();
    }
}
