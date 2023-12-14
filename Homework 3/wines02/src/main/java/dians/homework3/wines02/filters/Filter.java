package dians.homework3.wines02.filters;

import dians.homework3.wines02.dto.WineDto;

import java.util.List;

public interface Filter<T> {
    List<WineDto> execute(T input, List<WineDto> wines);
}
