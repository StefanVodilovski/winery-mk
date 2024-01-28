package org.example.searchenginems.filters.WineryPipes;

import org.example.searchenginems.model.Winery;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SearchFilterWinery implements Filter<String> {

    @Override
    public List<Winery> execute(String searchInput, List<Winery> wineries) {
        if(searchInput != null) {
            String finalSearchQuery = searchInput.toLowerCase();
            return wineries.stream().filter(wine -> wine.getName().toLowerCase().contains(finalSearchQuery)).collect(Collectors.toList());
        }
        return wineries;
    }
}
