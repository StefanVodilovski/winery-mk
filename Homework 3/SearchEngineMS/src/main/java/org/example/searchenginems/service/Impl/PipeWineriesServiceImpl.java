package org.example.searchenginems.service.Impl;

import org.example.searchenginems.filters.WineryPipes.PipeWineries;
import org.example.searchenginems.filters.WineryPipes.RegionFilterWinery;
import org.example.searchenginems.filters.WineryPipes.SearchFilterWinery;
import org.example.searchenginems.model.Winery;
import org.example.searchenginems.repository.WineryRepository;
import org.example.searchenginems.service.PipeWineriesService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PipeWineriesServiceImpl implements PipeWineriesService {
    private final WineryRepository wineryRepository;
    private final PipeWineries<String> pipeWineries;
    private final SearchFilterWinery searchFilterWinery;
    private final RegionFilterWinery regionFilterWinery;

    public PipeWineriesServiceImpl(WineryRepository wineryRepository) {
        this.pipeWineries = new PipeWineries<>();
        this.searchFilterWinery = new SearchFilterWinery();
        this.regionFilterWinery = new RegionFilterWinery();
        pipeWineries.addFilter(searchFilterWinery);
        pipeWineries.addFilter(regionFilterWinery);
        this.wineryRepository = wineryRepository;
    }


    @Override
    public List<Winery> filter(String search, String region) {
        List<String> stringList = new ArrayList<>();
        stringList.add(search);
        stringList.add(region);
        List<Winery> wineryDtos = wineryRepository.findAll().stream().collect(Collectors.toList());
        return pipeWineries.runFilters(stringList, wineryDtos);
    }
}
