package dians.homework3.wines02.filters.WinePipes;

import dians.homework3.wines02.dto.WineDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WineryFilter implements Filter<String> {
    @Override
    public List<WineDto> execute(String wineryId, List<WineDto> wines) {
        if(wineryId != null) {
            return wines.stream().filter(wine -> wine.getWinery().getId().equals(Long.parseLong(wineryId))).collect(Collectors.toList());
        }
        return wines;
    }
}
