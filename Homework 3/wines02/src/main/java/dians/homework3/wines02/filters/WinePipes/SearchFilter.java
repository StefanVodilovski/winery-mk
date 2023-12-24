package dians.homework3.wines02.filters.WinePipes;

import dians.homework3.wines02.dto.WineDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SearchFilter implements Filter<String> {
    @Override
    public List<WineDto> execute(String searchInput, List<WineDto> wines) {
        if(searchInput != null) {
            String finalSearchQuery = searchInput.toLowerCase();
            return wines.stream().filter(wine -> wine.getName().toLowerCase().contains(finalSearchQuery)).collect(Collectors.toList());
        }
        return wines;
    }
}
