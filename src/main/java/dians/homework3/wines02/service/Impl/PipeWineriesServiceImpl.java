package dians.homework3.wines02.service.Impl;

import dians.homework3.wines02.dto.WineryDto;
import dians.homework3.wines02.filters.WineryPipes.PipeWineries;
import dians.homework3.wines02.filters.WineryPipes.RegionFilterWinery;
import dians.homework3.wines02.filters.WineryPipes.SearchFilterWinery;
import dians.homework3.wines02.mapper.WineryMapper;
import dians.homework3.wines02.repository.WineryRepository;
import dians.homework3.wines02.service.PipeWineriesService;
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
    public List<WineryDto> filter(String search, String region) {
        List<String> stringList = new ArrayList<>();
        stringList.add(search);
        stringList.add(region);
        List<WineryDto> wineryDtos = wineryRepository.findAll().stream().map(WineryMapper::mapToWineryDto).collect(Collectors.toList());
        return pipeWineries.runFilters(stringList, wineryDtos);
    }
}
