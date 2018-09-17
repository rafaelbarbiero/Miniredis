package br.com.aquiris.core;

import java.util.Map;
import java.util.Objects;

public class ScoreModel implements Comparable<ScoreModel>{

    private int score;
    private String value;

    public ScoreModel(Map.Entry<String, Integer> entry) {
        this.score = entry.getValue();
        this.value = entry.getKey();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int compareTo(ScoreModel o) {
        return Integer.compare(this.score, o.score);
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoreModel that = (ScoreModel) o;
        return score == that.score &&
                Objects.equals(value, that.value);
    }

    @Override public int hashCode() {
        return Objects.hash(score, value);
    }
}
