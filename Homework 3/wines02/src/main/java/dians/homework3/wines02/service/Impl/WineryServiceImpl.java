package dians.homework3.wines02.service.Impl;

import dians.homework3.wines02.repository.WineryRepository;
import dians.homework3.wines02.service.WineryService;
import org.springframework.stereotype.Service;

@Service
public class WineryServiceImpl implements WineryService {
    WineryRepository wineryRepository;

    public WineryServiceImpl(WineryRepository wineryRepository) {
        this.wineryRepository = wineryRepository;
    }
}
