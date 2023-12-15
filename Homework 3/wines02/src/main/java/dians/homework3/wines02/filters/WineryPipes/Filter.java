package dians.homework3.wines02.filters.WineryPipes;

import dians.homework3.wines02.dto.WineryDto;

import java.util.List;

public interface Filter<T> {
    List<WineryDto> execute(T input, List<WineryDto> wineries);
}
