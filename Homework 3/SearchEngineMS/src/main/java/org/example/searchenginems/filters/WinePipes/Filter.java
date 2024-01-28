package org.example.searchenginems.filters.WinePipes;

import org.example.searchenginems.model.Wine;

import java.util.List;

public interface Filter<T> {
    List<Wine> execute(T input, List<Wine> wines);
}
