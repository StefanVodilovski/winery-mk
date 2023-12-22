package dians.homework3.wines02.filters.WinePipes;

import dians.homework3.wines02.dto.WineDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WineryFilter implements Filter<String> {
    @Override
    public List<WineDto> execute(String winery, List<WineDto> wines) {
        if(!winery.equals("-1")) {
            return wines.stream().filter(wine -> wine.getWinery().getName().equals(winery)).collect(Collectors.toList());
        }
        return wines;
    }
}
