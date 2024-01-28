package dians.homework3.wines02.filter_models;

public class WineryFilterRequest {
    private String searchQuery;
    private String region;

    public WineryFilterRequest(String searchQuery, String region) {
        this.searchQuery = searchQuery;
        this.region = region;
    }
}