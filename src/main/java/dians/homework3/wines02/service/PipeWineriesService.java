package dians.homework3.wines02.service;

import dians.homework3.wines02.dto.WineDto;
import dians.homework3.wines02.dto.WineryDto;

import java.util.List;

public interface PipeWineriesService {
    List<WineryDto> filter(String search, String region);
}