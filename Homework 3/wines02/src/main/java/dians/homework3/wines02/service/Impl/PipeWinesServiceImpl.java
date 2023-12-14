package dians.homework3.wines02.service.Impl;

import dians.homework3.wines02.dto.WineDto;
import dians.homework3.wines02.filters.*;
import dians.homework3.wines02.mapper.WineMapper;
import dians.homework3.wines02.repository.WineRepository;
import dians.homework3.wines02.service.PipeWinesService;
import org.springframework.beans.factory.annotation.Autowired;
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
    WineRepository wineRepository;

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
    public List<WineDto> filter(String search, String priceValue, String regionValue, String wineryValue, String litrageValue) {
        List<String> stringList = new ArrayList<>();
        stringList.add(search);
        stringList.add(priceValue);
        stringList.add(regionValue);
        stringList.add(wineryValue);
        stringList.add(litrageValue);
        List<WineDto> wineDtos = wineRepository.findAll().stream().map(WineMapper::mapToWineDto).collect(Collectors.toList());
        return pipeWines.runFilters(stringList, wineDtos);
    }
}
