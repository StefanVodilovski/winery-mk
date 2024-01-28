package dians.homework3.wines02.service;

import dians.homework3.wines02.dto.WineDto;
import dians.homework3.wines02.dto.WineryDto;
import dians.homework3.wines02.model.Winery;

import java.util.List;

public interface WineryService {
    List<WineryDto> getAll();

    WineryDto findById(Long wineryId);

    List<WineDto> getAllWineryWines(Long wineryId);

    List<Winery> findAllById(List<Long> wineriesId);
}
