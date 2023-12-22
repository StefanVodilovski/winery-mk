package dians.homework3.wines02.filters.WineryPipes;

import dians.homework3.wines02.dto.WineryDto;

import java.util.List;
import java.util.stream.Collectors;

public class RegionFilterWinery implements Filter<String> {
    @Override
    public List<WineryDto> execute(String region, List<WineryDto> wineries) {
        if(region != null) {
            return wineries.stream().filter(wine -> wine.getRegion().name().equals(region)).collect(Collectors.toList());
        }
        return wineries;
    }
}
