package com.example.wine_model.service.Impl;

import com.example.wine_model.model.Wine;
import com.example.wine_model.repository.WineRepository;
import com.example.wine_model.service.WineService;
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
