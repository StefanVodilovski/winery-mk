package dians.homework3.wines02.service;


import dians.homework3.wines02.dto.WineDto;

import java.util.List;
import java.util.Optional;

public interface WineService {
    List<WineDto> getAll();

    WineDto findById(Long wineId);
}
