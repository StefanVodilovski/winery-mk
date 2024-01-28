package org.example.searchenginems.filters.WineryPipes;

import org.example.searchenginems.model.Winery;

import java.util.List;

public interface Filter<T> {
    List<Winery> execute(T input, List<Winery> wineries);
}
