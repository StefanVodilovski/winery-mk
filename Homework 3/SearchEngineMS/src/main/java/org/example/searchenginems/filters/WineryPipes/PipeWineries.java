package org.example.searchenginems.filters.WineryPipes;

import org.example.searchenginems.model.Wine;
import org.example.searchenginems.model.Winery;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PipeWineries<T> {
    private List<Filter<T>> filters = new ArrayList<>();

    public void addFilter(Filter<T> filter){
        filters.add(filter);}

    public List<Long> runFilters(List<T> input, List<Winery> winery) {
        List<Winery> wineryDtos = winery;
        int counter = -1;
        for (Filter<T> filter: filters) {
            counter += 1;
            wineryDtos = filter.execute(input.get(counter),wineryDtos);
        }
        return wineryDtos.stream().map(Winery::getId).collect(Collectors.toList());
    }
}
