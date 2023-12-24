package dians.homework3.wines02.filters.WinePipes;

import dians.homework3.wines02.dto.WineDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PriceFilter implements Filter<String> {

    @Override
    public List<WineDto> execute(String price, List<WineDto> wines) {
        if(price != null) {
            Integer priceInteger = Integer.parseInt(price);
            return wines.stream().filter(wine -> (wine.getPrice() <= priceInteger)).collect(Collectors.toList());
        }
        return wines;
    }
}
