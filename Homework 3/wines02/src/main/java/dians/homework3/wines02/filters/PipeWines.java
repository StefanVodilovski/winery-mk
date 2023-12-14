package dians.homework3.wines02.filters;

import dians.homework3.wines02.dto.WineDto;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class PipeWines<T> {
    private List<Filter<T>> filters = new ArrayList<>();

    public void addFilter(Filter<T> filter){
        filters.add(filter);}

    public List<WineDto> runFilters(List<T> input, List<WineDto> wines) {
        List<WineDto> wineDtos = wines;
        int counter = -1;
        for (Filter<T> filter: filters) {
            counter += 1;
            wineDtos = filter.execute(input.get(counter),wineDtos);
        }
        return wineDtos;
    }
}
