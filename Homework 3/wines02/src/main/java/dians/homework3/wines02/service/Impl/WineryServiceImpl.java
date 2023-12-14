package dians.homework3.wines02.service.Impl;

import dians.homework3.wines02.dto.WineryDto;
import dians.homework3.wines02.model.Winery;
import dians.homework3.wines02.repository.WineryRepository;
import dians.homework3.wines02.service.WineryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static dians.homework3.wines02.mapper.WineryMapper.mapToWineryDto;

@Service
public class WineryServiceImpl implements WineryService {
    WineryRepository wineryRepository;

    public WineryServiceImpl(WineryRepository wineryRepository) {
        this.wineryRepository = wineryRepository;
    }

    @Override
    public List<WineryDto> getAll() {
        return wineryRepository.findAll().stream().map(winery -> mapToWineryDto(winery)).collect(Collectors.toList());
    }
}
