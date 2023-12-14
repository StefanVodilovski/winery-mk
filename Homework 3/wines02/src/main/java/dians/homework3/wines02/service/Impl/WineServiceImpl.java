package dians.homework3.wines02.service.Impl;

import dians.homework3.wines02.model.Wine;
import dians.homework3.wines02.repository.WineRepository;
import dians.homework3.wines02.service.WineService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WineServiceImpl implements WineService {
    private final WineRepository wineRepository;

    public WineServiceImpl(WineRepository wineRepository) {
        this.wineRepository = wineRepository;
    }

    @Override
    public List<Wine> getAll() {
        return wineRepository.findAll();
    }
}
