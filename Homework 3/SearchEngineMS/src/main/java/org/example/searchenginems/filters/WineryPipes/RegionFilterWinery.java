package org.example.searchenginems.filters.WineryPipes;


import org.example.searchenginems.model.Winery;

import java.util.List;
import java.util.stream.Collectors;

public class RegionFilterWinery implements Filter<String> {
    @Override
    public List<Winery> execute(String region, List<Winery> wineries) {
        if(region != null) {
            return wineries.stream().filter(wine -> wine.getRegion().name().equals(region)).collect(Collectors.toList());
        }
        return wineries;
    }
}
