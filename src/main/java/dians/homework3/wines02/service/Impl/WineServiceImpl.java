package dians.homework3.wines02.service.Impl;

import dians.homework3.wines02.dto.WineDto;
import dians.homework3.wines02.mapper.WineMapper;
import dians.homework3.wines02.model.Wine;
import dians.homework3.wines02.repository.WineRepository;
import dians.homework3.wines02.service.WineService;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static dians.homework3.wines02.mapper.WineMapper.mapToWineDto;

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

    @Override
    public WineDto findById(Long wineId) {
        Optional<Wine> wine = wineRepository.findById(wineId);
        if (wine.isPresent()) {
            return mapToWineDto(wine.get());
        }
        try {
            throw new NotFoundException("Wine with ID " + wineId + " not found");
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
