package org.example.searchenginems.service;

import org.example.searchenginems.model.Winery;

import java.util.List;

public interface PipeWineriesService {
    List<Winery> filter(String search, String region);
}