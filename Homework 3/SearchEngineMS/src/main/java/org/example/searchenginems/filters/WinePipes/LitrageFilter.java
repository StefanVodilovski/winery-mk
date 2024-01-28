package org.example.searchenginems.filters.WinePipes;

import org.example.searchenginems.model.Wine;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LitrageFilter implements Filter<String> {
    @Override
    public List<Wine> execute(String litrage, List<Wine> wines) {
        if(litrage != null) {
            Double litrageDouble = Double.parseDouble(litrage);
            return wines.stream().filter(wine -> wine.getLitrage() == litrageDouble).collect(Collectors.toList());
        }
        return wines;
    }
}
