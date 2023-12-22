package dians.homework3.wines02.mapper;

import dians.homework3.wines02.dto.WineryDto;
import dians.homework3.wines02.dto.WineryDto2;
import dians.homework3.wines02.model.Winery;

import java.util.stream.Collectors;

public class WineryMapper {
    public static Winery mapToWinery(WineryDto wineryDto) {
        return Winery.builder()
                .Id(wineryDto.getId())
                .description(wineryDto.getDescription())
                .name(wineryDto.getName())
                .photoUrl(wineryDto.getPhotoUrl())
                .xCordinate(wineryDto.getXCordinate())
                .yCordinate(wineryDto.getYCordinate())
                .region(wineryDto.getRegion())
                .wineryLink(wineryDto.getWineryLink())
                .build();
    }
    public static WineryDto mapToWineryDto(Winery winery) {
        return WineryDto.builder()
                .Id(winery.getId())
                .description(winery.getDescription())
//                .events(winery.getEvents())
                .name(winery.getName())
                .photoUrl(winery.getPhotoUrl())
                .xCordinate(winery.getXCordinate())
                .yCordinate(winery.getYCordinate())
                .region(winery.getRegion())
                .wines(winery.getWines().stream().map(WineMapper::mapToWineDto2).collect(Collectors.toList()))
                .wineryLink(winery.getWineryLink())
                .build();
    }

    public static WineryDto2 mapToWineryDto2(Winery winery) {
        return WineryDto2.builder()
                .Id(winery.getId())
                .description(winery.getDescription())
//                .events(winery.getEvents())
                .name(winery.getName())
                .photoUrl(winery.getPhotoUrl())
                .xCordinate(winery.getXCordinate())
                .yCordinate(winery.getYCordinate())
                .region(winery.getRegion())
                .wineryLink(winery.getWineryLink())
                .build();
    }
}
