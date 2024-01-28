package org.example.searchenginems.service;

import org.example.searchenginems.model.Wine;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PipeWinesService {
    List<Wine> filter(String search, String priceValue, String regionValue, String wineryValue, String litrageValue);
}
