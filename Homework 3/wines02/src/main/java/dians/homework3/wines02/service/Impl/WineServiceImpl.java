package dians.homework3.wines02.service.Impl;

import dians.homework3.wines02.dto.WineDto;
import dians.homework3.wines02.mapper.WineMapper;
import dians.homework3.wines02.repository.WineRepository;
import dians.homework3.wines02.service.WineService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WineServiceImpl implements WineService {
    private final WineRepository wineRepository;

    public WineServiceImpl(WineRepository wineRepository) {
        this.wineRepository = wineRepository;
    }

    @Override
    public List<WineDto> getAll() {
        return wineRepository.findAll().stream().map(WineMapper::mapToWineDto).collect(Collectors.toList());
    }
}
