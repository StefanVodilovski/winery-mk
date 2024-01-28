package org.example.searchenginems.filters.WinePipes;

import lombok.Getter;
import org.example.searchenginems.model.Wine;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PipeWines<T> {
    private List<Filter<T>> filters = new ArrayList<>();

    public void addFilter(Filter<T> filter){
        filters.add(filter);}

    public List<Long> runFilters(List<T> input, List<Wine> wines) {
        List<Wine> wineDtos = wines;
        int counter = -1;
        for (Filter<T> filter: filters) {
            counter += 1;
            wineDtos = filter.execute(input.get(counter),wineDtos);
        }
        return wineDtos.stream().map(Wine::getId).collect(Collectors.toList());
    }
}
