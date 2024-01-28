package dians.homework3.wines02.filter_models;

public class EventFilterRequest {
    private String searchQuery;
    private String winery;

    public EventFilterRequest(String searchQuery, String winery) {
        this.searchQuery = searchQuery;
        this.winery = winery;
    }
}