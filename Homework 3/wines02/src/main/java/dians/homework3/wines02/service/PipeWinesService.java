package dians.homework3.wines02.service;

import dians.homework3.wines02.dto.WineDto;

import java.util.List;

public interface PipeWinesService {
    List<WineDto> filter(String priceValue, String regionValue, String wineryValue, String litrageValue);
}
