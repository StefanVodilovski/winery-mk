package dians.homework3.wines02.filters.WinePipes;

import dians.homework3.wines02.dto.WineDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RegionFilter implements Filter<String> {

    @Override
    public List<WineDto> execute(String region, List<WineDto> wines) {
        if(region != null) {
            return wines.stream().filter(wine -> wine.getWinery().getRegion().name().equals(region)).collect(Collectors.toList());
        }
        return wines;
    }
}
