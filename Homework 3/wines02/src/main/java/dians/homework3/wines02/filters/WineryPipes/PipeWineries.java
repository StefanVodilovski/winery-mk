package dians.homework3.wines02.filters.WineryPipes;

import dians.homework3.wines02.dto.WineryDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PipeWineries<T> {
    private List<Filter<T>> filters = new ArrayList<>();

    public void addFilter(Filter<T> filter){
        filters.add(filter);}

    public List<WineryDto> runFilters(List<T> input, List<WineryDto> winery) {
        List<WineryDto> wineryDtos = winery;
        int counter = -1;
        for (Filter<T> filter: filters) {
            counter += 1;
            wineryDtos = filter.execute(input.get(counter),wineryDtos);
        }
        return wineryDtos;
    }
}
