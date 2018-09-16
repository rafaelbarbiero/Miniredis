package br.com.aquiris.core.domain;

import java.time.LocalDate;

public class DataModel implements Comparable<DataModel> {

    private int score = 0;
    private LocalDate expiration;
    private String value;
    private String key;

    public DataModel() {
    }

    public DataModel(final String key, int score, String value) {
        this.key = key;
        this.score = score;
        this.value = value;
    }

    public DataModel(final String key, String value) {
        this.key = key;
        this.value = value;
    }

    public DataModel(final String key, int score, LocalDate expiration, String value) {
        this.key = key;
        this.score = score;
        this.expiration = expiration;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public LocalDate getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDate expiration) {
        this.expiration = expiration;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int compareTo(DataModel o) {
        return Integer.compare(this.score, o.score);
    }
}
