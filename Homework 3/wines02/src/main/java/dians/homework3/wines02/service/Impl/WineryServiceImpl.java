package dians.homework3.wines02.service.Impl;

import dians.homework3.wines02.dto.WineryDto;
import dians.homework3.wines02.mapper.WineryMapper;
import dians.homework3.wines02.model.Winery;
import dians.homework3.wines02.repository.WineryRepository;
import dians.homework3.wines02.service.WineryService;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static dians.homework3.wines02.mapper.WineryMapper.mapToWineryDto;

@Transactional
@Service
public class WineryServiceImpl implements WineryService {
    private final WineryRepository wineryRepository;

    public WineryServiceImpl(WineryRepository wineryRepository) {
        this.wineryRepository = wineryRepository;
    }

    @Override
    public List<WineryDto> getAll() {
        return wineryRepository.findAll().stream().map(WineryMapper::mapToWineryDto).collect(Collectors.toList());
    }

    @Override
    public WineryDto findById(Long wineryId) {
        Optional<Winery> winery = wineryRepository.findById(wineryId);
        if (winery.isPresent()) {
            return mapToWineryDto(winery.get());
        }
        try {
            throw new NotFoundException("Winery with ID " + wineryId + " not found");
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
