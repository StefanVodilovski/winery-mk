package org.example.searchenginems.service.Impl;

import org.example.searchenginems.filters.WinePipes.*;
import org.example.searchenginems.model.Wine;
import org.example.searchenginems.repository.WineRepository;
import org.example.searchenginems.service.PipeWinesService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PipeWinesServiceImpl implements PipeWinesService {
    private final PipeWines<String> pipeWines;
    private final LitrageFilter litrageFilter;
    private final PriceFilter priceFilter;
    private final RegionFilter regionFilter;
    private final WineryFilter wineryFilter;
    private final SearchFilter searchFilter;
    private final WineRepository wineRepository;

    public PipeWinesServiceImpl(WineRepository wineRepository) {
        this.pipeWines = new PipeWines<>();
        this.litrageFilter = new LitrageFilter();
        this.priceFilter = new PriceFilter();
        this.regionFilter = new RegionFilter();
        this.wineryFilter = new WineryFilter();
        this.searchFilter = new SearchFilter();
        pipeWines.addFilter(searchFilter);
        pipeWines.addFilter(priceFilter);
        pipeWines.addFilter(regionFilter);
        pipeWines.addFilter(wineryFilter);
        pipeWines.addFilter(litrageFilter);
        this.wineRepository = wineRepository;
    }

    @Override
    public List<Long> filter(String search, String priceValue, String regionValue, String wineryValue, String litrageValue) {
        List<String> stringList = new ArrayList<>();
        stringList.add(search);
        stringList.add(priceValue);
        stringList.add(regionValue);
        stringList.add(wineryValue);
        stringList.add(litrageValue);
        List<Wine> wines = wineRepository.findAll().stream().collect(Collectors.toList());
        return pipeWines.runFilters(stringList, wines);
    }
}
