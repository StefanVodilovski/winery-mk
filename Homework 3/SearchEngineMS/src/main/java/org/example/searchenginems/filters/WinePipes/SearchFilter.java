package org.example.searchenginems.filters.WinePipes;

import org.example.searchenginems.model.Wine;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SearchFilter implements Filter<String> {
    @Override
    public List<Wine> execute(String searchInput, List<Wine> wines) {
        if(searchInput != null) {
            String finalSearchQuery = searchInput.toLowerCase();
            return wines.stream().filter(wine -> wine.getName().toLowerCase().contains(finalSearchQuery)).collect(Collectors.toList());
        }
        return wines;
    }
}
