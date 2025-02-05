package com.netflixdata.api.content;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Content {

    @Id
    private Long id;

    private String title;
    private String type;
    private String genres;
    private Integer releaseYear;
    private String imdbId;
    private Double imdbAverageRating;
    private Integer imdbVoteCount;
    private String availableCountries;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public Double getImdbAverageRating() {
        return imdbAverageRating;
    }

    public void setImdbAverageRating(Double imdbAverageRating) {
        this.imdbAverageRating = imdbAverageRating;
    }

    public Integer getImdbVoteCount() {
        return imdbVoteCount;
    }

    public void setImdbVoteCount(Integer imdbVoteCount) {
        this.imdbVoteCount = imdbVoteCount;
    }

    public String getAvailableCountries() {
        return availableCountries;
    }

    public void setAvailableCountries(String availableCountries) {
        this.availableCountries = availableCountries;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
