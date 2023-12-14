package dians.homework3.wines02.mapper;

import dians.homework3.wines02.dto.WineryDto;
import dians.homework3.wines02.model.Winery;

public class WineryMapper {
    public static Winery mapToWinery(WineryDto wineryDto) {
        return Winery.builder()
                .Id(wineryDto.getId())
                .description(wineryDto.getDescription())
                .events(wineryDto.getEvents())
                .name(wineryDto.getName())
                .photoUrl(wineryDto.getPhotoUrl())
                .build();
    }

    public static WineryDto mapToWineryDto(Winery winery) {
        return WineryDto.builder()
                .Id(winery.getId())
                .description(winery.getDescription())
                .events(winery.getEvents())
                .name(winery.getName())
                .photoUrl(winery.getPhotoUrl())
                .build();
    }
}
