package dians.homework3.wines02.filters.WinePipes;

import dians.homework3.wines02.dto.WineDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LitrageFilter implements Filter<String> {
    @Override
    public List<WineDto> execute(String litrage, List<WineDto> wines) {
        if(litrage != null) {
            Double litrageDouble = Double.parseDouble(litrage);
            return wines.stream().filter(wine -> wine.getLitrage() == litrageDouble).collect(Collectors.toList());
        }
        return wines;
    }
}
