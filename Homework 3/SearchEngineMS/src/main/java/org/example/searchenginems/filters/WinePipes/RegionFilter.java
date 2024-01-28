package org.example.searchenginems.filters.WinePipes;

import org.example.searchenginems.model.Wine;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RegionFilter implements Filter<String> {

    @Override
    public List<Wine> execute(String region, List<Wine> wines) {
        if(region != null) {
            return wines.stream().filter(wine -> wine.getWinery().getRegion().name().equals(region)).collect(Collectors.toList());
        }
        return wines;
    }
}
