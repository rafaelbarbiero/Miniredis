package br.com.aquiris.core;

import java.util.Map;

public class ScoreModel implements Comparable<ScoreModel>{

    private int score;
    private String value;

    public ScoreModel(Map.Entry<Integer, String> entry) {
        this.score = entry.getKey();
        this.value = entry.getValue();
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
}
