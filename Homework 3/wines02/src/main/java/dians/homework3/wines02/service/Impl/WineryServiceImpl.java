package dians.homework3.wines02.service.Impl;

import dians.homework3.wines02.model.Winery;
import dians.homework3.wines02.repository.WineryRepository;
import dians.homework3.wines02.service.WineryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WineryServiceImpl implements WineryService {
    WineryRepository wineryRepository;

    public WineryServiceImpl(WineryRepository wineryRepository) {
        this.wineryRepository = wineryRepository;
    }

    @Override
    public List<Winery> getAll() {
        return wineryRepository.findAll();
    }
}
