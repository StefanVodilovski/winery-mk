package dians.homework3.wines02.controller;


import dians.homework3.wines02.dto.AddWinesDto;
import dians.homework3.wines02.dto.UserDto;
import dians.homework3.wines02.dto.WineDto;
import dians.homework3.wines02.filter_models.WineFilterRequest;
import dians.homework3.wines02.mapper.WineMapper;
import dians.homework3.wines02.model.AddWines;
import dians.homework3.wines02.model.UserEntity;
import dians.homework3.wines02.model.Wine;
import dians.homework3.wines02.security.SecurityUtil;
import dians.homework3.wines02.security.UserAuthProvider;
import dians.homework3.wines02.service.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static dians.homework3.wines02.mapper.UserMapper.mapToUserDto;

@CrossOrigin("http://localhost:3001")
@RestController
@RequestMapping("/wines/")
public class WineController {
    private final WineService wineService;
    private final UserService userService;
    private final PipeWinesService pipeWinesService;

    public WineController(WineService wineService, UserService userService, PipeWinesService pipeWinesService) {
        this.wineService = wineService;
        this.userService = userService;
        this.pipeWinesService = pipeWinesService;
    }

    @GetMapping("all")
    public List<WineDto> getAllWines() {
        return this.wineService.getAll();
    }

    @GetMapping("filter")
    public List<WineDto> filterWines(@RequestParam(required = false) String searchQuery,
                                     @RequestParam(required = false) String priceFilter,
                                     @RequestParam(required = false) String region,
                                     @RequestParam(required = false) String winery,
                                     @RequestParam(required = false) String litrage) {

        RestTemplate restTemplate = new RestTemplate();

        StringBuilder apiUrlBuilder = new StringBuilder("http://localhost:8081/api/filter/wines?");

        // Add non-null parameters to the URL
        if (searchQuery != null) {
            apiUrlBuilder.append("searchQuery=").append(searchQuery).append("&");
        }

        if (priceFilter != null) {
            apiUrlBuilder.append("priceFilter=").append(priceFilter).append("&");
        }

        if (region != null) {
            apiUrlBuilder.append("region=").append(region).append("&");
        }

        if (winery != null) {
            apiUrlBuilder.append("winery=").append(winery).append("&");
        }

        if (litrage != null) {
            apiUrlBuilder.append("litrage=").append(litrage).append("&");
        }

        String apiUrl = apiUrlBuilder.toString();

        // Create a WineFilterRequest object to hold the parameters
        WineFilterRequest filterRequest = new WineFilterRequest(searchQuery, priceFilter, region, winery, litrage);

        // Make the HTTP request and retrieve the response
        ResponseEntity<Long[]> responseEntity = restTemplate.getForEntity(apiUrl, Long[].class, filterRequest);

        // Convert Long[] to List<Long>
        List<Long> wineIds = Arrays.asList(responseEntity.getBody());

        List<Wine> wines = wineService.findAllById(wineIds);
        // Extract the array of WineDto from the response
        List<WineDto> wineDtos = wines.stream().map(WineMapper::mapToWineDto).collect(Collectors.toList());

        // Convert the array to a List and return it
        return wineDtos;
    }

    @GetMapping("{wineId}")
    public WineDto getWineById(@PathVariable("wineId") Long wineId) {
        return wineService.findById(wineId);
    }
}