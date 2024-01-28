package dians.homework3.wines02.filter_models;

public class WineFilterRequest {
    private String searchQuery;
    private String priceFilter;
    private String region;
    private String winery;
    private String litrage;

    public WineFilterRequest(String searchQuery, String priceFilter, String region, String winery, String litrage) {
        this.searchQuery = searchQuery;
        this.priceFilter = priceFilter;
        this.region = region;
        this.winery = winery;
        this.litrage = litrage;
    }
}
