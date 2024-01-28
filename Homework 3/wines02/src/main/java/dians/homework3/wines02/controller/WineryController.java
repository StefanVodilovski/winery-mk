package dians.homework3.wines02.controller;

import dians.homework3.wines02.dto.WineDto;
import dians.homework3.wines02.dto.WineryDto;
import dians.homework3.wines02.filter_models.WineFilterRequest;
import dians.homework3.wines02.filter_models.WineryFilterRequest;
import dians.homework3.wines02.mapper.WineMapper;
import dians.homework3.wines02.mapper.WineryMapper;
import dians.homework3.wines02.model.Wine;
import dians.homework3.wines02.model.Winery;
import dians.homework3.wines02.service.PipeWineriesService;
import dians.homework3.wines02.service.WineryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("http://localhost:3001")
@RestController
@RequestMapping("/wineries/")
public class WineryController {
    private final WineryService wineryService;
    private final PipeWineriesService pipeWineriesService;

    public WineryController(WineryService wineryService, PipeWineriesService pipeWineriesService) {
        this.wineryService = wineryService;
        this.pipeWineriesService = pipeWineriesService;
    }

    @GetMapping("all")
    public List<WineryDto> getAllWines() {
        return this.wineryService.getAll();
    }

    @GetMapping("filter")
    public List<WineryDto> filterWines(@RequestParam(required = false) String searchQuery,
                                     @RequestParam(required = false) String region) {
        RestTemplate restTemplate = new RestTemplate();

        StringBuilder apiUrlBuilder = new StringBuilder("http://localhost:8081/api/filter/wineries?");

        if (searchQuery != null) {
            apiUrlBuilder.append("searchQuery=").append(searchQuery).append("&");
        }

        if (region != null) {
            apiUrlBuilder.append("region=").append(region).append("&");
        }

        String apiUrl = apiUrlBuilder.toString();

        // Create a WineFilterRequest object to hold the parameters
        WineryFilterRequest filterRequest = new WineryFilterRequest(searchQuery, region);

        // Make the HTTP request and retrieve the response
        ResponseEntity<Long[]> responseEntity = restTemplate.getForEntity(apiUrl, Long[].class, filterRequest);

        // Convert Long[] to List<Long>
        List<Long> wineryIds = Arrays.asList(responseEntity.getBody());

        List<Winery> wineries = wineryService.findAllById(wineryIds);
        // Extract the array of WineDto from the response
        List<WineryDto> wineryDtos = wineries.stream().map(WineryMapper::mapToWineryDto).collect(Collectors.toList());

        return wineryDtos;
    }

    //Vidi u WineryDto sto vrakja
    @GetMapping("{wineryId}")
    public WineryDto getWineById(@PathVariable("wineryId") Long wineryId) {
        return wineryService.findById(wineryId);
    }

    //Gi vrakja site vina na vinarijata
    @GetMapping("{wineryId}/wines")
    public List<WineDto> getWinerywinesById(@PathVariable("wineryId") Long wineryId) {
        return wineryService.getAllWineryWines(wineryId);
    }
}
