package dians.homework3.wines02.filters.WineryPipes;

import dians.homework3.wines02.dto.WineryDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SearchFilterWinery implements Filter<String> {

    @Override
    public List<WineryDto> execute(String searchInput, List<WineryDto> wineries) {
        if(searchInput != null) {
            String finalSearchQuery = searchInput.toLowerCase();
            return wineries.stream().filter(wine -> wine.getName().contains(finalSearchQuery)).collect(Collectors.toList());
        }
        return wineries;
    }
}
